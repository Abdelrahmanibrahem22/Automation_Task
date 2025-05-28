package com.tests;
import com.tests.BaseTest;
import com.Pages.HomePage;
import  com.Pages.LoginPage;
import utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test(priority = 1)
    public void testHomepageLoading() {
        HomePage homePage = new HomePage(driver, log);
        homePage.open();

        Assert.assertTrue(homePage.isHomePageLoaded(),
                "Homepage failed basic loading check");
        Assert.assertEquals(driver.getTitle(), "STORE",
                "Page title verification failed");
        Assert.assertTrue(driver.getCurrentUrl().contains("demoblaze.com"),
                "URL verification failed");
    }
    @Test(priority = 2)
    public void testValidLogin() throws IOException {
        HomePage homePage = new HomePage(driver, log);
        homePage.open();

        LoginPage loginPage = homePage.clickLoginLink();
        homePage = loginPage.loginWithCredentials(
                JsonReader.getTestData("validUser.username"),
                JsonReader.getTestData("validUser.password")
        );

        Assert.assertTrue(homePage.isLoggedInUserDisplayed());
        Assert.assertEquals(
                homePage.getLoggedInUsername(),"Welcome test"
        );
    }

}