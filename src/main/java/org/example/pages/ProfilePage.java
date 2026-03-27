package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends BasePage {

    /**
     * Elements with PageFactory + FindBy
     */
    @FindBy(id = "user_profile_name")
    WebElement nameField;

    @FindBy(xpath = "//span[text()='Update profile']")
    WebElement updateProfileButton;

    /**
     * Constructor for ProfilePage
     * Initializes web elements using PageFactory
     *
     * @param driver WebDriver used to control the browser
     */
    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Updates the user's profile name by clearing the field
     * entering a new name and clicking the save button
     *
     * @param newName the new profile name to set
     */
    public void updateProfileName(String newName) {
        //waitForElementToBeVisible(nameField);
        nameField.clear();
        sendKeys(nameField, newName);
        click(updateProfileButton);
    }

    /**
     * Returns the current value of the profile name field
     * Useful for validating that the update was successful
     *
     * @return current profile name text
     */
    public String getNameFieldValue() {
        return nameField.getAttribute("value");
    }
}
