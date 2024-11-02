package page.factory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    private final WebDriver driver;
    @FindBy(tagName = "h2")
    private WebElement username;
    @FindBy(tagName = "app-post")
    private List<WebElement> posts;
    @FindBy(className = "fa-trash-alt")
    private WebElement deleteButton;
    @FindBy(xpath = "//button[contains(text(), 'Yes')]")
    private WebElement confirmDeleteButton;
    @FindBy(className = "fa-user-edit")
    private WebElement userEditButton;
    @FindBy(xpath = "//textarea[@formcontrolname='publicInfo']")
    private WebElement publicInfoField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;
    @FindBy(xpath = "//p/strong")
    private WebElement publicInfoText;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        // Initialize PageFactory elements
        PageFactory.initElements(driver, this);
    }

    public String getUsername() {
        return username.getText();
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(ProfilePage.PAGE_URL));
    }

    public int getPostCount() {
        return posts.size();
    }

    public void clickPost(int postIndex) {
        posts.get(postIndex).click();
    }

    public void deletePost() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));
        confirmDeleteButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickUserEditButton (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(userEditButton));
        userEditButton.click();
    }

    public String populatePublicInfo(String info) {
        publicInfoField.sendKeys(info);
        return info;
    }

    public void clearPublicInfoField() {
        publicInfoField.sendKeys(Keys.CONTROL + "a");
        publicInfoField.sendKeys(Keys.DELETE);
    }

    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    public String getUserInfoText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(publicInfoText));
        return publicInfoText.getText();
    }

}
