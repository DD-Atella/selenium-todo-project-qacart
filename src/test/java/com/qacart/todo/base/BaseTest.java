package com.qacart.todo.base;

import com.qacart.todo.factory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BaseTest {
    protected ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setup(){
      WebDriver driver = new DriverFactory().initializeDriver();
      this.driver.set(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        String testCaseName = result.getMethod().getMethodName();
        File destFile = new File("target"+File.separator+ "screenShots" + File.separator + testCaseName+ ".png");
        takeScreenShot(destFile);
        driver.get().quit();
    }

    public void takeScreenShot(File destFile){
        File file = ((TakesScreenshot) driver.get()).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file,destFile);
            InputStream is = new FileInputStream(destFile);
            Allure.addAttachment("ScreenShot",is);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
