# @Android or @IOS

@Android
Feature: Testing repeated login

  @regression
Scenario: Repeated login. Forgot PIN code
Given client logs in with TEST-SVETLANA credentials
When client reopens the creditPrime mobile application
And client presses forgot PIN code
Then client sees initial welcome screen