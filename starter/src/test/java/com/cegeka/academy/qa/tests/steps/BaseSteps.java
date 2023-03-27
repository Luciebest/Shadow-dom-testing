package com.cegeka.academy.qa.tests.steps;

import com.cegeka.academy.qa.configurations.FrameworkConfiguration;
import com.cegeka.academy.qa.pageobjects.LoginPage;
import com.cegeka.academy.qa.pageobjects.ProductsPage;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = FrameworkConfiguration.class)
public class BaseSteps {
    @Autowired
    protected LoginPage loginPage;
    @Autowired
    protected ProductsPage productsPage;
}
