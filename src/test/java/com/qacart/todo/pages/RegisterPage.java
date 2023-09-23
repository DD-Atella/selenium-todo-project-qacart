package com.qacart.todo.pages;

import com.qacart.todo.apis.UserApi;
import com.qacart.todo.models.User;
import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private static RegisterPage registerPage;

    //Elements : all the elements in the page
    private final By firstNameInput = By.cssSelector("[data-testid=\"first-name\"]");
    private final By lastNameInput = By.cssSelector("[data-testid=\"last-name\"]");
    private final By emailInput = By.cssSelector("[data-testid=\"email\"]");
    private final By passwordInput = By.cssSelector("[data-testid=\"password\"]");
    private final By confirmPasswordInput =By.cssSelector("[data-testid=\"confirm-password\"]");
    private final By submitButton = By.cssSelector("[data-testid=\"submit\"]");

    //Constructor
    private RegisterPage(){}

    public static RegisterPage getInstance(){
        if(registerPage == null){
            registerPage = new RegisterPage(); // if it's null then define a new object
        }
        return registerPage;
    }

    //Methods,steps

    @Step("Visit the signup page")
    public void load(WebDriver driver){
        driver.get(ConfigUtils.getInstanse().getBaseURL() +"/signup");
    }
    @Step("Register using the UI")
    public void register(WebDriver driver, User user){
        driver.findElement(firstNameInput).sendKeys(user.getFirstName());
        driver.findElement(lastNameInput).sendKeys(user.getLastName());
        driver.findElement(emailInput).sendKeys(user.getEmail());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(confirmPasswordInput).sendKeys(user.getPassword());
        driver.findElement(submitButton).click();
    }

    @Step("Register using the API")
    public void registerUsinApi(WebDriver driver,User user) {
        Response res =  UserApi.getInstance().register(user);
        String access_token = res.path("access_token");
        String userID = res.path("userID");
        String userName = res.path("firstName");
        user.setAccessToken(access_token);//Set the access token to use it in the TodoApi
        //Sending the response to the cookie to register
        Cookie accessTokenCookie = new Cookie("access_token",access_token);
        Cookie userIDCookie = new Cookie("userID",userID);
        Cookie userNameCookie = new Cookie("firstName",userName);
        //Inject the cookie into the browser
        //RegisterPage.getInstance().load(driver);
        driver.manage().addCookie(accessTokenCookie);
        driver.manage().addCookie(userIDCookie);
        driver.manage().addCookie(userNameCookie);
        RegisterPage.getInstance().load(driver);
    }
}
