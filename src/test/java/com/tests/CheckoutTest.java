package com.tests;
import com.Pages.*;
import com.tests.BaseTest;
import utils.JsonReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.UUID;

public class CheckoutTest extends BaseTest {

    @Test(priority = 1)
    public void testCompleteCheckoutFlow() throws IOException {
        String username = "user_" + UUID.randomUUID().toString().substring(0, 8);
        String password = "pass_" + UUID.randomUUID().toString().substring(0, 10);
        String name = "Test User";
        String country = "United States";
        String city = "New York";
        String creditCard = "4111111111111111";
        String month = "12";
        String year = "2025";
        SignUpPage signUp = new SignUpPage(driver,log);
        HomePage homePage = new HomePage(driver, log);
        CheckoutPage checkoutPage=new CheckoutPage(driver,log);
        homePage.open();
        signUp.completeSignup(username,password);
        String productName =homePage.getProductNameInHomePage();
        String productPrice = homePage.getProductPriceInHomePage();
        homePage.clickLoginLink()
                .loginWithCredentials(username, password);
        Assert.assertTrue(homePage.isLoggedInUserDisplayed(), "User is not logged in");
     ProductPage productPage = homePage.clickProduct();
        productPage.addToCart();
        checkoutPage.handleAddToCartAlert();
        CartPage cartPage = homePage.clickCartLink();

        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not in cart");

        Assert.assertEquals(cartPage.getCartItemCount(), 1, "Cart item count mismatch");
        checkoutPage = cartPage.clickPlaceOrder();

        Assert.assertTrue(checkoutPage.isCheckoutFormDisplayed(), "Checkout form not displayed");
        checkoutPage.fillCheckoutForm(name, country, city, creditCard, month, year);
        checkoutPage.clickPurchase();

        String confirmationMessage = checkoutPage.getConfirmationMessageText();
        Assert.assertTrue(confirmationMessage.contains("Thank you for your purchase!"),
                "Confirmation message not displayed");

        String confirmationDetails = checkoutPage.getConfirmationDetailsText();
        Assert.assertTrue(confirmationDetails.contains("Name: "+ name),
                "Name not in confirmation details");
        Assert.assertTrue(confirmationDetails.contains(creditCard.substring(creditCard.length() - 4)),
                "Credit card not in confirmation details");
        Assert.assertTrue(confirmationDetails.contains(productPrice.replace("$", "")),
                "Price not correct confirmation details");
        Assert.assertTrue(confirmationDetails.contains("Id:"),
                "Id not not in confirmation details");

        // Take screenshot
        checkoutPage.takeConfirmationScreenshot();
        checkoutPage.clickOk();
    }

    @Test(priority = 2)
    public void testEmptyFieldsValidation() {
        HomePage homePage = new HomePage(driver, log);
        homePage.open();

        CartPage cartPage = homePage.clickCartLink();
        CheckoutPage checkoutPage = cartPage.clickPlaceOrder();

        checkoutPage.clickPurchase();

        String alertText = checkoutPage.getAlertText();
        Assert.assertTrue(alertText.contains("Please fill out Name and Creditcard."),
                "Empty fields validation failed");
        checkoutPage.acceptAlert();
    }
}
