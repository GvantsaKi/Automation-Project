package org.example;

import org.example.utils.ConfigReader;
import org.example.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getLong("wait")));
        PageFactory.initElements(driver, this);
    }

    // Refreshes page, is used in login test
    public void refreshPage() {
        driver.navigate().refresh();
    }

    // Gets current url, used in tests for assertion
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // Waits for element to be visible
    public void waitForElementToBeVisible(WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator));

    }

    // Waits for element to be clickable
    public void waitForElementToBeClickable(WebElement locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    // Fills in input fields and logs in reports
    public void sendKeys(WebElement locator, String text) {
        waitForElementToBeVisible(locator);
        locator.sendKeys(text);
        Utils.logInfo("send Key was: " + text);


    }

    // Clicks on element, logs
    public void click(WebElement locator) {
        waitForElementToBeClickable(locator);
        locator.click();
        Utils.logInfo("clicked on:  " + locator);


    }

    // Gets text and logs in reports
    public String getText(WebElement locator) {
        waitForElementToBeVisible(locator);
        Utils.logInfo("get text from: " + locator.getText());
        return locator.getText();
    }

    // for error invalid case. Temporary. Might remove later
    public void waitForTextVisibleAndNotEmpty(WebElement element) {
        wait.until(d -> element.isDisplayed() && !element.getText().trim().isEmpty());
    }

}
