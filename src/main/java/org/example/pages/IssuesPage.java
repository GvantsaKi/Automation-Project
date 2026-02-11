package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IssuesPage extends BasePage {

    //Elements with pageFactory + FindBy
    @FindBy(xpath = "//span[text()='Created by me']")
    WebElement createdByMeFilter;

    @FindBy(xpath = "//h1[text()='Created by me']")
    WebElement createdByMeTitle;


    public IssuesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Clicks on created by me filter on issues page
    public void filterCreatedByMe() {
        click(createdByMeFilter);
    }

    // Gets the text for issues test validation
    public String getCreatedByMeTitle() {
        return getText(createdByMeTitle);
    }
}
