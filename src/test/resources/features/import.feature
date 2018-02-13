Feature: A client can import transactions

  Scenario Outline: Import transactions from a file

    Given I have an account
    When I import a <type> transaction export file
    Then all transaction data in the export file is recorded

  Examples:
    | type        |
    | Capital One |