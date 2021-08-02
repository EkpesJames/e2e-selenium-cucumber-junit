Feature: Login


  Scenario: Successful Login with valid credentials
    Given user launch chrome browser
    When user opens the URL "http://admin-demo.nopcommerce.com/login"
    And user enters email as "admin@yourstore.com" and password as "admin"
    And I click on login
    Then page title should be "Dashboard / nopCommerce administration"
    When user click on log out link
    Then page title should be "Your store. Login"
    And close browser

  Scenario Outline: Successful Login with valid credentials data driven
    Given user launch chrome browser
    When user opens the URL "http://admin-demo.nopcommerce.com/login"
    And user enters email as "<username>" and password as "<password>"
    And I click on login
    Then page title should be "Dashboard / nopCommerce administration"
    When user click on log out link
    Then page title should be "Your store. Login"
    And close browser
    Examples:
      | username             | password |
      | admin@yourstore.com  | admin    |
      | admin1@yourstore.com | admin123 |