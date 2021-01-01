package BasePackage;

import Utilities.ZAPScanner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static Loggers.PrefsLogger.getChromeLoggingPrefs;
import static Loggers.PrefsLogger.logPrefs;
import static Utilities.ZAPScanner.ZAP_PROXYHOST;
import static Utilities.ZAPScanner.ZAP_PROXYPORT;

public class BrowserOptions {

    private static Proxy seleniumProxy,zapProxy;
    private static String ZAP_Proxy = ZAP_PROXYHOST+":"+ZAP_PROXYPORT;
    /*private static String ZAP_Proxy = ZAP_HOST+":"+ZAP_PORT;*/

    public static ChromeOptions chromeOptions() {

        WebDriverManager.chromedriver().clearPreferences();
        WebDriverManager.chromedriver().clearCache();
        WebDriverManager.chromedriver().setup();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//      System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        ZAPScanner.zapSetup(); //ZAPScanner
        getChromeLoggingPrefs();
        zapProxy = new Proxy();
        zapProxy.setHttpProxy(ZAP_Proxy).setFtpProxy(ZAP_Proxy).setSslProxy(ZAP_Proxy);
        ChromeOptions options = new ChromeOptions();
        options.merge(capabilities);
//      options.addArguments("--verbose");
//      options.addArguments("--window-size=1980,1080");
        options.setAcceptInsecureCerts(true);
        options.addArguments("--incognito");
//      options.addArguments("--start-fullscreen");
        options.addArguments("--start-maximized");
//      options.addArguments("--headless");
        options.setHeadless(false);
        options.addArguments("--silent");
        options.addArguments("--disable-web-security");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--allow-insecure-localhost");
//      options.addArguments("--no-sandbox");
//      options.addArguments("--disable-gpu");
        options.setCapability(CapabilityType.PROXY,zapProxy);
        return options;
    }
    public static FirefoxOptions firefoxOptions() {
        WebDriverManager.firefoxdriver().clearPreferences();
        WebDriverManager.firefoxdriver().clearCache();
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        options.setHeadless(false);
        return options;
    }

    public static EdgeOptions edgeOptions() {
        WebDriverManager.edgedriver().clearPreferences();
        WebDriverManager.edgedriver().clearCache();
        WebDriverManager.edgedriver().forceDownload().setup();
        EdgeOptions options = new EdgeOptions();
        return options;
    }

    public static InternetExplorerOptions internetExplorerOptions() {
        WebDriverManager.iedriver().clearPreferences();
        WebDriverManager.iedriver().clearCache();
        WebDriverManager.iedriver().forceDownload().setup();
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.introduceFlakinessByIgnoringSecurityDomains();
        options.ignoreZoomSettings();
        options.requireWindowFocus();
        return options;
    }
}
