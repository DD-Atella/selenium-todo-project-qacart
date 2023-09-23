package com.qacart.todo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.javafaker.Faker;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @JsonIgnore // It will ignore the below variable from the request body [serialization]
    private String accessToken;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(){
        Faker faker = new Faker();
        String firstName= faker.name().firstName(); // Return random fake first name
        String lastName= faker.name().lastName(); // Return random fake last name
        String email = faker.internet().safeEmailAddress(); // Return fake random email
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.password="Test@123";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
