package tests;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.object.model.LoginPage;
import page.object.model.RegistrationPage;

public class RegistrationTests extends BaseTest {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {RandomStringUtils.randomAlphabetic(10, 15), //user
                        RandomStringUtils.randomAlphabetic(10, 15) + "@abv.bg", //email
                        "Password123", //password
                        "11122003", //date
                        RandomStringUtils.randomAlphabetic(6)} //info
        };
    }

    @Test(dataProvider = "getUsers")
    public void testRegistrationPage(String user, String email, String password, String date, String info) {

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
        //The test fails on populateBirthDate!!!
        registrationPage.populateBirthDate(date);
        registrationPage.populatePassword(password);
        registrationPage.populateConfirmPassword(password);
        registrationPage.populatePublicInfo(info);
        loginPage.clickSignIn();




    }
}
