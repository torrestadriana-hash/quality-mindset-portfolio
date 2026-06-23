Feature: User Login

  @LU1
  Scenario: Successfully login with valid credentials
    When I login with email "eve.holt@reqres.in" and password "pistol"
    Then the response status code should be 200
    And the response should contain a valid token

  @LU2
  Scenario: Fail to login when password is missing
    When I login with email "eve.holt@reqres.in" without a password
    Then the response status code should be 400
    And the response should contain the error message "Missing password"

  @LU3
  Scenario: Fail to login with an unregistered email
    When I login with email "notfound@reqres.in" and password "pistol"
    Then the response status code should be 400
    And the response should contain the error message "user not found"
