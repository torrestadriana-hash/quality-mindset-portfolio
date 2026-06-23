Feature: Get User Details

  @GU1
  Scenario: Successfully retrieve an existing user
    Given the API is available at "https://reqres.in/api"
    When I request user with id 2
    Then the response status code should be 200
    And the response should contain the user id 2
    And the response should contain a valid email
    And the response should contain a first name
    And the response should contain a valid avatar url

  @GU2
  Scenario: Fail to retrieve a user that does not exist
    Given the API is available at "https://reqres.in/api"
    When I request user with id 23
    Then the response status code should be 404
