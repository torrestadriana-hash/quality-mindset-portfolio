Feature: Checkout process
  As a logged in user
  I want to complete a purchase
  So that my order is confirmed

  Scenario: Complete a successful purchase
    Given Ana is logged in as a standard user
    And she adds the Sauce Labs Backpack to the cart
    And she goes to the cart
    When she completes the purchase with first name "Ana", last name "Torres" and postal code "28001"
    Then she should see the order confirmation message "Thank you for your order!"
