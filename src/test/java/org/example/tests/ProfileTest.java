package org.example.tests;

import org.example.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.ProfilePage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

/**
 * Tests related to GitHub user profile functionality
 * Covers updating the profile name
 */
public class ProfileTest extends BaseTest {

    /**
     * Test verifies that a user can update their profile name
     * Steps:
     * 1. Login with valid credentials
     * 2. Navigate to user settings
     * 3. Enter new profile name
     * 4. Assert that the profile name is updated correctly
     */
    @Test
    public void testUpdateProfileName() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        // Login
        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        // Navigate to settings
        homePage.goToSettings();

        // Type new name
        profilePage.updateProfileName(ConfigReader.get("profile.name"));

        // Asserts that updated name matches the expected one
        assertString(profilePage.getNameFieldValue(), ConfigReader.get("profile.name"));
    }
}
