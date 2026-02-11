package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    //Elements with pageFactory + FindBy
    @FindBy(id = "login_field")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(name = "commit")
    WebElement loginButton;

    @FindBy(css = "#js-flash-container .flash-error") //უკეთესი ლოკატორი ვნახო
    WebElement errorMessage;

    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Fills username and password fields and then clicks on the button, for login
    public void login(String username, String password) {
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        click(loginButton);
    }

    // Gets the error message text for invalid login test
    public String getErrorMessageText() {
        waitForTextVisibleAndNotEmpty(errorMessage);
        return getText(errorMessage);
    }


}
