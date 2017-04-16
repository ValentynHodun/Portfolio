package olx.pages;

import base.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

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

    @FindBy(xpath = "//strong[contains(text(), 'Сейчас')]")
    private WebElement SUCCESS_MESSAGE;

    public MyAccountPage(EventFiringWebDriver driver) {
        super(driver);
    }
    /*Register New Customer*/
    public void inputEmailNewCustomer(String email){
        try {
            EMAIL_FIELD_REGISTER_NEW_CUSTOMER.sendKeys(email);
        }catch (Exception e){
            Assert.assertTrue(false, "While input text to email field, occur error\n" + e.getMessage());
        }
    }

    public void inputPasswordNewCustomer(String password){
        try {
            PASSWORD_FIELD_REGISTER_NEW_CUSTOMER.sendKeys(password);
        }catch (Exception e){
            Assert.assertTrue(false, "While input text to password field, occur error\n" + e.getMessage());
        }
    }

    public void clickCheckAcceptCheckbox(){
        try {
            CONFIRM_CHECKBOX_REGISTER_NEW_CUSTOMER.click();
        }catch (Exception e){
            Assert.assertTrue(false, "While click in 'Confirm' checkbox, occur error\n" + e.getMessage());
        }
    }

    public void clickRegisterNewCustomerButton(){
        try {
            REGISTER_NEW_CUSTOMER_BUTTON.click();
        } catch (Exception e){
            Assert.assertTrue(false, "While click in 'Register new Customer' button, occur error\n" + e.getMessage());
        }
    }

    public boolean isSuccessRegisterNEwCustomer(){
        waitForElement(SUCCESS_MESSAGE);
        return SUCCESS_MESSAGE.isDisplayed();
    }

    public void clickRegisterNewCustomerTab(){
        waitForElement(REGISTER_CUSTOMER_TAB);
        try {
            REGISTER_CUSTOMER_TAB.click();
        }catch (Exception e){
            Assert.assertTrue(false, "While click in 'Register new Customer' tab, occur error\n" + e.getMessage());
        }
    }

    /*Sign in*/
    public void inputEmail(String email) {
        waitForElement(EMAIL_FIELD);
        try {
            EMAIL_FIELD.sendKeys(email);
        }catch (Exception e){
            Assert.assertTrue(false, "While input text to email field, occur error\n" + e.getMessage());
        }
    }

    public void inputPassword(String password) {
        try {
            PASSWORD_FIELD.sendKeys(password);
        }catch (Exception e){
            Assert.assertTrue(false, "While input text to password field, occur error\n" + e.getMessage());
        }
    }

    public void clickLogInButton() {
        try {
            LOGIN_BUTTON.click();
        } catch (Exception e){
            Assert.assertTrue(false, "While click in 'Log in' button, occur error\n" + e.getMessage());
        }
    }

    public void clickLoginTab(){
        try {
            SIGN_IN_TAB.click();
        }catch (Exception e){
            Assert.assertTrue(false, "While click in 'Log in' tab, occur error\n" + e.getMessage());
        }
    }
}
