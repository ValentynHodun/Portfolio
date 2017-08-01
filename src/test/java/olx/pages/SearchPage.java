package olx.pages;

import base.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import utils.TestLogger;

import java.util.List;

public class SearchPage extends BasePage {

    private static final String ITEM_PRICE = "//p[@class='price']";
    @FindBy(xpath = "//span[.='Электроника']")
    private WebElement ELECTRONIC_PRODUCT_TYPE;
    @FindBy(xpath = "//span[.='Телефоны и аксессуары']")
    private WebElement PHONE_AND_ACCESSORIES_CATEGORY;
    @FindBy(xpath = "//*[@id='geo-suggestions']/a")
    private WebElement CLOSE_GEO_POPUP;
    @FindBy(xpath = "//*[@id='geo-suggestions']")
    private WebElement GEO_POPUP;
    @FindBy(xpath = "//*[@id='geo-suggestions-options']/a[1]")
    private WebElement GEO_POPUP_KIEV_LOCATION;
    @FindBy(xpath = "//*[@id='search-submit']")
    private WebElement SEARCH_SUBMIT_BUTTON;
    @FindBy(xpath = "//tr[@class='wrap']")
    private List<WebElement> SEARCH_RESULT_LIST;
    @FindBy(xpath = "//p[@class='price']")
    private List<WebElement> ITEMS_PRICES;


    public SearchPage(EventFiringWebDriver driver) {
        super(driver);
    }

    public void selectElectronicProductType() {
        try {
            ELECTRONIC_PRODUCT_TYPE.click();
        } catch (Exception e) {
            Assert.assertTrue(false, "Can't select Electronic product type, occur error\n" + e.getMessage());
        }
    }

    public void selectPhonesProductCategory() {
        try {
            PHONE_AND_ACCESSORIES_CATEGORY.click();
        } catch (Exception e) {
            Assert.assertTrue(false, "Can't select Phone and accessories category, occur error\n" + e.getMessage());
        }
    }

    public void selectFirstGeoLocation() {
        try {
            GEO_POPUP_KIEV_LOCATION.click();
        } catch (Exception e) {
            Assert.assertTrue(false, "Can't select geo location, occur error\n" + e.getMessage());
        }
    }

    public void clickSearchButton() {
        try {
            SEARCH_SUBMIT_BUTTON.click();
        } catch (Exception e) {
            Assert.assertTrue(false, "Can't click Search button, occur error\n" + e.getMessage());
        }
    }

    public int getSearchResultsItemsCount() {
        try {
            int count = SEARCH_RESULT_LIST.size();
            TestLogger.info("Displayed " + count + " item(s)");
            return count;
        } catch (Exception e) {
            Assert.assertTrue(false, "Can't return Search list count, occur error\n" + e.getMessage());
        }
        return 0;
    }

}
