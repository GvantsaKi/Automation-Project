package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateRepositoryPage extends BasePage {

    //Elements with pageFactory + FindBy
    @FindBy(id = "repository-name-input")
    WebElement repoNameField;

    @FindBy(xpath = "//button[.//span[text()='Create repository']]")
    WebElement createRepoButton;


    // Constructor
    public CreateRepositoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    // Enters repository name
    public void enterRepositoryName(String repoName) {
        sendKeys(repoNameField, repoName);
    }

    // Clicks create repository button
    public void clickCreateRepository() {
        click(createRepoButton);
    }

    // Gets the created repository name
    public String getTypedRepositoryName() {
        return repoNameField.getAttribute("value");
    }
}
