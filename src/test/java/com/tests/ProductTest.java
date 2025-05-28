package com.tests;
import com.tests.BaseTest;
import com.Pages.HomePage;
import com.Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test(priority = 1)
    public void testProductDetails() {


        HomePage homePage = new HomePage(driver, log);
        homePage.open();
        String productNameOnHomePage =homePage.getProductNameInHomePage();
        String productPriceOnHomePage =homePage.getProductPriceInHomePage();
        String productDescriptionOnHomePage =homePage.getProductDescriptionInHomePage();
        ProductPage productPage = homePage.clickProduct();
        String productNameOnPage = productPage.getProductNameText();
        String productPrice = productPage.getProductPriceText();
        String productDescription = productPage.getProductDescriptionText();

        Assert.assertEquals(productNameOnPage, productNameOnHomePage,
                "Product name doesn't match");
        Assert.assertEquals(productPriceOnHomePage + " *includes tax",productPrice ,
                "Product price format incorrect");
        Assert.assertEquals(productDescription,productDescriptionOnHomePage,
                "Product description incomplete");
    }

    @Test(priority = 2)
    public void testAddToCart() {
        HomePage homePage = new HomePage(driver, log);
        homePage.open();
       // String productNameOnHomePage =homePage.getProductNameInHomePage();
        ProductPage productPage = homePage.clickProduct();
        productPage.addToCart();

        String alertText = productPage.getAlertText();
        Assert.assertEquals(alertText, "Product added",
                "Add to cart confirmation not displayed");
        productPage.acceptAlert();
    }
}
