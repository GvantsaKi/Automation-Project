package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RepositoryPage extends BasePage {

    /**
     * Elements with PageFactory + FindBy
     */
    @FindBy(xpath = "//*[@id='repo-title-component']/strong/a")
    WebElement repositoryTitle;

    @FindBy(xpath = "//nav//span[text()='Settings']")
    WebElement repositorySettingsTab;

    @FindBy(xpath = "//span[text()='Delete this repository']/ancestor::button")
    WebElement deleteRepoButton;

    @FindBy(xpath = "//span[text()='I want to delete this repository']/ancestor::button")
    WebElement confirmDeleteModalButton;

    @FindBy(xpath = "//span[text()='I have read and understand these effects']/ancestor::button")
    WebElement finalConfirmEffectsButton;

    @FindBy(id = "verification_field")
    WebElement confirmRepoNameField;

    @FindBy(id = "repo-delete-proceed-button")
    WebElement confirmDeleteButton;


    /**
     * Constructor for RepositoryPage
     * Initializes web elements using PageFactory
     *
     * @param driver WebDriver used to control the browser
     */
    public RepositoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Returns the repository title text.
     * Useful for validating that the correct repository page is displayed
     *
     * @return text of the repository title
     */
    public String getRepositoryTitleText() {
        return getText(repositoryTitle);
    }

    /**
     * Deletes the repository by going through the settings and confirmation steps
     * Types the full repository name as verification before final deletion
     *
     * @param fullRepoName full repository name in format "username/repoName"
     */
    public void deleteRepository(String fullRepoName) {
        click(repositorySettingsTab);
        click(deleteRepoButton);
        click(confirmDeleteModalButton);
        click(finalConfirmEffectsButton);
        sendKeys(confirmRepoNameField, fullRepoName);
        click(confirmDeleteButton);
    }
}

