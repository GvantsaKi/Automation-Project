package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    //Elements with pageFactory + FindBy
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

    @FindBy(xpath = "//span[text()='New']") // simple and stable
    WebElement newRepoButton;

    @FindBy(xpath = "//button[@aria-haspopup='dialog' and contains(@class,'appHeaderButton')]")
    WebElement menuIcon;

    @FindBy(xpath = "//span[text()='Issues']")
    WebElement issuesLink;


    //constructor
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Gets title text for login test validation
    public String getHomeHeaderText() {
        return getText(homeHeaderText);

    }

    // Gets another title text for login test validation
    public String getHomeTitleText() {
        return getText(homeTitle);
    }


    // Code for logout test
    public void logout() {
        click(avatarIcon);
        click(signOutButton);
        click(confirmSignOutButton);
    }

    // Gets text for logout test validation
    public String getSignUpText() {
        return getText(signUpText);
    }

    // Code for user profile update test
    public void goToSettings() {
        click(avatarIcon);
        click(settingsButton);
    }

    // Click action for making new repository test
    public void goToNewRepository() {
        click(newRepoButton);
    }

    // Click menu action for issues test
    public void openMenu() {
        click(menuIcon); // reuse BasePage.click (waits + logs)
    }

    // Click issues action for issues test
    public void goToIssues() {
        click(issuesLink); // click Issues link
    }


}
