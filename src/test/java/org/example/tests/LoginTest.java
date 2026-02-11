package org.example.tests;

import org.example.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    // This test will check the valid login
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

    // This test will check the invalid login
    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Logging in with invalid credentials
        loginPage.login("wrongusername", "wrongpassword");

        // Asserts that error message is displayed correctly
        assertString(loginPage.getErrorMessageText(), "Incorrect username or password.");

        // Asserts that the user stays on the same page, login
        assertString(driver.getCurrentUrl(), "https://github.com/session");
    }

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
