package com.example.my_application.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {
//USER
    private String userId;

    public LoggedInUser(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}