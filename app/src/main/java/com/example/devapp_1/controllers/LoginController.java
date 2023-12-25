package com.example.devapp_1.controllers;

import android.content.Context;
import com.example.devapp_1.models.User;

public class LoginController {


    private static User user;

    private static LoginController instance;

    private LoginController() {
        super();
    }

    public static final LoginController getInstance(Context context) {
        if (LoginController.instance == null)
            instance = new LoginController();
        recapUser(context);
        return instance;
    }

    private static void recapUser (Context context) {Retrofit retrofit = ApiClient.getApiClient();
      ApiService apiService = retrofit.create(ApiService.class);

      Call<String> call = apiService.getUser();
      call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                user = new User("Error", "Error");
            }
        });
      
    }

    public String getEmail() { return user.getEmail();  }

    public String getPassword() { return user.getPassword(); }

}
