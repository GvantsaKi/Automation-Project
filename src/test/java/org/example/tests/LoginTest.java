package org.example.tests;

import org.example.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

/**
 * Tests related to GitHub login and logout functionality
 * Covers valid login, invalid login and logout scenarios
 */
public class LoginTest extends BaseTest {


    /**
     * Test verifies that a user can login successfully with valid credentials
     * Steps:
     * 1. Enter valid username and password
     * 2. Refresh page to ensure user stays logged in
     * 3. Assert that homepage header and title are correct
     */
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        // Login
        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        // Refreshes the page to make sure we stay on the correct page
        homePage.refreshPage();

        // Asserts the title text on homepage, after login
        assertString(homePage.getHomeHeaderText(), "Dashboard");

        // Asserts another title text on homepage, after login
        assertString(homePage.getHomeTitleText(), "Home");


    }

    /**
     * Test verifies that login fails with invalid credentials
     * Steps:
     * 1. Enter wrong username and password
     * 2. Assert that the error message is displayed
     * 3. Assert that the user stays on the login page
     */
    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Logging in with invalid credentials
        loginPage.login("wrongusername", "wrongpassword");

        // Asserts that error message is displayed correctly
        assertString(loginPage.getErrorMessageText(), "Incorrect username or password.");

        // Asserts that the user stays on the same page, login
        assertString(loginPage.getCurrentUrl(), "https://github.com/session");
    }

    /**
     * Test verifies that a user can logout successfully
     * Steps:
     * 1. Login with valid credentials
     * 2. Logout from homepage
     * 3. Assert that the homepage URL and text indicate the user is logged out
     */
    @Test
    public void testLogout() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        // Login
        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        // Logout
        homePage.logout();

        // Assert that the current link matches the expected link
        assertString(homePage.getCurrentUrl(), "https://github.com/");

        // Asserts that the text "Sign up for Github" is displayed on the main page
        assertString(homePage.getSignUpText(), "Sign up for GitHub");
    }
}
