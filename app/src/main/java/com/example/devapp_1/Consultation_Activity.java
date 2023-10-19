package com.example.devapp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Consultation_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        // Retrieve the data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            String result = ((Intent) intent).getStringExtra("result");

            // Now, you can use the 'result' variable in Const_Activity
            // For example, you can display it in a TextView
            TextView resultTextView = findViewById(R.id.res);
            resultTextView.setText(result);
        }

    }
}