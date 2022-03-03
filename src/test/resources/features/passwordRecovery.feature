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

  @regression @passwordRecovery
  Scenario: Password change process
    Given client logs in with TEST-PASSWORD2 credentials
    And goes to My Profile page
    When TEST-PASSWORD2 changes password
    And TEST-PASSWORD2 new password is set
    Then TEST-PASSWORD2 changes password to old one
    And old TEST-PASSWORD2 password is set
