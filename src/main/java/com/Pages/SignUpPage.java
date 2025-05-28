package com.Pages;
import com.Pages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public WebElement signupLink() {
        return driver.findElement(By.id("signin2"));
    }

    public WebElement usernameField() {
        return driver.findElement(By.id("sign-username"));
    }

    public WebElement passwordField() {
        return driver.findElement(By.id("sign-password"));
    }

    public WebElement signupButton() {
        return driver.findElement(By.cssSelector("#signInModal .btn-primary"));
    }

    public void completeSignup(String username, String password) {
        clickWithWait(signupLink());
        typeWithWait(usernameField(), username);
        typeWithWait(passwordField(), password);
        clickWithWait(signupButton());
        handleAlert();
    }

    private void handleAlert() {
        waitForAlertToBePresent();
        String alertText = getAlertText();
        if (!alertText.contains("Sign up successful")) {
            throw new RuntimeException("Unexpected alert text: " + alertText);
        }
        acceptAlert();
    }
    public void clickWithWait(WebElement element) {
        element = wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    public void typeWithWait(WebElement element,String text) {
        element = wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(text);
    }
}