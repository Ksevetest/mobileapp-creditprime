# @Android or @IOS

@Android
Feature: Testing sign in and logout

  @regression @smoke @signIn
  Scenario: CreditPrime login/logout test
    Given client opens creditPrime mobile application
    When client accepts terms and conditions
    And inserts TEST-SVETLANA valid credentials
    And sets up and confirms PIN code
    Then client sees My Credit page
    And opens My Profile page
    And logs out from the mobile app

  @regression @smoke @signIn
  Scenario: client without active loan
    Given client opens creditPrime mobile application
    When client accepts terms and conditions
    And inserts TEST-STANCUUS valid credentials
    And sets up and confirms PIN code
    Then client sees My Credit page without Active loan
