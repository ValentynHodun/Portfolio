package olx.pages;

import base.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class MyAccountPage extends BasePage {

    @FindBy (xpath = "//*[@id='register_tab']")
    private WebElement REGISTER_CUSTOMER_TAB;

    @FindBy(xpath = "//*[@id='userEmailRegister']")
    private WebElement EMAIL_FIELD_REGISTER_NEW_CUSTOMER;

    @FindBy(xpath = "//*[@id='userPassRegister']")
    private WebElement PASSWORD_FIELD_REGISTER_NEW_CUSTOMER;

    @FindBy(xpath = "//label[@for='checkbox_accept-terms' and @relname]")
    private WebElement CONFIRM_CHECKBOX_REGISTER_NEW_CUSTOMER;

    @FindBy(xpath = "//*[@id='button_register']")
    private WebElement REGISTER_NEW_CUSTOMER_BUTTON;

    @FindBy(xpath = "//*[@id='login_tab']")
    private WebElement SIGN_IN_TAB;

    @FindBy(xpath = "//*[@id='userEmail']")
    private WebElement EMAIL_FIELD;

    @FindBy(xpath = "//*[@id='userPass']")
    private WebElement PASSWORD_FIELD;

    @FindBy(xpath = "//*[@id='se_userLogin']")
    private WebElement LOGIN_BUTTON;

    public MyAccountPage(EventFiringWebDriver driver) {
        super(driver);
    }
    /*Register New Customer*/
    public void inputEmailNewCustome(String email){
        EMAIL_FIELD_REGISTER_NEW_CUSTOMER.sendKeys(email);
    }

    public void inputPasswordNewCustomer(String password){
        PASSWORD_FIELD_REGISTER_NEW_CUSTOMER.sendKeys(password);
    }

    public void checkAcceptCheckbox(){
        CONFIRM_CHECKBOX_REGISTER_NEW_CUSTOMER.click();
    }

    public void clickRegisterNewCustomerButton(){
        REGISTER_NEW_CUSTOMER_BUTTON.click();
    }

    /*Sign in*/
    public void inputEmail(String email) {
        waitForElement(EMAIL_FIELD);
        EMAIL_FIELD.sendKeys(email);
    }

    public void inputPassword(String password) {
        PASSWORD_FIELD.sendKeys(password);
    }

    public void clickLogInButton() {
        LOGIN_BUTTON.click();
    }
}
