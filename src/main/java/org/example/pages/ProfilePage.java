package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {

    //Elements with pageFactory + FindBy
    @FindBy(id = "user_profile_name")
    WebElement nameField;

    @FindBy(xpath = "//*[@id='edit_user_29142167']/div/p[2]/button/span/span")
    WebElement updateProfileButton;

    // Constructor
    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Clears, fills in name and clicks on save button
    public void updateProfileName(String newName) {
        waitForElementToBeVisible(nameField);
        nameField.clear(); // field by default has a value, so we need to clear it first
        sendKeys(nameField, newName);
        click(updateProfileButton);
    }

    // Gets name field text
    public String getNameFieldValue() {
        return nameField.getAttribute("value");
    }
}
