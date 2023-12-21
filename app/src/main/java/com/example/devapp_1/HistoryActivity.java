package com.example.devapp_1;

import androidx.appcompat.app.AppCompatActivity;

import com.example.devapp_1.controllers.HistoryController;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private HistoryController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);

        // Initialize the controller
        controller = HistoryController.getInstance(this);

        // Access the TableLayout defined in your XML and apply the style
        TableLayout tableLayout = findViewById(R.id.tableLayout);

        ArrayList<History> historyList = controller.getAllHistories();

        for (int i = 0; i < historyList.size(); i++) {
          History history = historyList.get(i);

    
          // Create a new row and apply the style
          TableRow row = new TableRow(this);
          row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT));
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
            
            // Create a TextView for Username and apply the style
    TextView usernameTextView = new TextView(this);
    usernameTextView.setText(history.getUsername());
    usernameTextView.setLayoutParams(new TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT));
    usernameTextView.setGravity(Gravity.CENTER);

    // Create a TextView for Consultation and apply the style
    TextView consultationTextView = new TextView(this);
    consultationTextView.setText(history.getConsultation());
    consultationTextView.setLayoutParams(new TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT));
    consultationTextView.setGravity(Gravity.CENTER);

            // Add the TextViews to the row
            row.addView(idTextView);
            row.addView(usernameTextView);
            row.addView(consultationTextView);

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
