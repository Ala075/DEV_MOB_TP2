package com.example.devapp_1.models;

public class User {

    private String Email ;
    private String password;

    public User(String email, String password) {
        this.Email = email;
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public  String getPassword() {
        return password; 
    }



}
