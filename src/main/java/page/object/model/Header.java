package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private final WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method that clicks Logon button
     */
    public void clickLogin() {
        WebElement loginLink = driver.findElement(By.id("nav-link-login"));
        loginLink.click();
    }

    /**
     * Method that clicks Profile Link
     */
    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement profileLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-link-profile")));
        profileLink.click();
    }

    /**
     * Method that clicks New Post button
     */
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
        WebElement searchField = driver.findElement(By.id("search-bar"));
        searchField.sendKeys(searchData);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchDropdown = driver.findElement(By.className("dropdown-container"));
        wait.until(ExpectedConditions.visibilityOf(searchDropdown));
    }

    /**
     * Method that clicks on the first element from the Search dropdown
     */
    public void clickFirstElementFromSearchDropdown() {
        WebElement firstElementFromDropdown = driver.findElement(By.xpath("//div[@class=\"dropdown-container\"]//a"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(firstElementFromDropdown));
        firstElementFromDropdown.click();
    }

    /**
     * Method that clicks Logout button
     */
    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'fas fa-sign-out-alt fa-lg')]")));
        logOutButton.click();
    }
}
