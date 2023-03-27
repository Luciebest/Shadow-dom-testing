package com.cegeka.academy.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class LoginPage extends BasePage {


    @FindBy(css = "[data-test=username]")
    private WebElement usernameInput;
    @FindBy(css = "[data-test=password]")
    private WebElement passwordInput;
    @FindBy(id = "login-button")
    private WebElement loginButton;

//    public LoginPage(WebDriver driver) {
//        super(driver);
//    }
    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }
    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public ProductsPage clickLoginButton() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

        return new ProductsPage(/*driver*/);

    }
}
