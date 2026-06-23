Feature: User Registration

  @RU1
  Scenario: Successfully register a user with valid credentials
    When I register with email "eve.holt@reqres.in" and password "pistol"
    Then the response status code should be 200
    And the response should contain a valid token
    And the response should contain a user id

  @RU2
  Scenario: Fail to register when password is missing
    When I register with email "eve.holt@reqres.in" without a password
    Then the response status code should be 400
    And the response should contain the error message "Missing password"

  @RU3
  Scenario: Fail to register when email is missing
    When I register without any credentials
    Then the response status code should be 400
    And the response should contain the error message "Missing email or username"
