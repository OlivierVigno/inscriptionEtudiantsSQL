package com.example.inscriptionetudiants;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import helper.DatabaseHelper;
import model.Etudiant;

public class showEtudiantActivity extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_etudiant);

        db = DatabaseHelper.getInstance(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_bar);

        TextView textActionBar = (TextView) findViewById(R.id.actionBar_text);
        textActionBar.setText("Inscription complété");
        int id_etudiant = getIntent().getIntExtra("id_etudiant", 0);

        Etudiant etudiant  = new Etudiant();

        etudiant = db.getEtudiant(id_etudiant);

        TextView textNom = (TextView) findViewById(R.id.showNom);
        textNom.setText(etudiant.getNom());

        TextView textPrenom = (TextView) findViewById(R.id.showPrenom);
        textPrenom.setText(etudiant.getPrenom());

        TextView textProvince = (TextView) findViewById(R.id.showProvince);
        textProvince.setText(db.getName(etudiant.getProvince(), "province"));

        TextView textVille = (TextView) findViewById(R.id.showVille);
        textVille.setText(db.getName(etudiant.getVille(), "ville"));

        TextView textAdresse = (TextView) findViewById(R.id.showAdresse);
        textAdresse.setText(etudiant.getNoCivique() + ", " + etudiant.getRue());

        TextView textProgramme = (TextView) findViewById(R.id.showProgramme);
        textProgramme.setText(db.getName(etudiant.getProgramme(), "programme"));

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