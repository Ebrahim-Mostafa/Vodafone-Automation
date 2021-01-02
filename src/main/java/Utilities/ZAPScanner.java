package Utilities;

import net.continuumsecurity.proxy.ScanningProxy;
import net.continuumsecurity.proxy.Spider;
import net.continuumsecurity.proxy.ZAProxyScanner;
import org.openqa.selenium.Proxy;
import org.zaproxy.clientapi.core.Alert;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static BasePackage.BaseTest.driver;
import static Loggers.Log4JLogger.logger;
import static Utilities.Utils.*;

public class ZAPScanner {

    private final static String BASE_URL = "http://automationpractice.com/index.php";
    private final static String LOGOUT_URL = "http://automationpractice.com/index.php?controller=authentication&back";
    public  final static String ZAP_PROXYHOST = "localhost";
    public  final static int ZAP_PROXYPORT = 8080;
    private final static String ZAP_APIKEY = null;
    private final static String MEDIUM = "MEDIUM";
    private final static String HIGH = "HIGH";
    public static ScanningProxy zapScanner;
    private static Spider zapSpider;
    private final static String[] policyNames = {"directory-browsing","cross-site-scripting","sql-injection","path-traversal","remote-file-inclusion","server-side-include",
            "script-active-scan-rules","server-side-code-injection","external-redirect","crlf-injection"};
    private static int currentScanID;

    public static void zapSetup() {
        zapScanner = new ZAProxyScanner(ZAP_PROXYHOST,ZAP_PROXYPORT,ZAP_APIKEY);
        zapScanner.clear(); //Start a new session
        zapSpider = (Spider)zapScanner;
        logger.info("Created client to ZAP API");
//      ZAPScanner.createZapProxyConfiguration();
    }

    public static void logAlerts(List<Alert> alerts) {
        for (Alert alert : alerts) {
            logger.info("Alert: "+alert.getAlert()+" at URL: "+alert.getUrl()+" Parameter: "+alert.getParam()+" CWE ID: "+alert.getCweId());
        }
    }

    /*
        Remove false positives, filter based on risk and reliability,
        Method to filter the generated alerts based on Risk and Confidence
     */
    public static List<Alert> filterAlerts(List<Alert> alerts) {
        List<Alert> filtered = new ArrayList<Alert>();
        for (Alert alert : alerts) {
            if (alert.getRisk().equals(Alert.Risk.High) && alert.getConfidence() != Alert.Confidence.Low) filtered.add(alert);
        }
        return filtered;
    }

    // Method to specify the strength for the ZAP Scanner as High, Medium, or Low
    public static void setAlertAndAttackStrength() {
        for (String policyName : policyNames) {
            String ids = enableZapPolicy(policyName);
            for (String id : ids.split(",")) {
                zapScanner.setScannerAlertThreshold(id,MEDIUM);
                zapScanner.setScannerAttackStrength(id,HIGH);
            }
        }
    }

    // Method to execute scan and log the progress
    public static void scanWithZap() {
        logger.info("Scanning...");
        zapScanner.scan(driver.getCurrentUrl());
        currentScanID = zapScanner.getLastScannerScanId();
        int complete = 0;
        while (complete < 100) {
            complete = zapScanner.getScanProgress(currentScanID);
            logger.info("Scan is " + complete + "% complete.");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Scanning done.");
    }

    /* Method to configure the ZAP Scanner for specified security policies and
       enable the scanner */
    private static String enableZapPolicy(String policyName) {
        String scannerIds = null;
        switch (policyName.toLowerCase()) {
            case "directory-browsing":
                scannerIds = "0";
                break;
            case "cross-site-scripting":
                scannerIds = "40012,40014,40016,40017";
                break;
            case "sql-injection":
                scannerIds = "40018";
                break;
            case "path-traversal":
                scannerIds = "6";
                break;
            case "remote-file-inclusion":
                scannerIds = "7";
                break;
            case "server-side-include":
                scannerIds = "40009";
                break;
            case "script-active-scan-rules":
                scannerIds = "50000";
                break;
            case "server-side-code-injection":
                scannerIds = "90019";
                break;
            case "remote-os-command-injection":
                scannerIds = "90020";
                break;
            case "external-redirect":
                scannerIds = "20019";
                break;
            case "crlf-injection":
                scannerIds = "40003";
                break;
            case "source-code-disclosure":
                scannerIds = "42,10045,20017";
                break;
            case "shell-shock":
                scannerIds = "10048";
                break;
            case "remote-code-execution":
                scannerIds = "20018";
                break;
            case "ldap-injection":
                scannerIds = "40015";
                break;
            case "xpath-injection":
                scannerIds = "90021";
                break;
            case "xml-external-entity":
                scannerIds = "90023";
                break;
            case "padding-oracle":
                scannerIds = "90024";
                break;
            case "el-injection":
                scannerIds = "90025";
                break;
            case "insecure-http-methods":
                scannerIds = "90028";
                break;
            case "parameter-pollution":
                scannerIds = "20014";
                break;
            default : throw new RuntimeException("No policy found for: "+policyName);
        }
        if (scannerIds == null) throw new RuntimeException("No matching policy found for: " + policyName);
        zapScanner.setEnableScanners(scannerIds, true);
        return scannerIds;
    }

    // Create ZAP proxy by specifying proxy host and proxy port
    public static Proxy createZapProxyConfiguration() {
        Proxy zapProxy = new Proxy();
        zapProxy.setHttpProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
        zapProxy.setSslProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
        return zapProxy;
    }

    // Method to configure spider settings, execute ZAP spider, log the progress and found URLs
    public static void spiderWithZap() {
        zapSpider.excludeFromSpider(LOGOUT_URL);
        zapSpider.setThreadCount(5);
        zapSpider.setMaxDepth(5);
        zapSpider.setPostForms(false);
        zapSpider.spider(BASE_URL);
        int spiderID = zapSpider.getLastSpiderScanId();
        int complete  = 0;
        while (complete < 100) {
            complete = zapSpider.getSpiderProgress(spiderID);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (String url : zapSpider.getSpiderResults(spiderID)) {
            logger.info("Found URL: "+url);
        }
    }

    public static void getReports() {

        try {
            // calling core apis to get html report in bytes.
            byte[] bytes =  zapScanner.getHtmlReport();
            String str = new String(bytes, StandardCharsets.UTF_8);
            fileInfo = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
            fileLocation = System.getProperty("user.dir") + "/logs";
            fileName = "OWASP ZAP Report";
            File newTextFile = new File(fileLocation + File.separator + fileName + "-" + fileInfo + ".html");
            FileWriter fw = new FileWriter(newTextFile);
            fw.write(str);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
