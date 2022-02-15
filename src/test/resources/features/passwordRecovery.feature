# @Android or @IOS

@Android
Feature: Testing password recovery and change

  @InDevelopment
  Scenario: Password recovery process
    Given client TEST-PASSWORD goes to password recovery page
    And enters his phone number
    And sees success message that SMS is sent
    When client enters temporary password that he received by SMS
    And chooses new password
    Then client successfully logs in and sees My Credit page

    #todo
  @InDevelopment
  Scenario: Password change process
