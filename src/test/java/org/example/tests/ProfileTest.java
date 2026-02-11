package org.example.tests;

import org.example.BaseTest;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.ProfilePage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest {

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
        profilePage.updateProfileName("GvantsaKi");

        // Asserts that updated name matches the expected one
        assertString(profilePage.getNameFieldValue(), "GvantsaKi");
    }
}
