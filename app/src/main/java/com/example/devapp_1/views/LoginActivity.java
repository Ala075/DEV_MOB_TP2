package com.example.devapp_1.views;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devapp_1.Consultation_Activity;
import com.example.devapp_1.HistoryActivity;
import com.example.devapp_1.HomeActivity;
import com.example.devapp_1.R;
import com.example.devapp_1.controllers.LoginController;

public class LoginActivity extends AppCompatActivity {
    
    private EditText vm = null;
    

    private static LoginController loginController=LoginController.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              LoginController.createPatient(vmValue, ageValue, isFasting);res = LoginController.getResponse();
                if (validAge && validVm) {
                    resultat();
                }
            }
        });

        Button dev = findViewById(R.id.dev);
        dev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }
    public void  goBack(){
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    public void init() {
        age = findViewById(R.id.votreAge);
        vm = findViewById(R.id.vm);
        sbage = findViewById(R.id.sbAge);
        rbGrp = findViewById(R.id.rbGrp);
        rbnon=(RadioButton)findViewById(R.id.rbNon);
        rboui=(RadioButton)findViewById(R.id.rbOui);
        btn = findViewById(R.id.btn);
    }

    public void resultat() {
        // Créer une intention 
        Intent intent = new Intent(LoginActivity.this, Consultation_Activity.class);
        startActivity(intent);
    }



}
