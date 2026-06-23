Feature: Login functionality
  As a SauceDemo user
  I want to log in with valid credentials
  So that I can access the product inventory

  Scenario: Successful login with valid credentials
    Given Ana is a registered user
    When she logs in with username "standard_user" and password "secret_sauce"
    Then she should see the inventory page title "Products"

  Scenario: Login attempt with a locked out user
    Given Ana is a registered user
    When she logs in with username "locked_out_user" and password "secret_sauce"
    Then she should see the error message "Epic sadface: Sorry, this user has been locked out."
