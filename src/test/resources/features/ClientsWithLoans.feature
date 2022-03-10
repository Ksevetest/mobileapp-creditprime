# @Android or @IOS

@Android
Feature: Testing clients with loans

  @regression
  Scenario: testing client with SOLD loan
    Given client opens creditPrime mobile application
    When client accepts terms and conditions
    And inserts TEST-SOLD valid credentials
    And sets up and confirms PIN code
    Then client sees My Credit page with SOLD loan information

  @regression
  Scenario: Client with an ACTIVE credit line and principal_open 0
    Given client opens creditPrime mobile application
    When client accepts terms and conditions
    And inserts TEST-CHIRIAC valid credentials
    And sets up and confirms PIN code
    Then client sees My Credit page with ACTIVE loan and principal_open 0.00

