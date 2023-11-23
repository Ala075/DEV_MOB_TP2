package com.example.devapp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        // Move SharedPreferences initialization here
        sp = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Get the total number of consultations
        int totalConsultations = sp.getInt("id", 0);

        // Access the TableLayout defined in your XML and apply the style
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        for (int i = 1; i <= totalConsultations; i++) {
            // Create a new row and apply the style
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT)

            );
            row.setPadding(0,0,0,20);

            // Create a TextView for ID and apply the style
            TextView idTextView = new TextView(this);
            idTextView.setText(String.valueOf(i));
            idTextView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            idTextView.setGravity(Gravity.CENTER);
            idTextView.setPadding(0,0,50,0);
            idTextView.setTextSize(20);
            //idTextView.setTextAppearance(this, R.style.TextViewStyle);

            // Create a TextView for Result and apply the style
            TextView resultTextView = new TextView(this);
            String result = sp.getString("result" + i, "");
            resultTextView.setText(result);
            resultTextView.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            resultTextView.setGravity(Gravity.CENTER);
            //resultTextView.setTextAppearance(this, R.style.TextViewStyle);

            // Add the TextViews to the row
            row.addView(idTextView);
            row.addView(resultTextView);

            // Add the row to the TableLayout
            tableLayout.addView(row);
        }

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    public void goBack() {
        Intent intent = new Intent(HistoryActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
