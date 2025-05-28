package com.tests;
import com.tests.BaseTest;
import com.Pages.CartPage;
import com.Pages.HomePage;
import com.Pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(priority = 1)
    public void testCartFunctionality() {
        HomePage homePage = new HomePage(driver, log);
        homePage.open();

        String productName =homePage.getProductNameInHomePage();
        ProductPage productPage = homePage.clickProduct();
        productPage.addToCart();
        productPage.handleAddToCartAlert();
        CartPage cartPage = homePage.clickCartLink();
        Assert.assertTrue(cartPage.isProductInCart(productName),
                "Product not in cart");
        Assert.assertEquals(cartPage.getCartItemCount(), 1,
                "Cart count incorrect");
    }

    @Test(priority = 2)
    public void testCartTotalCalculation() {
        String product1 = "Nexus 6";
        String product2 = "Samsung galaxy s6";

        HomePage homePage = new HomePage(driver, log);
        homePage.open();
        ProductPage productPage = homePage.clickProduct();
        productPage.addToCart();
        productPage.handleAddToCartAlert();
        homePage.open();
        productPage = homePage.clickProduct();
        productPage.addToCart();
        productPage.handleAddToCartAlert();
        CartPage cartPage = homePage.clickCartLink();
        Assert.assertEquals(cartPage.getCartItemCount(), 2,
                "Cart count incorrect");
        Assert.assertTrue(Integer.parseInt(cartPage.getTotalPrice()) > 0,
                "Total price calculation incorrect");
    }
}
