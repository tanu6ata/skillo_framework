package page.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private final WebDriver driver;
    @FindBy(id = "nav-link-profile")
    private WebElement profileLink;
    @FindBy(id = "nav-link-login")
    private WebElement loginLink;
    @FindBy (xpath = "//*[contains(@class,'fas fa-sign-out-alt fa-lg')]")
    private WebElement logoutLink;
    @FindBy(id = "nav-link-new-post")
    private WebElement  postLink;
    @FindBy(id = "search-bar")
    private WebElement searchField;
    @FindBy(className = "dropdown-container")
    private WebElement searchDropdown;
    @FindBy (xpath = "//div[@class=\"dropdown-container\"]//a[contains(text(), 'MARIELKATA')]")
    private WebElement firstElementFromDropdown;


    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickLogin() {
        loginLink.click();
    }

    public void clickProfile() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(profileLink));
        profileLink.click();
    }

    public void clickNewPost() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(postLink));
        postLink.click();
    }

    /**
     * Method that populates Search field with some data
     * @param searchData enter the data that you want to search for
     */
    public void populateSearchField(String searchData) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchField.sendKeys(searchData);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(searchDropdown));
    }

    public void clickFirstElementFromSearchDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(firstElementFromDropdown));
        firstElementFromDropdown.click();
    }

    public void clickLogout() {
        logoutLink.click();
    }
}
