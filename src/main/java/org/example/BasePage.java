package org.example;

import org.example.utils.ConfigReader;
import org.example.utils.Utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage class contains common methods for all pages
 * Other page classes (like LoginPage, HomePage) will extend this class
 */
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor initializes WebDriver and WebDriverWait for page actions
     *
     * @param driver WebDriver
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getLong("wait")));
        PageFactory.initElements(driver, this);
    }

    /**
     * Refreshes the current page
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Returns the current page URL
     *
     * @return URL as String
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Waits until the element is visible on the page
     *
     * @param locator WebElement to wait for
     */
    public void waitForElementToBeVisible(WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator));

    }

    /**
     * Waits until the element is clickable
     *
     * @param locator WebElement to wait for
     */
    public void waitForElementToBeClickable(WebElement locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));

    }

    /**
     * Types text into the input field and logs it in the report
     *
     * @param locator WebElement input field
     * @param text    Text to enter
     */
    public void sendKeys(WebElement locator, String text) {
        waitForElementToBeVisible(locator);
        locator.sendKeys(text);
        Utils.logInfo("send Key was: " + text);


    }

    /**
     * Clicks on an element and logs it in the report
     *
     * @param locator WebElement to click
     */
    public void click(WebElement locator) {
        waitForElementToBeClickable(locator);
        locator.click();
        Utils.logInfo("clicked on:  " + locator);


    }

    /**
     * Gets text from an element and logs it in the report
     *
     * @param locator WebElement to read
     * @return Text of the element
     */
    public String getText(WebElement locator) {
        waitForElementToBeVisible(locator);
        Utils.logInfo("get text from: " + locator.getText());
        return locator.getText();
    }
}
