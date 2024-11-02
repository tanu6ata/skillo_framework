package page.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PostModal {
    private final WebDriver driver;

    @FindBy(css = ".post-modal-img img")
    private WebElement image;
    @FindBy(className = "post-title")
    private WebElement postTitle;
    @FindBy(className = "post-user")
    private WebElement postUser;

    public PostModal(WebDriver driver) {
        this.driver = driver;
        WebElement modalElement = driver.findElement(By.className("post-modal"));
        // Initialize PageFactory elements
        PageFactory.initElements(driver, this);
    }

    public boolean isImageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.visibilityOf(image)).isDisplayed();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isImageDeleted() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return wait.until(ExpectedConditions.invisibilityOf(image));
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public String getPostTitle() {
        return postTitle.getText();
    }

    public String getPostUser() {
        return postUser.getText();
    }

}