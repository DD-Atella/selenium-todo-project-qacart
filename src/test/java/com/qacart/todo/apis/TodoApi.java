package com.qacart.todo.apis;

import com.qacart.todo.models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TodoApi {
    private static TodoApi todoApi;
    private void TodoPage(){}
    public static TodoApi getInstance(){
        if(todoApi == null){
            todoApi = new TodoApi();
        }
        return todoApi;
    }

    public Response addTodo(User user, String item){
        //given,when,then
      return  given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body("{\"item\": \""+item+"\", \"isCompleted\": false}")
              .auth().oauth2(user.getAccessToken())
        .when()
                .post("/api/v1/tasks")
        .then()
                .extract().response();
}
}
