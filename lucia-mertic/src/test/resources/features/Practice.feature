Feature: Practice

  @practiceCheckbox
  Scenario:  Checkbox
    Given User opens page "https://demoqa.com/automation-practice-form"
    When User checks Sports on DemoQa Practice Form
    Then User verifies Sports is selected

    When User checks Sports on DemoQa Practice Form
    Then User verifies Sports is not selected

  @practiceRadioButton
  Scenario:  Radio button
    Given User opens page "https://demoqa.com/automation-practice-form"
    When User selects Male
    Then User verifies Male is selected


  @practiceSelector
  Scenario:  Selector
    Given User opens page "https://demoqa.com/select-menu"
    When User selects option number 2 in select menu
    Then Select menu option "Blue" was verified

    When User selects option "Red" in select menu
    Then Select menu option "Red" was verified
