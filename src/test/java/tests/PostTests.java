package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.factory.*;

import java.io.File;

public class PostTests extends BaseTest{
    @DataProvider(name = "getUsers")
    public Object[][] getUsers() {
        File postPicture = new File("src\\test\\resources\\upload\\test_upload_pumpkins.jpg");
        String caption = "Testing image upload";

        return new Object[][]{{"user_test1", "user_test1", "user_test1", postPicture, caption}};
    }

    @Test(dataProvider = "getUsers")
    public void testCreateAndDeletePost(String user, String password, String username, File file, String caption) {
        //Gets a driver instance from parent class (TestObject)
        WebDriver driver = getDriver();

        LoginPage loginPage = new LoginPage(driver);
        //Navigate directly to Login page
        loginPage.navigateTo();
        loginPage.login(user, password);

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isUrlLoaded(), "The Home URL is not correct!");

        Header header = new Header(driver);
        header.clickNewPost();

        PostsPage postPage = new PostsPage(driver);
        Assert.assertTrue(postPage.isUrlLoaded(), "The POST URL is not correct!");
        postPage.uploadPicture(file);
        Assert.assertTrue(postPage.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(file.getName(), postPage.getImageName(), "The image name is incorrect!");
        postPage.populatePostCaption(caption);
        postPage.clickCreatePost();

        ProfilePage profilePage = new ProfilePage(driver);
        Assert.assertTrue(profilePage.isUrlLoaded(), "The Profile URL is not correct!");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(profilePage.getPostCount(), 1, "The number of Posts is incorrect!");

        profilePage.clickPost(0);

        PostModal postModal = new PostModal(driver);
        Assert.assertTrue(postModal.isImageVisible(), "The image is not visible!");
        Assert.assertEquals(postModal.getPostTitle(), caption);
        Assert.assertEquals(postModal.getPostUser(), username);

        //Delete post
        profilePage.deletePost();

        // Verify post is deleted
        Assert.assertTrue(postModal.isImageDeleted(), "The image is still visible!");
        Assert.assertEquals(profilePage.getPostCount(), 0, "The number of Posts is incorrect!");
    }
}