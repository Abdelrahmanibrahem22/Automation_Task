package com.Pages;

import com.Pages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    public WebElement loginLink() {
        return driver.findElement(By.id("login2"));
    }
    public WebElement cartLink() {
        return driver.findElement(By.id("cartur"));
    }
    public WebElement loggedInUser() {return driver.findElement(By.id("nameofuser"));
    }
    public WebElement homeLink() {
        return driver.findElement(By.id("nava"));
    }

    public WebElement categories() {
        return driver.findElement(By.id("cat"));
    }

    public WebElement searchBox() {
        return driver.findElement(By.id("search"));
    }

    public WebElement searchButton() {
        return driver.findElement(By.cssSelector("search > div > button"));
    }

    public WebElement productLink() {

        return driver.findElement(By.xpath("//a[@class='hrefch'][1]"));
    }

    public void open() {
        driver.get("https://www.demoblaze.com");
        waitForVisibilityOf(By.id("nava"));
    }

    public LoginPage clickLoginLink() {
        click(loginLink());
        return new LoginPage(driver, log);
    }

    public boolean isLoggedInUserDisplayed() {
        waitForVisibilityOf(By.id("nameofuser"));
        return isElementPresent(loggedInUser());
    }

    public String getLoggedInUsername() {
        waitForVisibilityOf(By.id("nameofuser"));
        return loggedInUser().getText();
    }

    public CartPage clickCartLink() {
        click(cartLink());
        return new CartPage(driver, log);
    }

    public boolean isHomePageLoaded() {
        return isElementPresent(homeLink()) && categories().isDisplayed();
    }


    public ProductPage clickProduct() {
        waitForVisibilityOf(By.xpath("//a[@class='hrefch'][1]"));
        click(productLink());
        return new ProductPage(driver, log);
    }
    public String getProductNameInHomePage(){
        WebElement firstProduct= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='hrefch'][1]")));
        String productName= firstProduct.getText();
        return productName;
    }
    public String getProductPriceInHomePage(){
        WebElement firstProduct= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(. , '$')] [1]")));
        String productprice= firstProduct.getText();
        return productprice;
    }
    public String getProductDescriptionInHomePage(){
        WebElement firstProduct= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='card-text'][1]")));
        String productDescription= firstProduct.getText();
        return productDescription;
    }
}