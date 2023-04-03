Feature: SauceLab Demo3

  @loginTest2
  Scenario Outline: Successful login
    Given Login page is opened
    When Username and password "<username>" and "<password>" are entered
    And Login is attempted
    Then Products page is displayed

    Examples:
      | username                | password     |
      | standard_user           | secret_sauce |
      | performance_glitch_user | secret_sauce |


  @addToCartHappy
  Scenario Outline: Add to cart
    Given Login page is opened
    When Username and password "<username>" and "<password>" are entered
    And Login is attempted
    Then Products page is displayed

#    User adds to cart product "Sauce Labs Backpack"
#    Then Product "Sauce Labs Backpack" is added to cart

#    When User goes to shopping cart
#    Then Shopping carts page is displayed

#    When User checks out
#    Then Checkout Info page is displayed

#    When User enters checkout info "<checkoutFirstName>" "<checkoutLastName>" "<postalCode>"
#    And User continues on Checkout Info page
#    Then Checkout Overview is displayed

#    When User finishes on Checkout Overview page
#    Then Checkout Complete page is displayed

#    When User goes back to  Products page
#    Then Products page is displayed

    Examples:
      | username      | password     | checkoutFirstName | checkoutLastName | postalCode |
      | standard_user | secret_sauce | Standard           | user             | 12346      |
