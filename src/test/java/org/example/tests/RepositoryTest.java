package org.example.tests;

import org.example.BaseTest;
import org.example.pages.CreateRepositoryPage;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.RepositoryPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

public class RepositoryTest extends BaseTest {

    @Test
    public void createNewRepositoryTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        CreateRepositoryPage createRepositoryPage = new CreateRepositoryPage(driver);
        RepositoryPage repositoryPage = new RepositoryPage(driver);


        // Login
        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        // Click "New" button on Home page
        homePage.goToNewRepository();

        // Enter repository name
        createRepositoryPage.enterRepositoryName("automation_r");

        // Click create repository button
        createRepositoryPage.clickCreateRepository();


        assertString(repositoryPage.getRepositoryTitleText(), createRepositoryPage.getTypedRepositoryName());

        assertString(driver.getCurrentUrl(), "https://github.com/GvantsaKi/" + createRepositoryPage.getTypedRepositoryName());


    }
}