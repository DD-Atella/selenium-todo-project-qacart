package com.qacart.todo.testcases;
import com.qacart.todo.base.BaseTest;
import com.qacart.todo.models.User;
import com.qacart.todo.pages.NewTodoPage;
import com.qacart.todo.pages.RegisterPage;
import com.qacart.todo.pages.TodoPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ToDoTest extends BaseTest {
    @Test (description = "Should be able to add a todo")
    public void shouldBeAbleToAddATodo(){
        User user = new User(); // the empty constructor have the firstName, lastName, email & password
        RegisterPage.getInstance().load(driver.get()); //Driver load step
        RegisterPage.getInstance().registerUsinApi(driver.get(),user); // Registration step using API
        TodoPage.getInstanse().clickOnPlusButton(driver.get());
        NewTodoPage.getInstance().addTodo(driver.get(), "Learn Selenium");
        TodoPage.getInstanse().getTodoText(driver.get());
    }

    @Test (description = "Should be able to delete a todo")
    public void shouldBeAbleToDeleteATodo() {
        User user = new User(); // the empty constructor have the firstName, lastName, email & password
        RegisterPage.getInstance().load(driver.get());
        RegisterPage.getInstance().registerUsinApi(driver.get(),user); // Registration step using API
        NewTodoPage.getInstance().addTodoUsingApi(user,"Learn Selenium");
        TodoPage.getInstanse().load(driver.get());
        TodoPage.getInstanse().deleteTodo(driver.get());
        boolean isNoTodosDisplayed =  TodoPage.getInstanse().isNoTodoMessageDisplayed(driver.get());
        Assert.assertTrue(isNoTodosDisplayed);
    }
}
