package com.cegeka.academy.qa.configurations;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PreDestroy;
import java.time.Duration;

@Configuration
@ComponentScan(basePackages = "com.cegeka.academy.qa")
@PropertySource("classpath:framework.properties")
public class FrameworkConfiguration {

    private WebDriver driver;

    @Bean
    WebDriver getDriver() {
        driver = getChrome();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    private ChromeDriver getChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Maria\\chromedrivers\\chromedriver111.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
//        chromeOptions.addArguments("--start-maximized");

        return  new ChromeDriver(chromeOptions);
    }


    @PreDestroy
    public void destroy() {
        if (driver != null)  {
            driver.quit();
            System.out.println("Driver quit was called");
        }
        else System.out.println("Cannot quit driver because it's already null");
    }

}
