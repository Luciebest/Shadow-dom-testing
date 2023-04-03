package com.cegeka.academy.qa.tests.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class PracticeSteps {

    @Autowired
    private WebDriver driver;

    @Given("User opens page {string}")
    public void iOpenPage(String url) {
        driver.get(url);
    }

    @When("User checks Sports on DemoQa Practice Form")
    public void userChecksSportsOnDemoQaPracticeForm() {
        By footer = By.tagName("footer");
        int footerHeight = driver.findElement(footer).getRect().getDimension().getHeight();
        By by = By.cssSelector("#hobbiesWrapper .custom-checkbox:nth-child(1) .custom-control-label");
        new Actions(driver).moveToElement(driver.findElement(by)).perform();
        try {
            getFluentWait().until(ExpectedConditions.elementToBeClickable(by)).click();
        } catch (ElementClickInterceptedException ex) {
            new Actions(driver).scrollByAmount(0, footerHeight + 10).perform();
            waitInMillis(500);
            getFluentWait().until(ExpectedConditions.elementToBeClickable(by)).click();
        }
    }

    private static void waitInMillis(long millis) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }

    }

    @Then("User verifies Sports is selected")
    public void sportsIsChecked() {
        WebElement sportsCheckBox = driver.findElement(By.cssSelector("#hobbiesWrapper .custom-checkbox:nth-child(1) .custom-control-input"));
        Assert.assertTrue("Sports is not selected", sportsCheckBox.isSelected());
    }

    @Then("User verifies Sports is not selected")
    public void sportsIsNotChecked() {
        WebElement sportsCheckBox = driver.findElement(By.cssSelector("#hobbiesWrapper .custom-checkbox:nth-child(1) .custom-control-input"));
        Assert.assertFalse("Sports is selected", sportsCheckBox.isSelected());
    }


    @When("User selects option number {int} in select menu")
    public void userSelectsOptionNumberInSelectMenu(int optionNumber) {
        By by = By.id("oldSelectMenu");
        WebElement selectWebElement = getFluentWait().until(ExpectedConditions.elementToBeClickable(by));
        Select selectElement = new Select(selectWebElement);
        List<String> optionsTexts = selectElement.getOptions().stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println(optionsTexts);
        selectElement.selectByIndex(optionNumber - 1);


    }


    @Then("Select menu option {string} was verified")
    public void selectMenuOptionWasVerified(String expectedOptionText) {
        By by = By.id("oldSelectMenu");
        Select selectElement = new Select(driver.findElement(by));
        String actualSelectedOptionText = selectElement.getAllSelectedOptions().stream().findFirst().map(WebElement::getText).orElse("");
        Assert.assertEquals("Incorrect option was selected", expectedOptionText, actualSelectedOptionText);


    }

    @When("User selects option {string} in select menu")
    public void userSelectsOptionInSelectMenu(String optionByName) {
        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByVisibleText(optionByName);
    }

    @When("User selects Male")
    public void userSelectsMale() {
        By by1 = By.cssSelector("#genterWrapper .custom-radio:nth-child(1) .custom-control-label");
//        By by = By.id("gender-radio-1");
//        getFluentWait().until(ExpectedConditions.elementToBeSelected(by));
        By footer = By.tagName("footer");
        int footerHeight = driver.findElement(footer).getRect().getDimension().getHeight();
        new Actions(driver).moveToElement(driver.findElement(by1));
        try {
            driver.findElement(by1).click();
        } catch (ElementClickInterceptedException ex) {
            new Actions(driver).scrollByAmount(0, footerHeight + 10).perform();
            waitInMillis(500);
            driver.findElement(by1).click();
        }

    }

    @Then("User verifies Male is selected")
    public void userVerifiesMaleIsSelected() {
        By by = By.id("gender-radio-1");
        Boolean isSelected = getFluentWait().until(ExpectedConditions.elementSelectionStateToBe(by, true));
        Assert.assertTrue("Male is not selected", isSelected);
    }

    private FluentWait<WebDriver> getFluentWait() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(8))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        return wait;
    }

    private WebDriverWait getExplicitWait() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return webDriverWait;
    }


}

