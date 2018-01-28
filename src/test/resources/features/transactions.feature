Feature: A client can post transactions, either credits or debits.

  Scenario Outline: Post transaction

    Given I have an account
    When I post a transaction to an account with the amount <amount>
    Then the transaction should be listed in the account's ledger

    Examples:
      | amount  |
      | 1000000 |
      | 1234.56 |
      | 1.01    |
      | .01     |
      | 0       |
      | -.01    |
      | -1000   |
