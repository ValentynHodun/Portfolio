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

    @FindBy(xpath = "//span[.='Электроника']")
    private WebElement ELECTRONIC_PRODUCT_TYPE;

    @FindBy(xpath = "//span[.='Телефоны и аксессуары']")
    private WebElement PHONE_AND_ACCESSORIES_CATEGORY;

    @FindBy(xpath = "//*[@id='geo-suggestions']/a")
    private WebElement CLOSE_GEO_POPUP;

    @FindBy(xpath = "//*[@id='geo-suggestions']")
    private WebElement GEO_POPUP;

    @FindBy(xpath = "//span[@data-default-label='Цена до']")
    private WebElement MAX_PRICE_INPUT_FIELD;

    @FindBy(xpath = "//*[@id='search-submit']")
    private WebElement SEARCH_SUBMIT_BUTTON;

    @FindBy(xpath = "//tr[@class='wrap']")
    private List<WebElement> SEARCH_RESULT_LIST;

    @FindBy(xpath = "//p[@class='price']")
    private List<WebElement> ITEMS_PRICES;

    private static final String ITEM_PRICE = "//p[@class='price']";


    public SearchPage(EventFiringWebDriver driver) {
        super(driver);
    }

    public void selectElectronicProductType(){
        try{
            ELECTRONIC_PRODUCT_TYPE.click();
        }catch(Exception e){
            Assert.assertTrue(false, "Can't select Electronic product type, occu error\n" + e.getMessage());
        }
    }

    public void selectPhonesProductCategory(){
        try{
            PHONE_AND_ACCESSORIES_CATEGORY.click();
        }catch (Exception e){
            Assert.assertTrue(false, "Can't select Phone and accessories category, occur error\n" + e.getMessage());
        }

    }

    public void inputMaxPrice(String price){
        try{
            MAX_PRICE_INPUT_FIELD.sendKeys(price);
        }catch (Exception e){
            Assert.assertTrue(false, "Can't input price to Max price field, occur error\n" + e.getMessage());
        }
    }

    public void closeGeoPopupIfItExist(){
        try{
            if (GEO_POPUP.isDisplayed()){
                CLOSE_GEO_POPUP.click();
            }
        }catch (Exception e){
            Assert.assertTrue(false, "Can't close Geo popup, occur error\n" + e.getMessage());
        }
    }

    public void clickSearchButton(){
        try{
            SEARCH_SUBMIT_BUTTON.click();
        }catch (Exception e){
            Assert.assertTrue(false, "Can't click Search button, occur error\n" + e.getMessage());
        }
    }

    public int getSearchResultsItemsCount(){
        try {
            int count = SEARCH_RESULT_LIST.size();
            TestLogger.info("Displayed " + count + " item(s)");
            return count;
        }catch (Exception e){
            Assert.assertTrue(false, "Can't return Search list count, occur error\n" + e.getMessage());
        }
        return 0;
    }

    public int getFirstItemPrice(){
        try {
            String price = SEARCH_RESULT_LIST.get(0).findElement(By.xpath(ITEM_PRICE)).getText().split(" ")[0];
            int result = Integer.parseInt(price);
            return result;
        }catch (Exception e){
            Assert.assertTrue(false, "Can't click Search button, occur error\n" + e.getMessage());
        }
        return 0;
    }
}
