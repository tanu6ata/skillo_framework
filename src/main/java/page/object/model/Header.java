package page.object.model;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogin() {
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();
    }

    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();
    }

    public void clickNewPost() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement postLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-new-post")));
        postLink.click();
    }

    /**
     * Method that populates Search field with some data
     * @param searchData enter the data that you want to search for
     */
    public void populateSearchField(String searchData) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-bar")));
        searchField.sendKeys(searchData);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.className("dropdown-container")));
    }

    public void clickFirstElementFromSearchDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstElementFromDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"dropdown-container\"]//a[contains(text(), 'MARIELKATA')]")));
        firstElementFromDropdown.click();
    }

    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'fas fa-sign-out-alt fa-lg')]")));
        logOutButton.click();
    }
}
