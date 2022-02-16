# @Android or @IOS

@Android
Feature: Testing password recovery and change

  @regression @passwordRecovery
  Scenario: Password recovery process
    Given client goes to password recovery page
    And TEST-PASSWORD enters his phone number
    And sees success message that SMS is sent
    When TEST-PASSWORD enters temporary password that he received by SMS
    And TEST-PASSWORD chooses new password
    Then client successfully logs in and sees My Credit page

    #todo
  @InDevelopment
  Scenario: Password change process
