package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/register";
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void populatePassword(String password) {
        WebElement passwordField = driver.findElement(By.id("defaultRegisterFormPassword"));
        passwordField.sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void populateConfirmPassword(String password) {
        WebElement passwordField = driver.findElement(By.id("defaultRegisterPhonePassword"));
        passwordField.sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void populateUsername(String username) {
        WebElement userNameField = driver.findElement(By.name("username"));
        userNameField.sendKeys(username);
    }

    public void populateEmail(String email) {
        WebElement emailField = driver.findElement(By.cssSelector("[type='email']"));
        emailField.sendKeys(email);
    }

    public void populateBirthDate(String date) {
        WebElement birthDateField = driver.findElement(By.cssSelector("[type='date']"));
        birthDateField.sendKeys(date);
    }

    public void populatePublicInfo(String info) {
        WebElement publicInfoField = driver.findElement(By.name("pulic-info"));
        publicInfoField.sendKeys(info);
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(RegistrationPage.PAGE_URL));
    }

    public String getSignUpElementText() {
        WebElement signUpFormTitle = driver.findElement(By.className("mb-4"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signUpFormTitle));
        return signUpFormTitle.getText();
    }

}
