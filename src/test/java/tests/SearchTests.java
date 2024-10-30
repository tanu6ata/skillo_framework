package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.object.model.*;

public class SearchTests extends BaseTest{

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"user_test1", "user_test1", "MARIELKATA"}
        };
    }

    @Test(dataProvider = "getUsers")
    public void testSearchField (String user, String password, String searchData) {
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        //Navigate directly to Login page
        loginPage.navigateTo();
        loginPage.login(user, password);

        // Verify that you are on Home Page
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");

        // Enter search data and click on first element from dropdown
        Header header = new Header(driver);
        header.populateSearchField(searchData);
        header.clickFirstElementFromSearchDropdown();

        //Verify results
        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, searchData, "The username is incorrect!");
    }

}