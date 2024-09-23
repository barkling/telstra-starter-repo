Feature: Sim Card Activation

#  Background:
#    Given the Sim Card Activation is running

  Scenario: Successful SIM Card Activation
    Given I have a SIM card with ICCID "1255789453849037777"
    When I submit an activation request
    Then the activation should be successful
    And I query the activation status for ICCID "1255789453849037777"
    Then the status should be "ACTIVE"

  Scenario: Failed SIM Card Activation
    Given I have a SIM card with ICCID "8944500102198304826"
    When I submit an activation request
    Then the activation should fail
    And I query the activation status for ICCID "8944500102198304826"
    Then the status should be "FAILED"