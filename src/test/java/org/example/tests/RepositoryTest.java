package org.example.tests;

import org.example.BaseTest;
import org.example.pages.CreateRepositoryPage;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.example.pages.RepositoryPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.Test;

/**
 * Tests related to GitHub repository creation and deletion
 */
public class RepositoryTest extends BaseTest {

    /**
     * Test creates a new repository and verifies its creation
     * Steps:
     * 1. Login with valid credentials
     * 2. Go to "Create Repository" page
     * 3. Enter repository name
     * 4. Create repository
     * 5. Verify repository name and URL
     * 6. Delete the created repository
     */
    @Test
    public void createNewRepositoryTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        CreateRepositoryPage createRepositoryPage = new CreateRepositoryPage(driver);
        RepositoryPage repositoryPage = new RepositoryPage(driver);

        // Login
        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        // Navigate to the "New Repository" page from Home
        homePage.goToNewRepository();

        // Enter repository name
        String repoName = "repository" + System.currentTimeMillis();
        createRepositoryPage.enterRepositoryName(repoName);

        // Click create repository button
        createRepositoryPage.clickCreateRepository();

        // Assert that the repository was created with the correct name
        assertString(repositoryPage.getRepositoryTitleText(), repoName);

        // Assert that the repository URL is correct
        String expectedUrl = "https://github.com/" + ConfigReader.get("valid.username") + "/" + repoName;
        assertString(repositoryPage.getCurrentUrl(), expectedUrl);

        // Delete the created repository
        repositoryPage.deleteRepository(ConfigReader.get("valid.username") + "/" + repoName);
    }

    /**
     * Test creates a repository and then deletes it to verify deletion functionality
     * Steps:
     * 1. Login with valid credentials
     * 2. Create a new repository (setup for deletion)
     * 3. Delete the repository using full repository name
     * 4. Verify the repository is deleted and the user is redirected to the repositories list
     *
     * Note: The main focus of this test is to ensure that repository deletion works correctly
     */
    @Test
    public void deleteRepositoryTest() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        CreateRepositoryPage createRepositoryPage = new CreateRepositoryPage(driver);
        RepositoryPage repositoryPage = new RepositoryPage(driver);

        loginPage.login(ConfigReader.get("valid.username"), ConfigReader.get("valid.password"));

        homePage.goToNewRepository();

        String repoName = "repository" + System.currentTimeMillis();

        createRepositoryPage.enterRepositoryName(repoName);
        createRepositoryPage.clickCreateRepository();

        // Delete repo
        String fullRepoName = ConfigReader.get("valid.username") + "/" + repoName;
        repositoryPage.deleteRepository(fullRepoName);

        // Assert redirected URL after deletion
        String expectedUrl = "https://github.com/" + ConfigReader.get("valid.username") + "?tab=repositories";
        assertString(repositoryPage.getCurrentUrl(), expectedUrl);


    }
}