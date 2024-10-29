package tests;

import page.object.model.Header;
import page.object.model.HomePage;
import page.object.model.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.object.model.ProfilePage;

public class LoginTests extends BaseTest {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"user_test1", "user_test1", "user_test1"}
        };
    }

    @Test(dataProvider = "getUsers")
    public void testLogin(String user, String password, String name) {
        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
        String signInText = loginPage.getSignInElementText();
        Assert.assertEquals(signInText, "Sign in");
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();

        //Verify results
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, name, "The username is incorrect!");
    }

    @DataProvider(name = "provideWrongCredentials")
    public Object[][] provideWrongCredentials() {
        return new Object[][]{
                {"user_test1", "ttt"}
        };
    }

    @Test(dataProvider = "provideWrongCredentials")
    public void testLoginFailed (String user, String password) {

        WebDriver driver = getDriver();
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        Header header = new Header(driver);
        header.clickLogin();

        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
        String signInText = loginPage.getSignInElementText();
        Assert.assertEquals(signInText, "Sign in");
        loginPage.populateUsername(user);
        loginPage.populatePassword(password);
        loginPage.clickSignIn();

        //Verify results
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
    }
}