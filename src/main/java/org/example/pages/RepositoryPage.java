package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RepositoryPage extends BasePage {

    //Elements with pageFactory + FindBy
    @FindBy(xpath = "//*[@id='repo-title-component']/strong/a")
    WebElement repositoryTitle;


    // Constructor
    public RepositoryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public String getRepositoryTitleText() {
        return getText(repositoryTitle); // uses BasePage.getText()
    }

}
