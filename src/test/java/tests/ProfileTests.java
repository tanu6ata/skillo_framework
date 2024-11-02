package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.factory.*;

public class ProfileTests  extends BaseTest {

    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        return new Object[][]{
                {"user_test1", "user_test1", "user_test1"}
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
        //Verify results
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String actualUserName = profilePage.getUsername();
        Assert.assertEquals(actualUserName, name, "The username is incorrect!");
    }

    @Test(dataProvider = "getUsers")
    public void testModifyProfile(String user, String password, String name) {

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
        //Verify results
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        String profileText = "user_test1  ";
        Assert.assertEquals(profilePage.getUserInfoText(), profileText, "The expected public info is incorrect!");

        // Modify public info in profile
        profilePage.clickUserEditButton();
        String publicInfoText = profilePage.populatePublicInfo("Test Public Info");
        profilePage.clickSave();

        //Verify results
        profilePage.getUserInfoText();
        Assert.assertEquals(profilePage.getUserInfoText() + publicInfoText, profileText + publicInfoText, "The expected public info is incorrect!");

        // Delete public info
        profilePage.clickUserEditButton();
        profilePage.clearPublicInfoField();
        profilePage.clickSave();

        //Verify results
        profilePage.getUserInfoText();
        Assert.assertEquals(profilePage.getUserInfoText(), profileText, "The expected public info is incorrect!");
    }


}