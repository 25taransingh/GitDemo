@tag
Feature: Error Validation

 
  @ErrorValidation
  Scenario Outline: Negative Test of error validation
    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed.
    Examples:
      | name                          | password    |
      | writetotaransingh@gmail.com   | Taran@1234  |

  
