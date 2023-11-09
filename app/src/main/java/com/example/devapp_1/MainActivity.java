package com.example.devapp_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView age = null, res = null;
    private SeekBar sbage = null;
    private RadioGroup rbGrp = null;
    private RadioButton rboui = null, rbnon = null;
    private EditText vm = null;
    private Button btn = null;
    private boolean jeuner;

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

                    if (rboui.isChecked()) {
                        if (ageValue >= 13) {
                            if (vmValue < 5.0) {
                                res.setText("Niveau de glycémie est bas");
                            } else if (vmValue >= 5.0 && vmValue <= 7.2) {
                                res.setText("Niveau de glycémie est normal");
                            } else {
                                res.setText("Niveau de glycémie est trop élevé");
                            }
                        } else if (ageValue >= 6 && ageValue <= 12) {
                            if (vmValue < 5.0) {
                                res.setText("Niveau de glycémie est trop bas");
                            } else if (vmValue >= 5.0 && vmValue <= 10.0) {
                                res.setText("Niveau de glycémie est normal");
                            } else {
                                res.setText("Niveau de glycémie est trop élevé");
                            }
                        } else {
                            if (vmValue < 5.5) {
                                res.setText("Niveau de glycémie est trop bas");
                            } else if (vmValue >= 5.5 && vmValue <= 10.0) {
                                res.setText("Niveau de glycémie est normal");
                            } else {
                                res.setText("Niveau de glycémie est trop élevé");
                            }
                        }
                    } else if (vmValue < 10.5) {
                        res.setText("Niveau de glycémie est normal");

                    } else {
                        res.setText("Niveau de glycémie est élevé");

                    }
                }

                // Effacer les champs après le traitement
                vm.setText("");
                sbage.setProgress(0);
                resultat();
            }
        });
    }

    public void init() {
        age = findViewById(R.id.votreAge);
        vm = findViewById(R.id.vm);
        sbage = findViewById(R.id.sbAge);
        res = findViewById(R.id.res);
        rbGrp = findViewById(R.id.rbGrp);
        rbnon=(RadioButton)findViewById(R.id.rbNon);
        rboui=(RadioButton)findViewById(R.id.rbOui);
        btn = findViewById(R.id.btn);
    }

    public void resultat() {
        // Créer une intention pour démarrer l'activité Consultation_Activity
        Intent intent = new Intent(MainActivity.this, Consultation_Activity.class);

        // Transmettre la valeur de 'res' à Consultation_Activity
        intent.putExtra("result", res.getText().toString());

        // Démarrer l'activité Consultation_Activity
        startActivity(intent);
    }
}
