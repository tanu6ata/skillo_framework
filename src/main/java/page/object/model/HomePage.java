package page.object.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    public static final String PAGE_URL = "http://training.skillo-bg.com:4300/posts/all";
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUrlLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.urlToBe(HomePage.PAGE_URL));
    }

    public void navigateTo() {
        this.driver.get(PAGE_URL);
    }

    //Logout section
    public void clickLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logOutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'fas fa-sign-out-alt fa-lg')]")));
        logOutButton.click();
    }

    //Search section
    public void populateSearchField(String searchText) {
        WebElement searchField = driver.findElement(By.id("search-bar"));
        searchField.sendKeys(searchText);
    }
    public void clickSearchField() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBar = wait.until(ExpectedConditions.elementToBeClickable(By.id("search-bar")));
        searchBar.click();
    }

}
