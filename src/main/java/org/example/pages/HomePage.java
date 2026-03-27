package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    /**
     * Elements with PageFactory + FindBy
     */
    @FindBy(xpath = "//span[text()='Dashboard']")
    WebElement homeHeaderText;

    @FindBy(xpath = "//h2[text()='Home']")
    WebElement homeTitle;

    @FindBy(css = "img[data-testid='github-avatar']")
    WebElement avatarIcon;

    @FindBy(xpath = "//span[normalize-space()='Sign out']")
    WebElement signOutButton;

    @FindBy(css = "input[type='submit'][value='Sign out']")
    WebElement confirmSignOutButton;

    @FindBy(xpath = "//span[normalize-space()='Sign up for GitHub']")
    WebElement signUpText;

    @FindBy(xpath = "//span[text()='Settings']")
    WebElement settingsButton;

    @FindBy(xpath = "//span[normalize-space()='New']")
    WebElement newRepoButton;

    @FindBy(xpath = "//button[@aria-haspopup='dialog' and contains(@class,'appHeaderButton')]")
    WebElement menuIcon;

    @FindBy(xpath = "//span[text()='Issues']")
    WebElement issuesLink;


    /**
     * Constructor for HomePage
     * Initializes web elements using PageFactory
     *
     * @param driver WebDriver used to control the browser
     */
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Returns the main header text on the home page
     * Useful for validating successful login
     *
     * @return home page header text
     */
    public String getHomeHeaderText() {
        return getText(homeHeaderText);

    }

    /**
     * Returns the title text on the home page
     * Useful for validating successful login
     *
     * @return home page title text
     */
    public String getHomeTitleText() {
        return getText(homeTitle);
    }


    /**
     * Performs logout from the platform by clicking
     * avatar, sign out and confirming sign out
     */
    public void logout() {
        click(avatarIcon);
        click(signOutButton);
        click(confirmSignOutButton);
    }

    /**
     * Returns the "Sign up for GitHub" text
     * Useful for validating that logout was successful
     *
     * @return text of the sign up element
     */
    public String getSignUpText() {
        return getText(signUpText);
    }

    /**
     * Navigates to the Settings page via user avatar
     * Used for profile update
     */
    public void goToSettings() {
        click(avatarIcon);
        click(settingsButton);
    }

    /**
     * Clicks the "Create repository" button to start creating a new repository
     * Used in repository creation tests
     */
    public void goToNewRepository() {
        click(newRepoButton);
    }

    /**
     * Opens the main menu by clicking the menu icon
     * Used before navigating to other sections like Issues
     */
    public void openMenu() {
        click(menuIcon);
    }

    /**
     * Navigates to the Issues page by clicking the Issues link
     */
    public void goToIssues() {
        click(issuesLink);
    }


}
