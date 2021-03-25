package com.example.inscriptionetudiants;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_bar);

        TextView textActionBar = (TextView) findViewById(R.id.actionBar_text);
        textActionBar.setText("Bienvenue aux inscriptions du programme de Techniquesde l'informatique du Cégep de Sherbrooke");

        Button informationsProgrammes = (Button) findViewById(R.id.buttonInformationsProgrammes);
        Button inscription = (Button) findViewById(R.id.buttonInscription);
        Button fin = (Button) findViewById(R.id.buttonFin);

        informationsProgrammes.setOnClickListener(this);
        inscription.setOnClickListener(this);
        fin.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.buttonInformationsProgrammes:
                    Intent intentInformations = new Intent(MainActivity.this, informationsActivity.class);
                    startActivityForResult(intentInformations, 0);
                    break;
                case R.id.buttonInscription:
                    Intent intentInscription = new Intent(MainActivity.this, inscriptionFormActivity.class);
                    startActivityForResult(intentInscription, 1);
                    break;
                case R.id.buttonFin:
                    finish();
                    moveTaskToBack(true);
                    break;
        }
    }

}