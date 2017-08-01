package base.tests;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import utils.DriverListener;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public abstract class BasicTest {
    public static EventFiringWebDriver driver;
    public static int testStepCount = 1;

    /**
     * For run test you need download chromedriver.exe
     * for Linux/Ubuntu OS put it to path "/home/username/IdeaProjects/"
     * for Window OS - "C:\\Automation\\chromedriver\\chromedriver.exe"
     */
    @BeforeSuite
    public void initEnvironment() {
        if (System.getProperty("os.name").equals("Linux")) {
            System.setProperty("webdriver.chrome.driver", "/home/geser/IdeaProjects/chromedriver"); //Chrome driver linux
        }
        if (System.getProperty("os.name").contains("Windows")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Automation\\chromedriver\\chromedriver.exe"); //Chrome driver windows
        }
        WebDriver webDriver = new ChromeDriver();
        driver = new EventFiringWebDriver(webDriver);
        driver.register(new DriverListener("#FFFF00 ", 1, 1, TimeUnit.MILLISECONDS));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @BeforeMethod
    public void initTest() {
        testStepCount = 1;
    }

    public abstract void initPages();

    public void gotoPage(String url) {
        if (driver == null) {
            initEnvironment();
        }
        try {
            if (!driver.getCurrentUrl().equals(url)) {
                driver.get(url);
            }
            initPages();
        } catch (TimeoutException e) {
            initPages();
        }
    }

    @AfterSuite(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null)
            driver.quit();
    }

}
