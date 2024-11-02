package tests;

import page.factory.Header;
import page.factory.HomePage;
import page.factory.LoginPage;
import page.factory.ProfilePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.lang3.RandomStringUtils;

public class LoginTests extends BaseTest {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"user_test1", "user_test1", "user_test1"}
        };
    }

    @Test(dataProvider = "getUsers")
    public void testLoginValidCredentials(String user, String password, String name) {
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
                {"user_test1", RandomStringUtils.randomAlphabetic(7, 15)},
                {RandomStringUtils.randomAlphabetic(7, 15), "user_test1"},

        };
    }

    @Test(dataProvider = "provideWrongCredentials")
    public void testLoginInvalidCredentials (String user, String password) {

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

    @Test(dataProvider = "getUsers")
    public void testLoginRememberMe(String user, String password, String name) {

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
        loginPage.clickRememberMe();
        loginPage.clickSignIn();

        //Verify user is logged in
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, name, "The username is incorrect!");

        //Log out
        header.clickLogout();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");

        //Log in again
        loginPage.getSignInElementText();
        Assert.assertEquals(signInText, "Sign in");
        loginPage.clickSignIn();

        // Verify that user is successfully logged in
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");
        header.clickProfile();
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        profilePage.getUsername();
        Assert.assertEquals(actualUserName, name, "The username is incorrect!");
    }




}