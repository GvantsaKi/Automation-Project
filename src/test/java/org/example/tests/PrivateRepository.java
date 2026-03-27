package org.example.tests;

import org.example.BaseTest;
import org.example.pages.LoginPage;
import org.example.pages.PrivateRepoPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

/**
 * Tests related to GitHub repository access
 * Covers behavior when trying to open a private repository without permission
 */
public class PrivateRepository extends BaseTest {

    /**
     * Verifies that a private repository cannot be accessed
     * when the user is NOT logged in
     * and a 404 page is shown instead
     * Opens a private repository URL and checks
     * that the 404 error image is displayed
     */
    @Test
    public void repositoryNotFoundWhenLoggedOut() {
        PrivateRepoPage privateRepoPage = new PrivateRepoPage(driver);
        // Private Repository link
        String privateRepoUrl = "https://github.com/otherUser/private-repo";

        driver.get(privateRepoUrl);
        String altText = privateRepoPage.get404ImageAltText();

        // Assert that the page contains following text
        assertString(altText, "404 “This is not the web page you are looking for”");
    }

    /**
     * Verifies that a private repository cannot be accessed
     * even when the user IS logged in
     */
    @Test
    public void repositoryNotFoundWhenLoggedIn() {
        LoginPage loginPage = new LoginPage(driver);
        PrivateRepoPage privateRepoPage = new PrivateRepoPage(driver);
        String privateRepoUrl = "https://github.com/otherUser/private-repo";

        // Login first
        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        driver.get(privateRepoUrl);
        String altText = privateRepoPage.get404ImageAltText();

        // Assert
        assertString(altText, "404 “This is not the web page you are looking for”");
    }
}

