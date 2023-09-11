Feature: join great odds
  @RegistrationFormClosed
  Scenario Template: Remove value from "<field>"
    Given registration popup is open
    When user fill registration form
    And user removes value from "<field>"
    Then error is marked with a "<message>"
    Examples:
    | field      | message                                                                        |
    | First name | Please provide your first name                                                 |
    | Surname    | Please provide your surname                                                    |
    | Email      | Please provide your valid email address                                        |
    | Number     | Please enter a valid mobile number between 7-20 characters                     |
    | Address    | Please enter a valid address                                                   |
    | Town/city  | Please enter a valid town or city                                              |
    | Postcode   | Please enter a valid postcode                                                  |
    | Username   | Username must contain min 6 - max 12 characters using letters and numbers      |
    | Password   | Password must contain min 8 - max 15 characters containing letters and numbers |
    | Policy     | Please confirm you have read and accepted Greatodds's Terms and Conditions     |

  @RegistrationFormClosed
  Scenario: Register valid user
    Given registration popup is open
    When user fill registration form
    And click submit button
    Then Activation account instruction is displayed

  @RegistrationFormClosed
  Scenario: Register a user younger required age
    Given registration popup is open
    When user fill registration form
    And set date of birth less then 18 years ago
    And click submit button
    Then validation message is displayed