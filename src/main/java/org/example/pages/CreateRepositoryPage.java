package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateRepositoryPage extends BasePage {

    /**
     * Elements with PageFactory + FindBy
     */
    @FindBy(id = "repository-name-input")
    WebElement repoNameField;

    @FindBy(xpath = "//button[.//span[text()='Create repository']]")
    WebElement createRepoButton;

    @FindBy(id = "RepoNameInput-is-available")
    WebElement repoNameInputIsAvailable;


    /**
     * Constructor for CreateRepositoryPage
     * Initializes web elements using PageFactory
     *
     * @param driver WebDriver used to control the browser
     */
    public CreateRepositoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Enters the given repository name into the repository name input field
     *
     * @param repoName name of the repository to create
     */
    public void enterRepositoryName(String repoName) {
        sendKeys(repoNameField, repoName);
    }

    /**
     * Clicks the "Create repository" button after ensuring the repository name input is validated and available
     */
    public void clickCreateRepository() {
        waitForElementToBeVisible(repoNameInputIsAvailable);
        click(createRepoButton);
    }
}
