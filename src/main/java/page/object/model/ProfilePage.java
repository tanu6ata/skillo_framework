package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/users/";
    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUsername() {
        WebElement username = driver.findElement(By.tagName("h2"));
        return username.getText();
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.urlContains(ProfilePage.PAGE_URL));
    }

    public int getPostCount() {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        return posts.size();
    }

    public void clickPost(int postIndex) {
        List<WebElement> posts = driver.findElements(By.tagName("app-post"));
        posts.get(postIndex).click();
    }

    public void deletePost() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("fa-trash-alt")));
        deleteButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Yes')]")));
        confirmDeleteButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void clickUserEditButton (){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userEditButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("fa-user-edit")));
        userEditButton.click();
    }

    public String populatePublicInfo(String info) {
        WebElement publicInfoField = driver.findElement(By.xpath("//textarea[@formcontrolname='publicInfo']"));
        publicInfoField.sendKeys(info);
        return info;
    }

    public void clearPublicInfoField() {
        WebElement publicInfoField = driver.findElement(By.xpath("//textarea[@formcontrolname='publicInfo']"));
        publicInfoField.sendKeys(Keys.CONTROL + "a");
        publicInfoField.sendKeys(Keys.DELETE);
    }

    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
        saveButton.click();
    }

    public String getUserInfoText() {
        WebElement publicInfoText = driver.findElement(By.xpath("//p/strong"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(publicInfoText));
        return publicInfoText.getText();
    }

}