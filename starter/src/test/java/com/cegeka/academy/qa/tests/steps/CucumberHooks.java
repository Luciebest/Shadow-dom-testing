package com.cegeka.academy.qa.tests.steps;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class CucumberHooks {

    //IntelliJ shows this @Autowired driver highlighted as red because @Autowired doesn't normally work in classes that are not spring beans
    //It DOES work, however because you have a "glue" class annotated with Cucumber's @CucumberContextConfiguration  and Spring's @ContextConfiguration where you point to context configuration class. Take a look at BaseSteps and you find them there.
    @Autowired
    private WebDriver driver;

    @Before
    public void setupBeforeScenario() {
        System.out.println("Perform before scenario cucumber hook");
        System.out.println("Driver instance " +  driver);
    }

}
