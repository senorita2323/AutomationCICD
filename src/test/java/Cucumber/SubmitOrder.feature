
Feature:Purchase the order from Ecommerce site

  Background:
    Given I landed on Ecommerce Page

  @Regression
  Scenario Outline:Positive test of purchasing the order
    Given Logged in with username<name> and password<password>
    When I add the product<productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page
    Examples:
      | name                | password   | productName |
      | anjali679@gmail.com | Anjali@123 | ZARA COAT 3 |
