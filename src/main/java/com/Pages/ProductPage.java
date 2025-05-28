package com.Pages;

import com.Pages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    public WebElement productName() {
        return driver.findElement(By.className("name"));
    }
    public WebElement productPrice() {
        return driver.findElement(By.cssSelector(".price-container"));
    }
    public WebElement productDescription() {
        return driver.findElement(By.cssSelector("#more-information > p"));
    }
    public WebElement addToCartButton() {
        return driver.findElement(By.xpath("//a[@class='btn btn-success btn-lg' and contains( . , 'Add to cart')]"));
    }
    public String getProductNameText() {
        waitForVisibilityOf(By.className("name"));
        return productName().getText();
    }

    public String getProductPriceText() {
        return productPrice().getText();
    }

    public String getProductDescriptionText() {
        return productDescription().getText();
    }

    public void addToCart() {
        waitForVisibilityOf(By.xpath("//a[@class='btn btn-success btn-lg' and contains( . , 'Add to cart')]"));
        click(addToCartButton());
    }

    public void handleAddToCartAlert() {
        waitForAlertToBePresent();
        String alertText = getAlertText();
        if (!alertText.equals("Product added")) {
            throw new RuntimeException("Unexpected alert text: " + alertText);
        }
        acceptAlert();
    }
}