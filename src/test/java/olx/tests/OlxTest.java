package olx.tests;

import base.tests.BasicTest;
import olx.pages.AddNewAdvertisementPage;
import olx.pages.HomePage;
import olx.pages.MyAccountPage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.LogForTest;
import utils.PropertyLoader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by valentyn on 2/22/2017.
 */
public class OlxTest extends BasicTest {

    public static final String EMAIL = PropertyLoader.loadProperty("email");
    public static final String PASSWORD = PropertyLoader.loadProperty("password");

    HomePage home;
    MyAccountPage myAccountPage;
    AddNewAdvertisementPage addNewAdvertisementPage;

    @Test
    public void postNewAd() {
        gotoPage("https://www.olx.ua/");
        LogForTest.info("Start postNewAd test");
        home.clickPostAd();
        home.closeCookiesPopup();
        myAccountPage.inputEmail(EMAIL);
        myAccountPage.inputPassword(PASSWORD);
        myAccountPage.clickLogInButton();
        addNewAdvertisementPage.inputHeader("Test rubric");
        addNewAdvertisementPage.clickRubric();
        addNewAdvertisementPage.chooseRubric();
        addNewAdvertisementPage.chooseFirstCategory();
        addNewAdvertisementPage.chooseFinalCategory();
        addNewAdvertisementPage.inputPrice("100");
        addNewAdvertisementPage.clickDropDownWithPrivatePerson();
        addNewAdvertisementPage.choosePersonType();
        addNewAdvertisementPage.inputContactPerson("Valentyn");
        addNewAdvertisementPage.inputDescription("this is test for glorium technologies");
        addNewAdvertisementPage.inputMobilePhone("+380931231212");
        addNewAdvertisementPage.inputLocation("Киев", "Киев, Киевская область");
        addNewAdvertisementPage.clickReview();
        Assert.assertTrue(addNewAdvertisementPage.reviewPopup(), "Reviews not display!");
        LogForTest.info("Finish postNewAd test");
    }

    @AfterMethod // If any test crashed - take screenshot and write ERROR message in log file
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String fn = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss ").format(new Date()).toString();
            FileUtils.copyFile(scrFile, new File("C:\\Automation\\Screenshot\\"
                    + fn + testResult.getName()  + ".jpg"));
            LogForTest.error("Test " + testResult.getName() + " ERROR !");
        }
    }

    @Override
    public void initPages() {
        home = new HomePage(driver);
        myAccountPage = new MyAccountPage(driver);
        addNewAdvertisementPage = new AddNewAdvertisementPage(driver);
    }
}
