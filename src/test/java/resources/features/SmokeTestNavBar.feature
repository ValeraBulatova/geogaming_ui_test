Feature: Navigation Bar
  Scenario Outline: Select "<module>" in Navigation Bar
    Given Start page of application is opened
    When user selects "<module>"
    Then "<module>" is open
    Examples:
      | module         |
      | Sports         |
      | Live           |
      | Casino         |
      | Live Casino    |
      | BetGames       |
      | Virtual Sports |
      | Promos         |