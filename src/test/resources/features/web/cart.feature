Feature: Shopping cart
  As a logged in user
  I want to add products to my cart
  So that I can review them before purchasing

  Scenario: Add a product to the cart
    Given Ana is logged in as a standard user
    When she adds the Sauce Labs Backpack to the cart
    Then the cart badge should show "1"

  Scenario: Add multiple products to the cart
    Given Ana is logged in as a standard user
    When she adds the Sauce Labs Backpack to the cart
    And she adds the Bike Light to the cart
    Then the cart badge should show "2"
