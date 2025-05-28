package com.Pages;

import com.Pages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public List<WebElement> productNames() {
        waitForVisibilityOf(By.cssSelector(".success td:nth-child(2)"));
        return driver.findElements(By.cssSelector(".success td:nth-child(2)"));
    }

    public List<WebElement> productPrices() {
        return driver.findElements(By.cssSelector(".success td:nth-child(3)"));
    }

    public WebElement placeOrderButton() {
        return driver.findElement(By.cssSelector(".btn-success"));
    }

    public WebElement totalPrice() {
        return driver.findElement(By.id("totalp"));
    }

    public boolean isProductInCart(String productName) {
        waitForVisibilityOf(By.cssSelector(".success"));
        for (WebElement nameElement : productNames()) {
            if (nameElement.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    public int getCartItemCount() {
        return productNames().size();
    }

    public String getTotalPrice() {
        return totalPrice().getText();
    }

    public CheckoutPage clickPlaceOrder() {
        click(placeOrderButton());
        return new CheckoutPage(driver, log);
    }
}