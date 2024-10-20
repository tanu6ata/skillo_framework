package page.object.model;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LogoutTests extends BaseTest {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"user_test1", "user_test1", "user_test1"},
//                {"DimitarTarkalanov", "Dimitar1.Tarkalanov1", "DimitarTarkalanov"}, //login with username
//                {"testMail1@gmail.com", "Dimitar1.Tarkalanov1", "DimitarTarkalanov"}, //login with email
//                {"testAdmin@gmail.com", "Admin1.User1", "AdminUser"}, //login with admin user
//                {"manager@gmail.com", "Manager1.Use1", "ManagerUser"} //login with manager user
        };
    }

    @Test(dataProvider = "getUsers")
    public void testLogout(String user, String password, String name) {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        //Navigate directly to Login page
        loginPage.navigateTo();
        loginPage.login(user, password);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");

        Header header = new Header(driver);
        header.clickLogout();
        Assert.assertTrue(loginPage.isUrlLoaded(), "The Login URL is not correct!");
        String signInText = loginPage.getSignInElementText();
        Assert.assertEquals(signInText, "Sign in");
    }

}