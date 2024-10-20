package factory.model;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.factory.Header;
import page.factory.HomePage;
import page.factory.LoginPage;
import page.factory.ProfilePage;

public class LoginTests extends BaseTest{

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"user_test1", "user_test1", "user_test1"}, //login with username
//                {"testMail1@gmail.com", "Dimitar1.Tarkalanov1", "DimitarTarkalanov"}, //login with email
//                {"testAdmin@gmail.com", "Admin1.User1", "AdminUser"}, //login with admin user
//                {"manager@gmail.com", "Manager1.Use1", "ManagerUser"} //login with manager user
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

        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, name, "The username is incorrect!");
    }
}
