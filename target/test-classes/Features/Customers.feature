Feature: Customer Profile

  Background: Common steps for all scenarios
    Given user launch chrome browser
    When user opens the URL "http://admin-demo.nopcommerce.com/login"
    And user enters email as "admin@yourstore.com" and password as "admin"
    And I click on login
    Then user can view dashboard

  Scenario: Add New Customer
    When user click on customers menu
    And click on customers menu item
    And click on add new button
    Then user can view Add New customer page
    When user enter customer info
    And click on Save button
    Then user can view confirmation message  "The new customer has been added successfully."
    And close browser


  Scenario: Perform Customer Search by email
    When user click on customers menu
    And click on customers menu item
    And enter customer email
    When click on search button
    Then user should find email in the search table
    And close browser


  Scenario: Perform Customer Search firstName and lastName
    When user click on customers menu
    And click on customers menu item
    And enter customer FirstName
    And enter customer LastName
    When click on search button
    Then user should find Name in the search table
    And close browser