package utils;

import org.apache.log4j.Logger;
import org.testng.Reporter;

import java.util.ArrayList;

import static base.tests.BasicTest.testStepCount;

public class TestLogger {
    private static ArrayList<String> errorLog;
    private static ArrayList<String> infoLog;
    private static ArrayList<String> headerLog;
    private static ArrayList<String> warningLog;

    public static void resetLogLists(){
        headerLog = new ArrayList<>();
        infoLog = new ArrayList<>();
        errorLog = new ArrayList<>();
        warningLog = new ArrayList<>();
    }

    public static ArrayList<String> getErrorLog() {
        return errorLog;
    }

    public static ArrayList<String> getInfoLog() {
        return infoLog;
    }

    public static ArrayList<String> getHeaderLog() {
        return headerLog;
    }

    public static ArrayList<String> getWarningLog() {
        return warningLog;
    }

    private static final Logger LOGGER = Logger.getLogger(TestLogger.class);
    private static final String INFO_LOG = "INFO: %s";
    private static final String ERROR_LOG = "ERROR: %s !";
    private static final String WARN_LOG = "WARING: %s !";

    public static String header(String message){
        LOGGER.info(String.format(INFO_LOG, message));
        Reporter.log(String.format(INFO_LOG, message));
        headerLog.add(message  + "\n");
        return String.format(INFO_LOG, message);
    }

    public static String info(String message) {
        LOGGER.info(String.format(INFO_LOG, testStepCount + ") " + message));
        Reporter.log(String.format(INFO_LOG, testStepCount + ") " + message));
        infoLog.add(testStepCount + ") " + message + "\n");
        testStepCount++;
        return String.format(INFO_LOG, testStepCount + ") " + message);
    }

    public static String error(String message) {
        LOGGER.error(String.format(ERROR_LOG, message));
        Reporter.log(String.format(ERROR_LOG, message));
        errorLog.add(message + "\n");
        return String.format(ERROR_LOG, message);
    }

    public static String warning(String message){
        LOGGER.info(String.format(WARN_LOG, message));
        Reporter.log(String.format(WARN_LOG, message));
        warningLog.add(message  + "\n");
        return String.format(WARN_LOG, message);
    }
}
