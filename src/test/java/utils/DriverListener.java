package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static base.tests.BasicTest.driver;

public class DriverListener implements WebDriverEventListener {
    private long interval;
    private final int count;
    private final String color;

    public static ArrayList<String> errorTrace = new ArrayList<>();
    public static Field[] LAST_PUBLIC_FIELDS;
    public static String PC_NAME = "";
    public static String OS = "";
    public static String TEST_NAME = "";
    public static String TEST_CLASS = "";

    public DriverListener(String color, int count, long interval, TimeUnit unit) {
        this.color = color;
        this.count = count;
        this.interval = TimeUnit.MILLISECONDS.convert(Math.max(0, interval), unit);
    }

    /**
     * Method for getting class from StackTrace
     *
     * @param - String that's contains class name
     * @return - Class
     */
    public Class getPageObjectRunningClass() {
        Class pageClass = null;
        Throwable t = new Throwable();
        StackTraceElement trace[] = t.getStackTrace();
        String list[] = new File("src/test/java").list();
        try {
            for (StackTraceElement tr : trace) {
                String packageName = String.valueOf(tr).split("\\.")[0];
                if (Arrays.toString(list).contains(packageName)) {
                    pageClass = Class.forName(tr.getClassName());
                    if (!pageClass.equals(this.getClass()) && pageClass.getDeclaredAnnotation(Test.class) == null) {
                        return pageClass;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageClass;
    }

    /**
     * Method find WebElement Name by xpath
     *
     * @param webElement - get WebElement
     * @return - String WebElement Name
     */
    public String getWebElementName(WebElement webElement) {
        String[] webElementXPATH = String.valueOf(webElement).split("] -> ");
        String element = webElementXPATH[1].substring(webElementXPATH[1].indexOf(":") + 2, webElementXPATH[1].length() - 1).replace("]]", "]");
        LAST_PUBLIC_FIELDS = getPageObjectRunningClass().getDeclaredFields();
        for (Field field : LAST_PUBLIC_FIELDS) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (Arrays.toString(annotations).contains(element)) {
                return "[" + field.getName().replace("_", " ") + "]";
            }
        }
        return "[" + webElement.getText() + "]";
    }

    /**
     * Method for paint element
     *
     * @param color   -  String color
     * @param element - Current WebElement
     * @param js      - JS executor
     */
    private void changeColor(String color, WebElement element, JavascriptExecutor js) {
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(interval);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method for light click
     *
     * @param element - get WebElement for light up
     */
    private void flash(WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("scrollTo(0, " + (element.getLocation()
                .getY() - driver.manage().window().getSize().getHeight() / 2) + ")"); //Super scroll
        String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < count; i++) {
            changeColor(color, element, js);
            changeColor(bgcolor, element, js);
        }
    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {
    }

    public static String getOS() {
        return System.getProperty("os.name");
    }

    public static String getPcName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void afterNavigateTo(String s, WebDriver webDriver) {
    }

    public void beforeNavigateBack(WebDriver webDriver) {
    }

    public void afterNavigateBack(WebDriver webDriver) {
    }

    public void beforeNavigateForward(WebDriver webDriver) {
    }

    public void afterNavigateForward(WebDriver webDriver) {
    }

    public void beforeNavigateRefresh(WebDriver webDriver) {
    }

    public void afterNavigateRefresh(WebDriver webDriver) {
    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        flash(webElement);
        if (webElement.getText().length() == 0) {
            TestLogger.info("Click on " + getWebElementName(webElement));
        } else if (webElement.getText().length() != 0) {
            TestLogger.info("Click on " + getWebElementName(webElement) + " with text \"" + webElement.getText().replaceAll("\\n", " ") + "\"");
        }
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {
        flash(webElement);
    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {
        if (webElement.getAttribute("value").length() != 0) {
            TestLogger.info("Input \"" + webElement.getAttribute("value") + "\" in " + getWebElementName(webElement));
        } else if (webElement.getAttribute("value").length() == 0) {
            TestLogger.info("Clear " + getWebElementName(webElement));
        }
    }

    public void beforeScript(String s, WebDriver webDriver) {
    }

    public void afterScript(String s, WebDriver webDriver) {
    }

    public void onException(Throwable throwable, WebDriver webDriver) {
    }
}
