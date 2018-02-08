Feature: A client can create and access accounts used to record transactions

  Scenario: Invalid JSON in create Account request
    When I create an account with invalid JSON
    Then the response should indicate an invalid request