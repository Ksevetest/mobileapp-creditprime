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
    And inserts TEST-ZERO-PRINCIPAL valid credentials
    And sets up and confirms PIN code
    Then TEST-ZERO-PRINCIPAL sees My Credit page with ACTIVE loan and principal_open 0

  @regression
  Scenario: Client with ACTIVE credit line and principal_open > 0
    Given client opens creditPrime mobile application
    When client accepts terms and conditions
    And inserts TEST-ACTIVE valid credentials
    And sets up and confirms PIN code
    Then TEST-ACTIVE sees My Credit page with ACTIVE loan and principal_open greater than 0

  @regression
  Scenario: Client with LATE loan
    Given client opens creditPrime mobile application
    When client accepts terms and conditions
    And inserts TEST-LATE valid credentials
    And sets up and confirms PIN code
    Then TEST-LATE sees My Credit page with LATE loan warning message

  @regression
  Scenario: Client with LATE loan 91 dpd
    Given client opens creditPrime mobile application
    When client accepts terms and conditions
    And inserts TEST-91DPD valid credentials
    And sets up and confirms PIN code
    Then TEST-91DPD sees My Credit page with LATE loan 91+ dpd message

  @regression
  Scenario: Client with COMPLETED loan and next CL CONFIRMED application
    Given client opens creditPrime mobile application
    When client accepts terms and conditions
    And inserts TEST-CONFIRMED valid credentials
    And sets up and confirms PIN code
    Then client sees My Credit page with CONFIRMED application message

