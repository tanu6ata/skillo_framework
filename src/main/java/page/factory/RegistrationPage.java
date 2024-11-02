package page.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class RegistrationPage {

    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/register";
    private final WebDriver driver;
    @FindBy(id = "defaultRegisterFormPassword")
    private WebElement passwordField;
    @FindBy(id = "defaultRegisterPhonePassword")
    private WebElement confirmPasswordField;
    @FindBy(name = "username")
    private WebElement userNameField;
    @FindBy(css = "[type='email']")
    private WebElement emailField;
    @FindBy(css = "[type='date']")
    private WebElement birthDateField;
    @FindBy(name = "pulic-info")
    private WebElement publicInfoField;
    @FindBy(className = "mb-4")
    private WebElement signUpFormTitle;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        // Initialize PageFactory elements
        PageFactory.initElements(driver, this);
    }

    public void populatePassword(String password) {
        passwordField.sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void populateConfirmPassword(String password) {
        confirmPasswordField.sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void populateUsername(String username) {
        userNameField.sendKeys(username);
    }

    public void populateEmail(String email) {
        emailField.sendKeys(email);
    }

    public void populateBirthDate(String date) {
        birthDateField.sendKeys(date);
    }

    public void populatePublicInfo(String info) {
        publicInfoField.sendKeys(info);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(page.object.model.RegistrationPage.PAGE_URL));
    }

    public String getSignUpElementText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signUpFormTitle));
        return signUpFormTitle.getText();
    }

}