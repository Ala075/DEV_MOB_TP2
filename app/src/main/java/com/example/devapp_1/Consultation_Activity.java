package com.example.devapp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.devapp_1.views.MainActivity;

public class Consultation_Activity extends AppCompatActivity {

    SharedPreferences sp;
    private int id = 0;

    String result = null;

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
        }

        sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int nextId = sp.getInt("id", 0) + 1;
        editor.putInt("id", nextId);
        editor.putString("result" + nextId, result);
        editor.apply();

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    public void goBack() {
        Intent intent = new Intent();
        if(result != null){
            setResult(RESULT_OK,intent);
        }else {
            setResult(RESULT_CANCELED,intent);
        }
        finish();
    }
}
