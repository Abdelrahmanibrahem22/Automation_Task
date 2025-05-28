package com.Pages;

import com.Pages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    public WebElement usernameField() {
        return driver.findElement(By.id("loginusername"));
    }
    public WebElement passwordField() {
        return driver.findElement(By.id("loginpassword"));
    }
    public WebElement loginButton() {
        return driver.findElement(By.cssSelector("#logInModal .btn-primary"));
    }
    public WebElement closeButton() {
        return driver.findElement(By.cssSelector("#logInModal .btn-secondary"));
    }
    public void waitForLoginModal() {
        wait.until(ExpectedConditions.visibilityOf(usernameField()));
    }

    public void enterUsername(String username) {
        type(username, usernameField());
    }

    public void enterPassword(String password) {
        type(password, passwordField());
    }

    public void clickLogin() {
        click(loginButton());
    }
    public HomePage loginWithCredentials(String username, String password) {
        waitForLoginModal();
        enterUsername(username);
        enterPassword(password);
        clickLogin();
        return new HomePage(driver, log);
    }

}