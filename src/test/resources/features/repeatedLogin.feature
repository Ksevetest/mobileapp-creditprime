# @Android or @IOS

@Android
Feature: Testing repeated login

  @regression @RepeatedLogin
  Scenario: Repeated login. Forgot PIN code
    Given client logs in with TEST-SVETLANA credentials
    When client reopens the creditPrime mobile application
    And client presses forgot PIN code
    Then client sees initial welcome screen

  @regression @RepeatedLogin
  Scenario: Repeated login. Incorrect PIN code is entered 5 times
    Given client logs in with TEST-SVETLANA credentials
    When client reopens the creditPrime mobile application
    And client presses incorrect PIN code 5 times in the row
    Then client sees initial welcome screen