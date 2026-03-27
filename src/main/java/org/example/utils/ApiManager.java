package org.example.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiManager {

    /**
     * Sets the base URI for API requests
     *
     * @param baseURI API base URL
     */
    public ApiManager(String baseURI) {
        RestAssured.baseURI = baseURI;
    }

    /**
     * Sends GET request without path parameters
     *
     * @param endpoint   endpoint URL (for example, "/products")
     * @param headers    request headers
     * @param statusCode expected HTTP status code
     * @return API Response
     */
    public static Response get(String endpoint, Map<String, String> headers, int statusCode) {
        return given()
                .headers(headers)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract()
                .response();
    }

    /**
     * Sends GET request with a path parameter
     *
     * @param endpoint   endpoint URL with placeholder (for example, "/users/{id}")
     * @param headers    request headers
     * @param userId     int path parameter value "Id"
     * @param statusCode expected HTTP status code
     * @return API response
     */
    public static Response get(String endpoint, Map<String, String> headers, int userId, int statusCode) {
        return given()
                .pathParam("id", userId)
                .headers(headers)
                .log().all()
                .when()
                .get(endpoint)
                .then()
                .statusCode(statusCode)
                .log().all()
                .extract()
                .response();
    }

    /**
     * Sends a POST request to the given endpoint with headers and body
     *
     * @param endpoint endpoint URL ("/users/")
     * @param headers  request headers
     * @param body     request body (user data)
     * @return API response
     */
    public static Response post(String endpoint, Map<String, String> headers, Object body) {
        return given()
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }

    /**
     * Sends a PUT request to the given endpoint with a path parameter, headers and body
     *
     * @param endpoint endpoint URL (for example, "/users/{id}")
     * @param headers  request headers
     * @param userId   value for path parameter "id"
     * @param body     request body with updated data
     * @return API response
     */
    public static Response put(String endpoint, Map<String, String> headers, int userId, Object body) {
        return given()
                .pathParam("id", userId)
                .headers(headers)
                .body(body)
                .log().all()
                .when()
                .put(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
    }
}
