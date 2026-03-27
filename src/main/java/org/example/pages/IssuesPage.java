package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IssuesPage extends BasePage {

    /**
     * Elements with PageFactory + FindBy
     */
    @FindBy(xpath = "//span[text()='Created by me']")
    WebElement createdByMeFilter;

    @FindBy(xpath = "//h1[text()='Created by me']")
    WebElement createdByMeTitle;

    /**
     * Constructor for IssuesPage
     * Initializes web elements using PageFactory
     *
     * @param driver WebDriver used to control the browser
     */
    public IssuesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Clicks the "Created by me" filter on the Issues page
     * Used to display issues created by the logged-in user
     */
    public void filterCreatedByMe() {
        click(createdByMeFilter);
    }

    /**
     * Returns the title text of the "Created by me" section
     * Useful for validating that the filter worked correctly
     *
     * @return text of the "Created by me" title
     */
    public String getCreatedByMeTitle() {
        return getText(createdByMeTitle);
    }
}
