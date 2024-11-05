package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.factory.Header;
import page.factory.HomePage;
import page.factory.LoginPage;
import page.factory.RegistrationPage;
import page.factory.ProfilePage;

public class RegistrationTests extends BaseTest {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {RandomStringUtils.randomAlphabetic(10, 15), //user
                        RandomStringUtils.randomAlphabetic(10, 15) + "@abv.bg", //email
                        "Password123", //password
                        "119" + (Keys.TAB) + "1983",  //date "11" + "09" + (Keys.TAB) + "1983"
                        "Test123"}
        };
    }

    @Test(dataProvider = "getUsers")
    public void testRegistrationPageValidData(String user, String email, String password, String date, String info) {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        //Navigate directly to Login page
        loginPage.navigateTo();
        loginPage.clickRegister();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        Assert.assertTrue(registrationPage.isUrlLoaded(), "The Registration URL is not correct!");
        String signUpText = registrationPage.getSignUpElementText();
        Assert.assertEquals(signUpText, "Sign up");
        registrationPage.populateUsername(user);
        registrationPage.populateEmail(email);
        registrationPage.populateBirthDate(date);
        registrationPage.populatePassword(password);
        registrationPage.populateConfirmPassword(password);
        registrationPage.populatePublicInfo(info);
        loginPage.clickSignIn();

        //Verify results
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");
        Header header = new Header(driver);
        header.clickProfile();
        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, user, "The username is incorrect!");
    }

    @DataProvider(name = "provideInvalidRegistrationData")
    public Object[][] provideInvalidRegistrationData() {
        return new Object[][]{
                {"wep", RandomStringUtils.randomAlphabetic(10, 15) + "@abv.bg", "Password123", "119" + (Keys.TAB) + "1983", "Test123"}, //invalid username
                {RandomStringUtils.randomAlphabetic(10, 15), "test@", "Password123", "119" + (Keys.TAB) + "1983", "Test123"}, //invalid email
                {RandomStringUtils.randomAlphabetic(10, 15), RandomStringUtils.randomAlphabetic(10, 15) + "@abv.bg",
                        "Password", "119" + (Keys.TAB) + "1983", "Test123"}, //invalid password
                {RandomStringUtils.randomAlphabetic(10, 15), RandomStringUtils.randomAlphabetic(10, 15) + "@abv.bg",
                        "Password123", "119" + (Keys.TAB) + "1983", ""}, //invalid info
        };
    }

    @Test(dataProvider = "provideInvalidRegistrationData")
    public void testRegistrationPageInvalidData(String user, String email, String password, String date, String info) {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        //Navigate directly to Login page
        loginPage.navigateTo();
        loginPage.clickRegister();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        Assert.assertTrue(registrationPage.isUrlLoaded(), "The Registration URL is not correct!");
        String signUpText = registrationPage.getSignUpElementText();
        Assert.assertEquals(signUpText, "Sign up");
        registrationPage.populateUsername(user);
        registrationPage.populateEmail(email);
        registrationPage.populateBirthDate(date);
        registrationPage.populatePassword(password);
        registrationPage.populateConfirmPassword(password);
        registrationPage.populatePublicInfo(info);
        //Verify results
        loginPage.isSignInDisabled();
        Assert.assertTrue(registrationPage.isUrlLoaded(), "The Registration URL is not correct!");
    }
}