Feature:Error Validation

@ErrorValidation
  Scenario Outline:Error validation in login page
    Given I landed on Ecommerce Page
    When Logged in with username<name> and password<password>
    Then "Incorrect email or password." message is displayed
    Examples:
      | name                | password |
      | anjali679@gmail.com | Anjali   |

