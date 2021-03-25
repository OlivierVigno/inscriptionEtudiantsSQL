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

        db = new DatabaseHelper(getApplicationContext());

        Province province1 = new Province("Québec");
        int id_provinceQuebec = db.createProvince(province1);
        Ville ville1 = new Ville("Sherbrooke", id_provinceQuebec);

        /*
        Province province1 = new Province("Québec");
        Province province2 = new Province("Ontario");

        Long id_provinceQuebec = db.createProvince(province1);
        Long id_provinceOntario = db.createProvince(province2);

        Ville ville1 = new Ville("Sherbrooke", id_provinceQuebec);
        Ville ville2 = new Ville("Magog", id_provinceQuebec);
        Ville ville3 = new Ville("Drummondville", id_provinceQuebec);
        Ville ville4 = new Ville("Montréal", id_provinceQuebec);

        Ville ville5 = new Ville("Toronto", id_provinceOntario);
        Ville ville6 = new Ville("Ottawa", id_provinceOntario);
        Ville ville7 = new Ville("Kingston", id_provinceOntario);

        List<Ville> listeVilles = Arrays.asList(ville1, ville2, ville3, ville4, ville5, ville6, ville7);

        for (Ville ville : listeVilles) {
            db.createVille(ville);
        }

        Programme programme1 = new Programme("Gestion de réseaux");
        Programme programme2 = new Programme("Conception et programmation");

        List<Programme> listeProgrammes = Arrays.asList(programme1, programme2);

        for (Programme programme : listeProgrammes) {
            db.createProgramme(programme);
        }
        */

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_bar);

        TextView textActionBar = (TextView) findViewById(R.id.actionBar_text);
        textActionBar.setText("Inscription au programme");

        spinnerProvince = (Spinner) findViewById(R.id.spnProvince);
        spinnerVille = (Spinner) findViewById(R.id.spnVille);
        spinnerProgramme = (Spinner) findViewById(R.id.spnProgramme);

        //String[] ville = db.getVilles("Québec");

        //Button inscription = (Button) findViewById(R.id.buttonCompleterInscription);
        //inscription.setText(ville[0]);

        String[] ville = {"QC", "ON"};
        String[] programme = {"QC", "ON"};
        String[] province = {"QC", "ON"};

        ArrayAdapter<String> adapterProvince = new ArrayAdapter<String>(inscriptionFormActivity.this, android.R.layout.simple_spinner_item, programme);
        adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvince.setAdapter(adapterProvince);

        ArrayAdapter<String> adapterProgramme = new ArrayAdapter<String>(inscriptionFormActivity.this, android.R.layout.simple_spinner_item, programme);
        adapterProgramme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProgramme.setAdapter(adapterProgramme);

        ArrayAdapter<String> adapterVille = new ArrayAdapter<String>(inscriptionFormActivity.this, android.R.layout.simple_spinner_item, province);
        adapterVille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVille.setAdapter(adapterVille);

        /*
        ArrayAdapter<CharSequence> adapterProvince = ArrayAdapter.createFromResource(this, R.array.provinces, android.R.layout.simple_spinner_item);
        adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapterProgramme = ArrayAdapter.createFromResource(this, R.array.programmes, android.R.layout.simple_spinner_item);
        adapterProvince.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedProvince = parent.getItemAtPosition(position).toString();
                /*switch (selectedProvince) {
                    case "Québec":
                        spinnerVille.setAdapter(new ArrayAdapter<String>(inscriptionFormActivity.this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.villes_Quebec)));
                        break;
                    case "Ontario":
                        spinnerVille.setAdapter(new ArrayAdapter<String>(inscriptionFormActivity.this, R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.villes_Ontario)));
                        break;
                }
                ArrayAdapter<String> adapterVille = new ArrayAdapter<String>(inscriptionFormActivity.this, android.R.layout.simple_spinner_item, db.getVilles(selectedProvince));
                adapterVille.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerVille.setAdapter(adapterVille);
            }*/
            /*
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                // can leave this empty
            }
        });*/

        Button inscription = (Button) findViewById(R.id.buttonCompleterInscription);
        Button clear = (Button) findViewById(R.id.buttonClear);
        Button cancel = (Button) findViewById(R.id.buttonCancel);

        inscription.setOnClickListener(this);
        clear.setOnClickListener(this);
        cancel.setOnClickListener(this);

        //db.closeDB();
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

                Bundle bundle = new Bundle();
                bundle.putString("prenom", prenom.getText().toString());
                bundle.putString("nom", nom.getText().toString());
                bundle.putString("noCivique", noCivique.getText().toString());
                bundle.putString("rue", rue.getText().toString());
                bundle.putString("telephone", telephone.getText().toString());
                bundle.putString("province", selectedProvince);
                bundle.putString("ville", selectedVille);
                bundle.putString("programme", selectedProgramme);
                intentInformations.putExtra("infos", bundle);

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