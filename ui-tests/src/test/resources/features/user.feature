Feature: User Tests

  Scenario: Register User
    When user registers a new account
    Then account should be created successfully

  Scenario: Login Valid
    When user enters valid credentials
    Then user should login successfully

  Scenario: Login Invalid
    When user enters invalid credentials
    Then error message should be displayed

  Scenario: Add Cart
    When user adds a product to cart
    Then product should be added to cart

  Scenario: Remove Cart
    When user adds a product to cart
    And user removes product
    Then cart should be empty