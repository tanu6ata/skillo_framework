package tests.page.object.model;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.object.model.Header;
import page.object.model.HomePage;
import page.object.model.LoginPage;
import page.object.model.ProfilePage;

public class ProfileTests  extends BaseTest {

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
    public void testProfilePage(String user, String password, String name) {

        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        //Navigate directly to Login page
        loginPage.navigateTo();
        loginPage.login(user, password);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");

        Header header = new Header(driver);
        header.clickProfile();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, name, "The username is incorrect!");


    }
}