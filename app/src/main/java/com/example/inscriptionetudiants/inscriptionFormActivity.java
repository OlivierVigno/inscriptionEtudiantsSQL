package com.example.inscriptionetudiants;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import helper.DatabaseHelper;
import model.Etudiant;
import model.Programme;
import model.Province;
import model.Ville;

public class inscriptionFormActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper db;

    private Spinner spinnerProvince;
    private Spinner spinnerVille;
    private Spinner spinnerProgramme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription_form);

        getApplicationContext().deleteDatabase("myDatabase");

        db = DatabaseHelper.getInstance(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_bar);

        TextView textActionBar = (TextView) findViewById(R.id.actionBar_text);
        textActionBar.setText("Inscription au programme");

        spinnerProvince = (Spinner) findViewById(R.id.spnProvince);
        spinnerVille = (Spinner) findViewById(R.id.spnVille);
        spinnerProgramme = (Spinner) findViewById(R.id.spnProgramme);

        ArrayAdapter<String> adapterProvince = new ArrayAdapter<String>(inscriptionFormActivity.this, android.R.layout.simple_spinner_item, db.getProvinces());
        adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapterProvince);

        ArrayAdapter<String> adapterProgramme = new ArrayAdapter<String>(inscriptionFormActivity.this, android.R.layout.simple_spinner_item, db.getProgrammes());
        adapterProgramme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProgramme.setAdapter(adapterProgramme);

        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String[] villes = {};
                String selectedProvince = parent.getItemAtPosition(position).toString();
                villes = db.getVilles(selectedProvince);
                ArrayAdapter<String> adapterVille = new ArrayAdapter<String>(inscriptionFormActivity.this, android.R.layout.simple_spinner_item, villes);
                adapterVille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerVille.setAdapter(adapterVille);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });

        Button inscription = (Button) findViewById(R.id.buttonCompleterInscription);
        Button clear = (Button) findViewById(R.id.buttonClear);
        Button cancel = (Button) findViewById(R.id.buttonCancel);

        inscription.setOnClickListener(this);
        clear.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCompleterInscription:
                Intent intentInformations = new Intent(inscriptionFormActivity.this, progressBarActivity.class);

                EditText nom = (EditText) findViewById(R.id.txtNom);
                EditText prenom = (EditText) findViewById(R.id.txtPrenom);
                EditText noCivique = (EditText) findViewById(R.id.txtNoCivique);
                EditText rue = (EditText) findViewById(R.id.txtRue);
                EditText telephone = (EditText) findViewById(R.id.txtTelephone);
                String selectedProvince = spinnerProvince.getSelectedItem().toString();
                String selectedVille = spinnerVille.getSelectedItem().toString();
                String selectedProgramme = spinnerProgramme.getSelectedItem().toString();

                System.out.println(selectedProgramme);

                Etudiant etudiant = new Etudiant();

                etudiant.setNom(nom.getText().toString());
                etudiant.setPrenom(prenom.getText().toString());
                etudiant.setNoCivique(Integer.parseInt(noCivique.getText().toString()));
                etudiant.setRue(rue.getText().toString());
                etudiant.setTelephone(telephone.getText().toString());
                etudiant.setProvince(db.getId(selectedProvince, "province"));
                etudiant.setVille(db.getId(selectedVille, "ville"));
                etudiant.setProgramme(db.getId(selectedProgramme, "programme"));

                int idEtudiant = db.createEtudiant(etudiant);
                intentInformations.putExtra("id_etudiant", idEtudiant);
                startActivity(intentInformations);
                break;
            case R.id.buttonClear:
                clearForm((ViewGroup) findViewById(R.id.layoutForm));
                break;
            case R.id.buttonCancel:
                Intent intentCancel = new Intent(inscriptionFormActivity.this, MainActivity.class);
                startActivity(intentCancel);
                break;
        }
    }

    private void clearForm(ViewGroup group) {
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            View view = group.getChildAt(i);
            if (view instanceof EditText) {
                ((EditText)view).setText("");
            }

            if(view instanceof ViewGroup && (((ViewGroup)view).getChildCount() > 0))
                clearForm((ViewGroup)view);
        }
    }

}