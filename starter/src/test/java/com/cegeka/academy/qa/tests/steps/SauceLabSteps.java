package com.cegeka.academy.qa.tests.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class SauceLabSteps extends BaseSteps{

    @Given("Login page is opened")
    public void loginPageIsOpened() {
        loginPage.goToStartPage();
    }

    @When("Username and password {string} and {string} are entered")
    public void usernameAndPasswordAndAreEntered(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @Then("Products page is displayed")
    public void productsPageIsDisplayed() {
        Assert.assertTrue("Products are not displayed", productsPage.isProductsContainerDisplayed());
    }

    @And("Login is attempted")
    public void loginIsAttempted() {
        loginPage.clickLoginButton();
    }


}
