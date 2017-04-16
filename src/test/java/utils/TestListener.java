package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static base.tests.BasicTest.driver;
import static utils.DriverListener.*;
import static utils.TestLogger.*;

public class TestListener extends TestListenerAdapter {
    public static ArrayList<File> screenNamesList = new ArrayList<>();
    public static String date = "";

    public static void screenForTestFailure() {
        String filePath = "";
        date = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date());
        if (System.getProperty("os.name").equals("Linux")){
            filePath = "/home/geser/IdeaProjects/Automation/Screenshot/" + TEST_CLASS + "/"
                    + date + TEST_NAME + ".png";
        }
        if (System.getProperty("os.name").contains("Windows")){
            filePath = "C:\\Automation\\Screenshot\\" + TEST_CLASS + "\\"
                    + date + TEST_NAME + ".png";
        }

        try {
            File scrFile = driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(filePath));
            screenNamesList.add(new File(filePath));
        } catch (IOException e) {
            TestLogger.error("Can't get screenshot" + "\n");
            e.printStackTrace();
        }
    }

    @Override
    public void onTestStart(ITestResult tr) {
        resetLogLists();
        super.onTestStart(tr);
        String[] tempArray = tr.getTestClass().getName().split("\\.");
        String className = tempArray[tempArray.length - 1];
        String testName = tr.getName();
        TEST_CLASS = className;
        TEST_NAME = testName;
        OS = getOS();
        PC_NAME = getPcName();

        TestLogger.header("Test: " + TEST_NAME);
        TestLogger.header("CLass: " + TEST_CLASS);
        TestLogger.header("OS: " + OS);
        TestLogger.header("PC name: " + PC_NAME);
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        TestLogger.info("Test \"" + tr.getName() + "\" successfully passed");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        TestLogger.info("Test \"" + tr.getName() + "\" skipped");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        TestLogger.info("Test: \"" + tr.getName() + "\" is failed");
        TestLogger.error(String.valueOf(tr.getThrowable().getMessage()));
        String currentURL = driver.getCurrentUrl();
        screenForTestFailure();

        /*GET LOG INFO FOR EMAIL CONTENT*/
        ArrayList<String> testLog = new ArrayList<>();
        testLog.add("<head><style>\n" +
                "   hr {\n" +
                "    border: none;\n" +
                "    background-color: red;\n" +
                "    color: red;\n" +
                "    height: 2px;\n" +
                "   }\n" +
                "  </style></head>");
        testLog.add("<h1>TEST CASE</h1>");
        testLog.add("<strong>ADDITIONAL INFO</strong><br>");
        testLog.add("<hr></hr>");
        testLog.addAll(getHeaderLog());
        testLog.add("<strong>URL: </strong>" + currentURL + "<br><br>");
        testLog.add("<strong>STEPS</strong><br>");
        testLog.add("<hr></hr>");
        testLog.addAll(getInfoLog());
        testLog.add("<br><strong>CAUSED BY:</strong><br>");
        testLog.add("<hr></hr>");
        testLog.addAll(getErrorLog());
        StringBuilder content = new StringBuilder();
        testLog.forEach(string -> content.append(string));

        try {
            sendEmail(TEST_CLASS + ": " +
                            TEST_NAME + " test is failed " + " " + date,
                    content.toString(), screenNamesList);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("can't send email  \n" + e.getMessage());
        } finally {
            screenNamesList = new ArrayList<>();
            errorTrace = new ArrayList<>();
        }
        super.onTestFailure(tr);
    }

    private void sendEmail(String subjectMessage, String content, ArrayList<File> screenFiles) {
        System.out.println("try send email");
        EmailReporter email = new EmailReporter();
        try {
            ArrayList<String> rec = new ArrayList<>();
            rec.add("s.konoplyaniy@gmail.com");
            rec.add("g_v_s2005@ukr.net");
            email.execute(rec, subjectMessage, content, screenFiles);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("can't send email  \n" + e.getMessage());
        }
    }
}
