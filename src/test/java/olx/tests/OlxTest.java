package olx.tests;

import base.tests.BasicTest;
import olx.pages.AddNewAdvertisementPage;
import olx.pages.HomePage;
import olx.pages.MyAccountPage;
import olx.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.PropertyLoader;
import utils.TestLogger;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OlxTest extends BasicTest {

    public static final String EMAIL = PropertyLoader.loadProperty("email");
    public static final String PASSWORD = PropertyLoader.loadProperty("password");

    HomePage home;
    SearchPage searchPage;
    MyAccountPage myAccountPage;
    AddNewAdvertisementPage addNewAdvertisementPage;

    private String generateEmail() {
        String emailTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        return emailTime + "@ukr.net";
    }

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

    @Test
    public void registerNewCustomer() {
        gotoPage("https://www.olx.ua/");
        home.closeCookiesPopup();
        home.clickPostAd();
        myAccountPage.clickRegisterNewCustomerTab();
        String email = generateEmail();
        myAccountPage.inputEmailNewCustomer(email);
        myAccountPage.inputPasswordNewCustomer(PASSWORD);
        myAccountPage.clickCheckAcceptCheckbox();
        myAccountPage.clickRegisterNewCustomerButton();
        Assert.assertTrue(myAccountPage.isSuccessRegisterNEwCustomer(), "New account not register");
    }

    @Test
    public void searchTest() {
        gotoPage("https://www.olx.ua/");
        searchPage.selectElectronicProductType();
        searchPage.selectPhonesProductCategory();
        searchPage.selectFirstGeoLocation();
        searchPage.clickSearchButton();
        Assert.assertFalse(searchPage.getSearchResultsItemsCount() == 0, "Search results list is empty");
    }

    @Override
    public void initPages() {
        home = new HomePage(driver);
        searchPage = new SearchPage(driver);
        myAccountPage = new MyAccountPage(driver);
        addNewAdvertisementPage = new AddNewAdvertisementPage(driver);
    }
}
