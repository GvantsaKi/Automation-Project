## **GitHub Automation Project**
Educational automation project that tests GitHub functionality
and API Automation

## UI Automation - Features Covered
- Login (valid & invalid)
- Logout
- Profile name update
- Create & delete repository
- Issues filtering ("Created by me")

## API Automation – Features Covered
- Get all products 
- Filter products by price 
- Get user by ID 
- Create a new user 
- Update an existing user

## Technologies used
- **Java** - Programming Language
- **Selenium WebDriver** - Browser Automation
- **TestNG** - Test Execution Framework
- **Maven** - Project Management
- **Page Object Model (POM) + PageFactory** - Design Pattern
- **RestAssured** - API Automation Testing
- **ExtentReport + TestListener** - Reporting

## Configuration
Test data and environment settings are stored in `config.properties`
(For example - base URL, credentials, wait time, report settings)

## Installation
```mvn clean install```

## Run tests using mvn command
```mvn clean test```
