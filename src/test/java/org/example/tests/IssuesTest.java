package org.example.tests;

import org.example.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.IssuesPage;
import org.example.pages.LoginPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

public class IssuesTest extends BaseTest {

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
        assertString(driver.getCurrentUrl(), "https://github.com/issues/created");

    }
}
