package com.qacart.todo.pages;

import com.qacart.todo.utils.ConfigUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TodoPage {

    private static TodoPage todoPage; // related to the singleton design

    //Elements : all the elements in the page
    private final By welcomMessage = By.cssSelector("[data-testid=\"welcome\"]");
    private final By plusButton = By.cssSelector("[data-testid=\"add\"]");
    private final By todoItem = By.cssSelector("[data-testid=\"todo-item\"]");
    private final By deleteIcon = By.cssSelector("[data-testid=\"delete\"]");
    private final By noToDoMessage = By.cssSelector("[data-testid=\"no-todos\"]");

    //Constructor
    public TodoPage(){}

    public static TodoPage getInstanse(){
        if(todoPage == null){
            todoPage = new TodoPage();
        }
        return todoPage;
    }
    @Step("Visiting the todo page")
    public void load(WebDriver driver){
        driver.get(ConfigUtils.getInstanse().getBaseURL() +"/todo");
    }

    //Methods ,steps
    //The welcome message check
    @Step("Check if the welcome message is displayed")
    public boolean isWelcomeMessageDisplayed(WebDriver driver){
       return  driver.findElement(welcomMessage).isDisplayed();
    }

    @Step("Click on the plus button")
    public void clickOnPlusButton(WebDriver driver){
        driver.findElement(plusButton).click();
    }

    @Step("Get the text of the todo")
    public boolean getTodoText(WebDriver driver){
        return driver.findElement(By.cssSelector("[data-testid=\"todo-item\"]")).isDisplayed();
    }

    @Step("Click om the delete icon")
    public void deleteTodo(WebDriver driver){
        driver.findElement(deleteIcon).click();
    }

    @Step("Check if the No todo's message displayed")
    public boolean isNoTodoMessageDisplayed(WebDriver driver){
         return driver.findElement(noToDoMessage).isDisplayed();
    }

}
