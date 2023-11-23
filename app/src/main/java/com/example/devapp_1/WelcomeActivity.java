package com.example.devapp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.devapp_1.views.MainActivity;

public class WelcomeActivity extends AppCompatActivity {

    Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        resultat();
    }

    public void resultat() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an intent to start the MainActivity
                Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);

                // Start the MainActivity
                startActivity(intent);
                finish();
            }
        },3000);

    }

}