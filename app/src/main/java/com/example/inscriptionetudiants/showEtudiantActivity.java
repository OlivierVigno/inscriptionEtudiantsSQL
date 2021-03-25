package com.example.inscriptionetudiants;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class showEtudiantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_etudiant);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_bar);

        TextView textActionBar = (TextView) findViewById(R.id.actionBar_text);
        textActionBar.setText("Inscription complété");
        Bundle bundle = getIntent().getBundleExtra("infos");

        TextView textNom = (TextView) findViewById(R.id.showNom);
        textNom.setText(bundle.getString("nom"));

        TextView textPrenom = (TextView) findViewById(R.id.showPrenom);
        textPrenom.setText(bundle.getString("prenom"));

        TextView textProvince = (TextView) findViewById(R.id.showProvince);
        textProvince.setText(bundle.getString("province"));

        TextView textVille = (TextView) findViewById(R.id.showVille);
        textVille.setText(bundle.getString("ville"));

        TextView textAdresse = (TextView) findViewById(R.id.showAdresse);
        textAdresse.setText(bundle.getString("noCivique") + ", " + bundle.getString("rue"));

        TextView textProgramme = (TextView) findViewById(R.id.showProgramme);
        textProgramme.setText(bundle.getString("programme"));

        Button retour = (Button) findViewById(R.id.buttonRetour);

        retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(showEtudiantActivity.this, MainActivity.class);
                textNom.setText("");
                textPrenom.setText("");
                textProvince.setText("");
                textVille.setText("");
                textAdresse.setText("");
                textProgramme.setText("");
                startActivity(intent);
            }
        });
    }
}