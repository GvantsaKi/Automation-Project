package org.example.tests;

import org.example.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.IssuesPage;
import org.example.pages.LoginPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

/**
 * Tests related to GitHub Issues page
 * Checks filtering and navigation for issues created by the logged-in user
 */
public class IssuesTest extends BaseTest {

    /**
     * Test verifies that "Created by me" filter works correctly on the Issues page
     * Steps:
     * 1. Login with valid credentials
     * 2. Open menu and go to Issues page
     * 3. Apply "Created by me" filter
     * 4. Check that the title and URL are correct
     */
    @Test
    public void verifyCreatedByMeIssues() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        IssuesPage issuesPage = new IssuesPage(driver);

        // Login
        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        // Open menu
        homePage.openMenu();

        // Go to Issues page
        homePage.goToIssues();

        // Click "Created by me" filter
        issuesPage.filterCreatedByMe();

        // Assert that the page contains "Created by me" text
        assertString(issuesPage.getCreatedByMeTitle(), "Created by me");

        // Assert that the current link matches the expected link
        assertString(issuesPage.getCurrentUrl(), "https://github.com/issues/created");

    }
}
