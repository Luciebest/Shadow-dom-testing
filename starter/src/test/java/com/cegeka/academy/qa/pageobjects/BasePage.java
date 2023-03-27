package com.cegeka.academy.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class BasePage {

    @Value("${base.url:}")
    private String baseUrl;

//   Field injection
   @Autowired
    protected WebDriver driver;

//   Constructor injection
//   Autowired is not mandatory on constructor from spring5
//    @Autowired
//    public BasePage(WebDriver driver) {
//        this.driver = driver;
//        PageFactory.initElements(driver, this);
//    }

    @PostConstruct
    public void init() {
        System.out.println("Initializing page for class" + this.getClass());
        PageFactory.initElements(driver, this);
    }

    public void goToStartPage() {
        driver.get(baseUrl);
    }

}
