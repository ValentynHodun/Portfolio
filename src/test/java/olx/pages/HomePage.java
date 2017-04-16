package olx.pages;

import base.page.BasePage;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import utils.TestLogger;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id='headerSearch']")
    private WebElement SEARCH_FIELD;

    @FindBy(xpath = "//*[@id='submit-searchmain']")
    private WebElement SEARCH_BUTTON;

    @FindBy(xpath = "//*[@id='postNewAdLink']")
    private WebElement POST_AD;

    @FindBy(xpath = "//a[@class='cookiesBarClose abs close']")
    private WebElement CLOSE_COOKIES_POPUP;

    public HomePage(EventFiringWebDriver driver) {
        super(driver);
    }

    public void closeCookiesPopup() {
        if (CLOSE_COOKIES_POPUP.isDisplayed()) {
            try{
                CLOSE_COOKIES_POPUP.click();
            }catch (StaleElementReferenceException e){
                TestLogger.info("Can't close cookies popup");
                TestLogger.warning("closeCookiesPopup method throw StaleElementReferenceException:\n"
                        + e.getMessage());
            }
        }
    }

    public void inputSearchField(String search){
        try {
            SEARCH_FIELD.sendKeys(search);
        }catch (Exception e){
            Assert.assertTrue(false, "While input text to search field, occur error\n" + e.getMessage());
        }
    }

    public void clickSearchButton(){
        try {
            SEARCH_BUTTON.click();
        }catch(Exception e){
            Assert.assertTrue(false, "Can't click Search button, occur error\n" + e.getMessage());
        }
    }

    public void clickPostAd() {
        try {
            POST_AD.click();
        }catch(Exception e){
            Assert.assertTrue(false, "Can't click Post advertisement, occur error\n" + e.getMessage());
        }
    }
}
