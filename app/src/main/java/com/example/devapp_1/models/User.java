package com.example.devapp_1.models;

public class User {

    private String Username ;
    private String password;

    public User(String username, String password) {
        this.Username = username;
        this.password = password;
    }

    public String getUsername() {
        return Username;
    }

    public  String getPassword() {
        return password;
    }



}