package olx.pages;

import base.page.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.List;

public class AddNewAdvertisementPage extends BasePage {

    @FindBy(xpath = "//*[@id='add-title']")
    private WebElement HEADER;

    @FindBy(xpath = "//*[@id='targetrenderSelect1-0']")
    private WebElement RUBRIC;

    @FindBy(xpath = "//strong[contains(text(), 'Электроника')]")
    private WebElement CHOOSE_ELECTRONICS;

    @FindBy(xpath = "//span[contains(text(), 'Телефоны и аксессуары')]")
    private WebElement CHOOSE_TELEPHONE;

    @FindBy(xpath = "//span[contains(text(), 'Сим-карты / тарифы / номера')]")
    private WebElement CHOOSE_SIM_CARD;

    @FindBy(xpath = "//*[@id='targetid_private_business']")
    private WebElement PRIVATE_PERSON_CLICK;

    @FindBy(xpath = "//*[@id='targetid_private_business']/dd/ul/li[2]/a")
    private WebElement CHOOSE_PRIVATE_PERSON;

    @FindBy(xpath = "//input[@name = 'data[param_price][1]']")
    private WebElement PRICE;

    @FindBy(xpath = "//*[@id='add-description']")
    private WebElement DESCRIPTION;

    @FindBy(xpath = "//*[@id='mapAddress']")
    private WebElement LOCATION;

    @FindBy(xpath = "//*[@id='autosuggest-geo-ul']/li")
    private List<WebElement> LOCATION_LIST;

    @FindBy(xpath = "//*[@id='add-person']")
    private WebElement CONTACT_PERSON;

    @FindBy(xpath = "//*[@id='add-phone']")
    private WebElement PHONE_NUMBER;

    @FindBy(xpath = "//*[@id='preview-link']/span")
    private WebElement REVIEW_BUTTON;

    @FindBy(xpath = "//p[contains(text(), 'Так будет выглядеть ваше объявление')]")
    private WebElement REVIEW_POPUP;


    public AddNewAdvertisementPage(EventFiringWebDriver driver) {
        super(driver);
    }

    public void inputHeader(String header) {
        HEADER.sendKeys(header);
    }

    public void clickRubric() {
        RUBRIC.click();
    }

    public void chooseRubric() {
        waitForElement(CHOOSE_ELECTRONICS);
        CHOOSE_ELECTRONICS.click();
    }

    public void chooseFirstCategory() {
        waitForElement(CHOOSE_TELEPHONE);
        CHOOSE_TELEPHONE.click();
    }

    public void chooseFinalCategory() {
        waitForElement(CHOOSE_SIM_CARD);
        CHOOSE_SIM_CARD.click();
    }

    public void inputPrice(String price) {
        PRICE.sendKeys(price);
    }

    public void clickDropDownWithPrivatePerson() {
        PRIVATE_PERSON_CLICK.click();
    }

    public void choosePersonType() {
        waitForElement(CHOOSE_PRIVATE_PERSON);
        CHOOSE_PRIVATE_PERSON.click();
    }

    public void inputDescription(String description) {
        waitForElement(DESCRIPTION);
        DESCRIPTION.sendKeys(description);
    }

    public void inputLocation(String inputLocation, String chooseLocation) {
        LOCATION.sendKeys(inputLocation + Keys.ENTER);
        waitForElement(LOCATION_LIST.get(2));
        for (int i = 0; i < LOCATION_LIST.size(); i++) {
            if (LOCATION_LIST.get(i).getText().contains(chooseLocation)) {
                LOCATION_LIST.get(i).click();
                break;
            }
        }
    }

    public void inputContactPerson(String contactPerson) {
        CONTACT_PERSON.sendKeys(contactPerson);
    }

    public void inputMobilePhone(String mobilePhone) {
        PHONE_NUMBER.sendKeys(mobilePhone);
    }

    public void clickReview() {
        waitForElement(REVIEW_BUTTON);
        REVIEW_BUTTON.click();
    }

    public boolean reviewPopup() {
        return REVIEW_POPUP.isDisplayed();
    }

}
