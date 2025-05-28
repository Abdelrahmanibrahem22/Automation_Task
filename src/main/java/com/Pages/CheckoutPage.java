package com.Pages;

import com.Pages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public WebElement nameField() {
        return driver.findElement(By.id("name"));
    }

    public WebElement countryField() {
        return driver.findElement(By.id("country"));
    }

    public WebElement cityField() {
        return driver.findElement(By.id("city"));
    }

    public WebElement creditCardField() {
        return driver.findElement(By.id("card"));
    }

    public WebElement monthField() {
        return driver.findElement(By.id("month"));
    }

    public WebElement yearField() {
        return driver.findElement(By.id("year"));
    }

    public WebElement purchaseButton() {
        return driver.findElement(By.cssSelector("#orderModal .btn-primary"));
    }

    public WebElement confirmationMessage() {
        return driver.findElement(By.xpath("//div[@data-animation='pop']"));
    }

    public WebElement confirmationDetails() {
        return driver.findElement(By.cssSelector(".sweet-alert p"));
    }

    public WebElement okButton() {
        return driver.findElement(By.cssSelector(".confirm"));
    }

    public void fillCheckoutForm(String name, String country, String city,
                                 String creditCard, String month, String year) {
        type(name, nameField());
        type(country, countryField());
        type(city, cityField());
        type(creditCard, creditCardField());
        type(month, monthField());
        type(year, yearField());
    }

    public void clickPurchase() {
        click(purchaseButton());
    }

    public String getConfirmationMessageText() {
        waitForVisibilityOf(By.xpath("//div[@data-animation='pop']"));
        return confirmationMessage().getText();
    }

    public String getConfirmationDetailsText() {
        return confirmationDetails().getText();
    }

    public void clickOk() {
        click(okButton());
    }

    public boolean isCheckoutFormDisplayed() {
        waitForVisibilityOf(By.id("name"));
        return isElementPresent(nameField());
    }
    public void handleAddToCartAlert() {
        waitForAlertToBePresent();
        String alertText = getAlertText();
        if (!alertText.equals("Product added.")) {
            throw new RuntimeException("Unexpected alert text: " + alertText);
        }
        acceptAlert();
    }
    public void takeConfirmationScreenshot() {
        takeScreenshot("order-confirmation-" + System.currentTimeMillis());
    }
}