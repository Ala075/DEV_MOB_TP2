package com.example.devapp_1.views;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.devapp_1.Consultation_Activity;
import com.example.devapp_1.HistoryActivity;
import com.example.devapp_1.HomeActivity;
import com.example.devapp_1.R;
import com.example.devapp_1.controllers.LoginController;

public class LoginActivity extends AppCompatActivity {
    
    private EditText email = null;
    private EditText password = null;
    private Button btn = null;
    
    LoginController loginController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        String emailValue = loginController.getEmail();
        String passwordValue = loginController.getPassword(); 
      

        email.setText(emailValue);
        password.setText(passwordValue);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              boolean validEmail = false;
              boolean validPassword=false;

                if (!email.getText().toString().isEmpty()&&emailValue.equals(email.getText().toString())){
                    validEmail=true;
                }else {
                    Toast.makeText(LoginActivity.this,"verifier votre email",Toast.LENGTH_SHORT).show();
                }
                if (!password.getText().toString().isEmpty()&&passwordValue.equals(password.getText().toString())){
                    validPassword=true;
                }else {
                    Toast.makeText(LoginActivity.this,"verifier votre password",Toast.LENGTH_LONG).show();
                }
              if (validEmail && validPassword) {
                  resultat();
              }
            }
        });
        
                

    } 

    public void init() {
      
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        btn = findViewById(R.id.btn);
        loginController=LoginController.getInstance(this);
    }

    public void resultat() {
        // Créer une intention 
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }



}
