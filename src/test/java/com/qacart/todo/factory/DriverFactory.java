package com.qacart.todo.factory;

import org.bouncycastle.oer.Switch;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverFactory {

    public WebDriver initializeDriver(){
        WebDriver driver;
        String browser =  System.getProperty("browser","CHROME");// it will get the value from the terminal
        switch (browser){
             case "CHROME" -> {
                 ChromeOptions options  = new ChromeOptions();
                 options.addArguments("--headless"); // Run the test cases in headless mode [Without opening the browser]
                 driver = new ChromeDriver(options);
             }
             case "FIREFOX" -> {
                 driver = new FirefoxDriver();
             }
             case "SAFARI" -> {
                 driver = new SafariDriver();
             }
            default -> {
                 throw new RuntimeException("Browser is not supported");
            }
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));//implicit wait
        return driver;
    }
}
