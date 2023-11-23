package com.example.devapp_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.devapp_1.views.MainActivity;

public class HomeActivity extends AppCompatActivity {

    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_DEVELOPER = "DEVELOPER";

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Initialize SharedPreferences instance
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        TextView dev = findViewById(R.id.dev);
        Button btn = findViewById(R.id.btn);
        Button hist = findViewById(R.id.hist);


        // Retrieve the value stored with the key "DEVELOPER"
        String devName = sharedPreferences.getString(KEY_DEVELOPER, "Unknow");
        dev.setText(devName);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultat();
            }
        });
        hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        // Save the developer name to SharedPreferences
        saveDeveloperName("Ala Arfaoui");
    }

    private void saveDeveloperName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_DEVELOPER, name);
        editor.apply();
    }

    public void resultat() {
        // Create an intent to start the MainActivity
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);

        // Start the MainActivity
        startActivity(intent);
    }
    public void history() {
        // Create an intent to start the MainActivity
        Intent intent = new Intent(HomeActivity.this, HistoryActivity.class);

        // Start the MainActivity
        startActivity(intent);
    }
}
