package com.cegeka.academy.qa.tests.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShadowDomSteps {
    @Autowired
    @Qualifier("driver")
    private WebDriver driver;
    //JavascriptExecutor javascriptExecutor= ((JavascriptExecutor) driver);
    @Autowired
    @Qualifier("javascriptExecutor")
    private JavascriptExecutor javascriptExecutor;


   @When("User verifies shadow dom text {string} and shadow dom nested text {string} with Js")
    public void userVerifiesShadowDomText(String expectedText, String expectedNestedText) {

      /* WebElement shadowHost = driver.findElement(By.id("shadow_host"));
       SearchContext searchContext = (SearchContext) javascriptExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
       String actualText = searchContext.findElement(By.cssSelector("span.info")).getText();

       Assert.assertEquals("Shadow text is not correct ", expectedText, actualText);

       //nu merge dupa id, xpath in shadow dom
       WebElement shadowHost2 =  searchContext.findElement(By.cssSelector("#nested_shadow_host"));
       SearchContext searchContext2 = (SearchContext) javascriptExecutor.executeScript("return arguments[0].shadowRoot", shadowHost2);
       String actualNestedText = searchContext2.findElement(By.cssSelector("#nested_shadow_content > div")).getText();

       Assert.assertEquals("Shadow text is not correct ", expectedNestedText, actualNestedText);*/


       //3. (Assignment) optional - using getElementSearchContext
       List<By> shadowHostList = new ArrayList<>(List.of(By.id("shadow_host"), By.cssSelector("#nested_shadow_host")));

       SearchContext searchContext = getElementSearchContext(shadowHostList, By.cssSelector("span.info"));
       String actualText = ((WebElement) searchContext).getText();

       Assert.assertEquals("Shadow text is not correct ", expectedText, actualText);

       SearchContext nestedSearchContext = getElementSearchContext(shadowHostList, By.cssSelector("#nested_shadow_content > div"));
       String actualNestedText = ((WebElement) nestedSearchContext).getText();

       Assert.assertEquals("Nested shadow text is not correct ", expectedNestedText, actualNestedText);

   }

    @When("User verifies shadow dom text {string} and shadow dom nested text {string} with Selenium")
    public void userVerifiesShadowDomTextAndShadowDomNestedText(String expectedText, String expectedNestedText) {
        WebElement shadowHost1 = driver.findElement(By.id("shadow_host"));
        SearchContext searchContext1 = shadowHost1.getShadowRoot();
        String actualText = searchContext1.findElement(By.cssSelector("span.info")).getText();

        Assert.assertEquals("Shadow text is not correct ", expectedText, actualText);

        WebElement shadowHost2 =  searchContext1.findElement(By.cssSelector("#nested_shadow_host"));
        SearchContext searchContext2 = shadowHost2.getShadowRoot();
        String actualNestedText = searchContext2.findElement(By.cssSelector("#nested_shadow_content > div")).getText();

        Assert.assertEquals("Shadow text is not correct ", expectedNestedText, actualNestedText);

    }

    @When("User enters and verifies {string} in the input text field with Js")
    public void userEntersTextInInputFieldWithJs(String expectedText) {
        WebElement shadowHost = driver.findElement(By.id("shadow_host"));
        SearchContext searchContext = (SearchContext) javascriptExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
        WebElement inputField = searchContext.findElement(By.cssSelector("input[type='text']"));

        //Set the value property of the first argument (which is the inputField element) to the second argument (which is expectedText)
        javascriptExecutor.executeScript("arguments[0].value = arguments[1]", inputField, expectedText);

        String actualText = (String) javascriptExecutor.executeScript("return arguments[0].value", inputField);
        Assert.assertEquals("Text is not correct in input field", expectedText, actualText);
    }

    @When("User enters and verifies {string} in the input text field with Selenium")
    public void userEntersTextInInputFieldWithSelenium(String expectedText) {
        WebElement shadowHost = driver.findElement(By.id("shadow_host"));
        SearchContext searchContext = shadowHost.getShadowRoot();
        WebElement inputField = searchContext.findElement(By.cssSelector("input[type='text']"));
        inputField.sendKeys(expectedText);
        String actualText = inputField.getAttribute("value");
        Assert.assertEquals("Text is not correct in input field", expectedText, actualText);
    }

    @When("User selects the checkbox and verifies if it was selected with Js")
    public void userSelectsCheckboxAndVerifiesSelectionWithJs() {
        WebElement shadowHost = driver.findElement(By.id("shadow_host"));
        SearchContext searchContext = (SearchContext) javascriptExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);
        WebElement checkbox = searchContext.findElement(By.cssSelector("input[type='checkbox']"));
        javascriptExecutor.executeScript("arguments[0].click()", checkbox);
        Boolean isChecked = (Boolean) javascriptExecutor.executeScript("return arguments[0].checked", checkbox);
        Assert.assertTrue("Checkbox is not selected", isChecked);
    }

    @When("User selects the checkbox and verifies if it was selected with Selenium")
    public void userSelectsCheckboxAndVerifiesSelectionWithSelenium() {
        WebElement shadowHost = driver.findElement(By.id("shadow_host"));
        SearchContext searchContext = shadowHost.getShadowRoot();
        WebElement checkbox = searchContext.findElement(By.cssSelector("input[type='checkbox']"));
        checkbox.click();
        Assert.assertTrue("Checkbox is not selected", checkbox.isSelected());
    }

    //3. (Assignment) optional
    public SearchContext getElementSearchContext(List<By> shadowHostList, By elementLocator) {
        WebElement currentShadowHost = null;
        //Iterate over the shadow roots, trying to find the element inside each shadow root until it is found
        for (By shadowHostLocator : shadowHostList) {
            if (currentShadowHost == null) {
                currentShadowHost = driver.findElement(shadowHostLocator);
            } else {
                // If we are already inside a shadow root, find the next shadow host inside it
                currentShadowHost = currentShadowHost.getShadowRoot().findElement(shadowHostLocator);
            }

            // Enter the shadow root context
            SearchContext shadowRootContext = (SearchContext) javascriptExecutor.executeScript("return arguments[0].shadowRoot", currentShadowHost);

            // We want to stop the iteration as soon as we find the element inside the current shadow root context,
            // So we should catch the NoSuchElementException and continue to the next shadow root
            try {
                return shadowRootContext.findElement(elementLocator);
            } catch (NoSuchElementException e) {
                // If the element is not found inside the current shadow root, continue to the next one
            }
        }
        // If the element was not found inside any of the shadow roots, throw an exception
        throw new NoSuchElementException("Element not found inside any of the shadow roots");
    }
}
