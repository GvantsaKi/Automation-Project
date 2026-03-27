package org.example.api;

import io.restassured.response.Response;
import org.example.BaseTest;
import org.example.utils.ApiManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

// Initializes base URI for all API tests
public class ApiTest {
    private final ApiManager api = new ApiManager("https://api.escuelajs.co/api/v1");

    /**
     * Verifies that GET /products returns a non-empty list
     */
    @Test
    public void getAllProductsTest() {
        // Request headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer simpleString");

        Response response = ApiManager.get("/products", headers, 200);
        // Assert response body is not empty
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    /**
     * Verifies that products can be filtered by exact price
     */
    @Test
    public void getProductsByPriceTest() {
        // Request headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer simpleString");

        // Filter product by price
        Response response = ApiManager.get("/products?price=100", headers, 200);

        // Assert HTTP status code
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    /**
     * Test GET /users/{id}
     * Verifies that API returns the correct user details for a given ID
     */
    @Test
    public void getSingleUserTest() {
        // Request headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer simpleString");

        // GET user with ID
        Response response = ApiManager.get("/users/{id}", headers, 1, 200);

        // Assert
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
    }

    /**
     * Test POST /users
     * Verifies that a new user can be created successfully
     */
    @Test
    public void createUserTest() {
        // Request headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer simpleString");

        // Request body with user data
        Map<String, Object> body = new HashMap<>();
        body.put("name", "Nicolas");
        body.put("email", "nico@gmail.com");
        body.put("password", "1234");
        body.put("avatar", "https://picsum.photos/800");

        // Send POST request to create a new user
        Response response = ApiManager.post("/users/", headers, body);

        // Assert status code and returned user data
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "Nicolas");
        Assert.assertEquals(response.jsonPath().getString("email"), "nico@gmail.com");

    }

    /**
     * Test PUT /users/{id}
     * Verifies that an existing user can be updated
     */
    @Test
    public void updateUserTest() {
        // Request headers
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json");
        headers.put("Authorization", "Bearer simpleString");

        // Body for creating a new user (precondition)
        Map<String, Object> createBody = new HashMap<>();
        createBody.put("name", "Test Name");
        createBody.put("email", "test@mail.com");
        createBody.put("password", "1234");
        createBody.put("avatar", "https://picsum.photos/800");

        // Create a user that can be updated
        Response createResponse = ApiManager.post("/users/", headers, createBody);

        // Verify user creation by validating status code
        Assert.assertEquals(createResponse.getStatusCode(), 201);

        // Extract created user ID
        int userId = createResponse.jsonPath().getInt("id");

        // Update body
        Map<String, Object> updateBody = new HashMap<>();
        updateBody.put("name", "Updated Name");
        updateBody.put("email", "updated@mail.com");

        // Update the previously created user
        Response updateResponse = ApiManager.put("/users/{id}", headers, userId, updateBody);

        // Assert that update was successful
        Assert.assertEquals(updateResponse.getStatusCode(), 200);
        Assert.assertEquals(updateResponse.jsonPath().getString("name"), "Updated Name");
        Assert.assertEquals(updateResponse.jsonPath().getString("email"), "updated@mail.com");
    }

}
