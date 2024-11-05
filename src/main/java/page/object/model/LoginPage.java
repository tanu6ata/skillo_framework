package page.object.model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/login";
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String username, String password) {
        populateUsername(username);
        populatePassword(password);
        clickSignIn();
    }

    public void navigateTo() {
        this.driver.get(LoginPage.PAGE_URL);
    }

    public void clickSignIn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("sign-in-button")));
            signInButton.click();
        } catch (TimeoutException e) {
            System.out.println("Timed out waiting for the Sign-In button to become clickable.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isSignInDisabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement signInButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@disabled]")));
            return signInButton != null;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRegister (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement registerButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Register")));
        registerButton.click();
    }

    public void populatePassword(String password) {
        WebElement passwordField = driver.findElement(By.id("defaultLoginFormPassword"));
        passwordField.sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void populateUsername(String username) {
        WebElement userNameField = driver.findElement(By.id("defaultLoginFormUsername"));
        userNameField.sendKeys(username);
    }

    public String getSignInElementText() {
        WebElement signInFormTitle = driver.findElement(By.className("h4"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(signInFormTitle));
        return signInFormTitle.getText();
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(LoginPage.PAGE_URL));
    }

    public void clickRememberMe (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement rememberMeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.className("remember-me-button")));
        rememberMeCheckbox.click();
    }

}