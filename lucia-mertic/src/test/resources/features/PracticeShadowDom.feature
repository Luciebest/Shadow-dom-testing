Feature: Practice Shadow DOM

  @practiceShadowJs1
  Scenario:  Shadow DOM get text, enter text in input field and check value with Js
    Given User opens page "http://watir.com/examples/shadow_dom.html"
    When User verifies shadow dom text "some text" and shadow dom nested text "nested text" with Js
    When User enters and verifies "text" in the input text field with Js
    When User selects the checkbox and verifies if it was selected with Js




  @practiceShadowJs2
  Scenario:  Shadow DOM get text, enter text in input field and check value with Selenium
    Given User opens page "http://watir.com/examples/shadow_dom.html"
    When User verifies shadow dom text "some text" and shadow dom nested text "nested text" with Selenium
    When User enters and verifies "text" in the input text field with Selenium
    When User selects the checkbox and verifies if it was selected with Selenium