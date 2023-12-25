package com.example.devapp_1.models;

public class User {

    private String Username ;
    private String password;

    public User(String username, String password) {
        this.Email = username;
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public  String getPassword() {
        return password;
    }



}
