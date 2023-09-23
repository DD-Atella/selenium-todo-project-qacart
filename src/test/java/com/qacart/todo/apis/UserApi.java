package com.qacart.todo.apis;

import com.qacart.todo.models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static UserApi userApi;
    private UserApi(){}
    public static UserApi getInstance(){
        if(userApi == null){
            userApi = new UserApi();
        }
        return userApi;
    }
    public Response register(User user){
        return  given()
                .baseUri("https://todo.qacart.com")
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post("/api/v1/users/register")
                .then()
                // .log().all(); //print the response
                .extract().response();
    }
}