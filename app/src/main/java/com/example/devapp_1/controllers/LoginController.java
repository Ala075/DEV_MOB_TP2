package com.example.devapp_1.controllers;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.devapp_1.models.User;

public class LoginController {


    private static User user;

    private static LoginController instance;
    private static final String shared_Pref = "sharedPref";

    private LoginController() {
        super();
    }

    public static final LoginController getInstance(Context context) {
        if (LoginController.instance == null)
            instance = new LoginController();
        recapUser(context);
        return instance;
    }

    private static void recapUser (Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(shared_Pref, Context.MODE_PRIVATE);
        String Username = sharedPreferences.getString("Username","");
        String Password = sharedPreferences.getString("password","");
        user = new User(Username, Password);
    }

    public void CreateUser(String Username, String password, Context context) {
        //création de l'utilisateur
        user = new User(Username, password);
        //persistance de données de l'utilisateur
        SharedPreferences sharedPreferences = context.getSharedPreferences(shared_Pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username" , Username);
        editor.putString("password" ,password);
        editor.apply();
    }

    public String getUsername() { return user.getUsername();  }

    public String getPassword() { return user.getPassword(); }
}
