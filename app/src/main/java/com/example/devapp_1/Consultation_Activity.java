package com.example.devapp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.devapp_1.controllers.HistoryController;

public class Consultation_Activity extends AppCompatActivity {

    private String result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        // Retrieve the data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            result = intent.getStringExtra("result");

            // Now, you can use the 'result' variable in Consultation_Activity
            // For example, you can display it in a TextView
            TextView resultTextView = findViewById(R.id.res);
            resultTextView.setText(result);

            // Add the result to the SQLite database
            addHistoryToDatabase(result);
        }

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void addHistoryToDatabase(String result) {
      // Initialize the controller
      HistoryController controller = HistoryController.getInstance(this);
      controller.openForWrite();

      // Get the next available id from SharedPreferences
      int nextId = getAndIncrementNextId();

      // Add the result to the database with a dynamic username
      controller.addHistory("user" + nextId, result);

      // Close the database
      controller.close();
    }

    private int getAndIncrementNextId() {
      // Retrieve the next available id from SharedPreferences
      SharedPreferences sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);
      int nextId = sp.getInt("id", 0);

      // Increment and save the next id
      SharedPreferences.Editor editor = sp.edit();
      editor.putInt("id", nextId + 1);
      editor.apply();

      return nextId;
    }


    public void goBack() {
        Intent intent = new Intent();
        if (result != null) {
            setResult(RESULT_OK, intent);
        } else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }
}
