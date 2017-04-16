package olx.tests;

import base.tests.BasicTest;
import olx.pages.AddNewAdvertisementPage;
import olx.pages.HomePage;
import olx.pages.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyLoader;

public class OlxTest extends BasicTest {

    public static final String EMAIL = PropertyLoader.loadProperty("email");
    public static final String PASSWORD = PropertyLoader.loadProperty("password");

    HomePage home;
    MyAccountPage myAccountPage;
    AddNewAdvertisementPage addNewAdvertisementPage;

    @Test
    public void postNewAd() {
        gotoPage("https://www.olx.ua/");        
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
        addNewAdvertisementPage.inputDescription("this is test description");
        addNewAdvertisementPage.inputMobilePhone("+380931231212");
        addNewAdvertisementPage.inputLocation("Киев", "Киев, Киевская область");
        addNewAdvertisementPage.clickReview();
        Assert.assertTrue(addNewAdvertisementPage.reviewPopup(), "Reviews not display!");
    }

    @Override
    public void initPages() {
        home = new HomePage(driver);
        myAccountPage = new MyAccountPage(driver);
        addNewAdvertisementPage = new AddNewAdvertisementPage(driver);
    }
}
