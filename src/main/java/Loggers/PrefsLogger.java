package Loggers;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import static Utilities.Utils.*;

public class PrefsLogger {

    public static LoggingPreferences logPrefs;

    public static LoggingPreferences getChromeLoggingPrefs() {
        // System.setProperty("webdriver.chrome.logfile", "./chrome-driver.log");
        fileInfo = new SimpleDateFormat("dd.MM.yyyy_HH.mm.ss").format(new Date());
        fileLocation = System.getProperty("user.dir") + File.separator + "logs";
        fileName="ChromeDriver-";
        System.setProperty("webdriver.chrome.logfile", fileLocation + File.separator + fileName + fileInfo + ".log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
//      logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
//      logPrefs.enable(LogType.CLIENT, Level.ALL);
//      logPrefs.enable(LogType.DRIVER, Level.ALL);
//      logPrefs.enable(LogType.PROFILER, Level.ALL);
//      logPrefs.enable(LogType.SERVER, Level.ALL);
//      options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        return logPrefs;
    }
}
