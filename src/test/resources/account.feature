Feature: Manage Account

  Scenario: Make a deposit
    Given I'm a bank customer "John Doe"
    When I make deposit with 200
    Then I receive the new amount of my account 200

  Scenario: Make a withdrawal
    Given I'm a bank customer "John Doe"
    When I make a withdrawal with 10
    Then I receive the new amount of my account 190

  Scenario: Retrieve all operations
    Given I'm a bank customer "John Doe"
    When I ask for my history
    Then I receive the history of my operations "deposit,withdrawal"

  Scenario: Make a deposit
    Given I'm not a bank customer "HackerMan"
    When I make deposit with 200
    Then I receive Exception "Customer HackerMan is not a bank customer"
