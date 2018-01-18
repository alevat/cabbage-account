Feature: A client can post transactions, either credits or debits.

  Scenario: Post transaction

    Given I have an account
    When I post a transaction to an account
    Then the transaction should be listed in the account's ledger