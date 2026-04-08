 Automation Framework (UI + API Testing)

 Project Overview

This project is a Hybrid Test Automation Framework developed using Selenium (UI Testing) and RestAssured (API Testing).
It follows a modular architecture to ensure scalability, maintainability, and reusability.

The framework supports:

* UI automation testing
* API automation testing
* Logging and reporting
* Cross-layer validation


 Tech Stack

* Language: Java
* Build Tool: Maven
* UI Automation: Selenium WebDriver
* API Automation: RestAssured
* Test Framework: TestNG
* Logging: Log4j
* Reporting: Cucumber Reports



 Project Structure

AutomationFramework1/
│
├── api-tests/        # API test cases and utilities
├── ui-tests/         # UI test cases and page objects
├── common/           # Shared utilities and configurations
├── pom.xml           # Maven configuration


 Features

* Modular framework design
* Separation of UI and API layers
* Reusable request builders and utilities
* Logging using Log4j for debugging
* Detailed test reports generation
* No hardcoded test data (config-driven approach)



 Setup Instructions

 1. Clone Repository

git clone https://github.com/sai123madhavi/Veeva-Project.git

 2. Navigate to Project

cd AutomationFramework1


 3. Install Dependencies


mvn clean install


 Running Tests

 Run All Tests


mvn test


 Run UI Tests

mvn test -Dtest=ui-tests

 Run API Tests

mvn test -Dtest=api-tests


Reports

* Cucumber Reports for UI tests
* Logs available in `logs/` directory


 Validation Strategy

* API response validation using status codes and response body
* UI validation using Selenium assertions
* Cross-layer validation ensures consistency between UI and API


 Best Practices Followed

* Page Object Model (POM) for UI tests
* Reusable API request builder pattern
* Centralized configuration management
* Clean code and naming conventions
* Git version control with proper commits

 Notes

* Ensure Java and Maven are installed before execution
* Update configuration files for environment-specific data
