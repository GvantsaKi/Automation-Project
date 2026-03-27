package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    /**
     * Elements with PageFactory + FindBy
     */
    @FindBy(id = "login_field")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(name = "commit")
    WebElement loginButton;

    @FindBy(css = "#js-flash-container .flash-error")
    WebElement errorMessage;

    /**
     * Constructor for LoginPage
     * Initializes web elements using PageFactory
     *
     * @param driver WebDriver used to control the browser
     */
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Performs login by filling in the username and password
     * then clicking the login button
     *
     * @param username valid username for the login
     * @param password valid password for the login
     */
    public void login(String username, String password) {
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        click(loginButton);
    }

    /**
     * Returns the error message displayed after a failed login attempt
     * Waits until the error message is visible and then gets the text
     * Any extra whitespace, newlines, or formatting added by the UI
     * are normalized to a single space and trimmed, so assertions
     * remain stable and reliable
     *
     * @return cleaned error message text
     */
    public String getErrorMessageText() {
        waitForElementToBeVisible(errorMessage);
        String text = errorMessage.getText();
        return text.replaceAll("\\s+", " ").trim();
    }


}