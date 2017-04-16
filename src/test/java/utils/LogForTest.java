package utils;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public class LogForTest {

    private static final Logger LOGGER = Logger.getLogger(LogForTest.class);
    private static final String INFO_LOG = "INFO: \"%s\"" + '\n';
    private static final String ERROR_LOG = "ERROR: \"%s\" !" + '\n';

    public static String error(String message) {
        LOGGER.error(String.format(ERROR_LOG, message));
        Reporter.log(String.format(ERROR_LOG, message));
        return String.format(ERROR_LOG, message);
    }

    public static String info(String message) {
        LOGGER.info(String.format(INFO_LOG, message));
        Reporter.log(String.format(INFO_LOG, message));
        return String.format(INFO_LOG, message);
    }

}
