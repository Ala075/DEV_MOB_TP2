package com.example.devapp_1.views;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
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
import com.example.devapp_1.controllers.Controller;

public class MainActivity extends AppCompatActivity {
    private TextView age = null;
    String res;
    private SeekBar sbage = null;
    private RadioGroup rbGrp = null;
    private RadioButton rboui = null, rbnon = null;
    private EditText vm = null;
    private Button btn = null;
    private boolean isFasting;

    private static Controller controller=Controller.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        sbage.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Mettre à jour le TextView avec la valeur actuelle du progrès
                age.setText("Votre Age: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Arrêt du suivi tactile", Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ageValue;
                double vmValue;
                boolean validAge = false, validVm = false;

                if (sbage.getProgress() != 0) {
                    validAge = true;
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez vérifier votre âge", Toast.LENGTH_SHORT).show();
                }

                if (!vm.getText().toString().isEmpty()) {
                    validVm = true;
                } else {
                    Toast.makeText(MainActivity.this, "Veuillez vérifier votre valeur mesurée", Toast.LENGTH_LONG).show();
                }

                if (validAge && validVm) {
                    ageValue = sbage.getProgress();
                    vmValue = Double.valueOf(vm.getText().toString());

                    controller.createPatient(vmValue, ageValue, isFasting);

                    res = controller.getResponse();
                }

                // Effacer les champs après le traitement
                vm.setText("");
                sbage.setProgress(0);
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
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
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
        // Créer une intention pour démarrer l'activité Consultation_Activity
        Intent intent = new Intent(MainActivity.this, Consultation_Activity.class);

        // Transmettre la valeur de 'res' à Consultation_Activity
        intent.putExtra("result", res);

        // Démarrer l'activité Consultation_Activity
        activityResultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        Toast.makeText(getApplicationContext(), "OK, Consultation saved !", Toast.LENGTH_SHORT).show();
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        Toast.makeText(getApplicationContext(), "Failed, wrong result", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

}
