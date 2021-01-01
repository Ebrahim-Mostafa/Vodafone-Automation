package BasePackage;

import Loggers.BrowserConsoleLogger;
import Recorders.ATUTestRecord;
import Utilities.TimeUtils;
import Utilities.WebEventListener;
import Utilities.ZAPScanner;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileNotFoundException;

import static Loggers.Log4JLogger.logger;
import static Utilities.ZAPScanner.zapScanner;

public class BaseTest {

    public static WebDriver driver;
    private static EventFiringWebDriver firingWebDriver;
    private static WebEventListener eventListener;


    @Parameters(value = {"Browser", "ENV", "URL"})
    @BeforeSuite(alwaysRun = true)
    public void setup(String browserName, @Optional String environment, String url) throws FileNotFoundException, ATUTestRecorderException {
        logger.info("****************************** Starting test cases execution on " + environment + " environment " + "*****************************************");
        DriverFactory.setDriver(browserName);
        TimeUtils.pageLoadTimeout(150);
        TimeUtils.explicitWait(50);
        TimeUtils.implicitlyWait(50);
        TimeUtils.setScriptTimeout(150);
        DriverFactory.getDriver().manage().deleteAllCookies();
        driver = DriverFactory.getDriver();
        firingWebDriver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        firingWebDriver.register(eventListener);
        driver = firingWebDriver;
        driver.get(url);
        BrowserConsoleLogger.getBrowserConsoleLogs();
        ATUTestRecord.startScreenRecorder("VodafoneAutomationTask");
        // --ZAPScanner--
        ZAPScanner.spiderWithZap();
        ZAPScanner.setAlertAndAttackStrength();
        zapScanner.setEnablePassiveScan(true);
        ZAPScanner.scanWithZap();
//      List<Alert> alerts = filterAlerts(zapScanner.getAlerts());
//      logAlerts(alerts);
//      assertThat(alerts.size(), equalTo(0));

    }

    @AfterMethod
    @Parameters(value = {"URL"})
    public void consoleNotificationTestStart(ITestResult result, String url) throws Throwable {
        //Prepare the Sysout log to show which tests are being run and the environment against...
        System.out.println("//---------------------------");
        System.out.println("RUNNING IN ENVIRONMENT: " + url);
        System.out.println("STARTING TEST Class Name : " + result.getTestClass().getName() + " :::::: " + "MethodName:" + result.getMethod().getMethodName() + ".");
        System.out.println("//---------------------------");
    }


    @AfterSuite(alwaysRun = true)
    public synchronized void tearDown() throws ATUTestRecorderException {
        logger.info("****************************** Browser is closed *****************************************");
        ZAPScanner.getReports();
        ATUTestRecord.stopScreenRecorder();
        DriverFactory.getDriver().quit();
    }
}
