package org.example.pages;

import org.example.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PrivateRepoPage extends BasePage {

    /**
     * Elements with PageFactory + FindBy
     */
    @FindBy(xpath = "//img[contains(@alt,'404')]")
    private WebElement notFoundImage;


    /**
     * Constructor for PrivateRepoPage
     * Initializes web elements using PageFactory
     *
     * @param driver WebDriver used to control the browser
     */
    public PrivateRepoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Gets the alt text of the 404 image
     * Used for string assertion in tests
     *
     * @return alt text of 404 image
     */
    public String get404ImageAltText() {
        //waitForElementToBeVisible(notFoundImage);
        return notFoundImage.getAttribute("alt");
    }
}
