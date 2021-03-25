package com.example.inscriptionetudiants;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class informationsActivity extends AppCompatActivity {

    private final String FILE_NAME_PROGRAMME1 = "programmeReseau.txt";
    private final String FILE_NAME_PROGRAMME2 = "programmeConception.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_bar);

        saveToFile(informationsActivity.this, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Etiam tempor orci " +
                "eu lobortis elementum nibh. Orci eu lobortis elementum nibh tellus molestie nunc. At erat pellentesque adipiscing commodo elit " +
                "at imperdiet dui accumsan. Tempor orci dapibus ultrices in iaculis.1", FILE_NAME_PROGRAMME1);

        saveToFile(informationsActivity.this, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor" +
                "incididunt ut labore et dolore magna aliqua. Etiam tempor orci eu lobortis elementum nibh. Orci eu lobortis elementum nibh tellus " +
                "molestie nunc. At erat pellentesque adipiscing commodo elit at imperdiet dui accumsan. Tempor orci dapibus ultrices in iaculis.2", FILE_NAME_PROGRAMME2);

        TextView textActionBar = (TextView) findViewById(R.id.actionBar_text);
        textActionBar.setText("Informations sur les programmes Techniques de l'informatique");



        TextView titleProgramme1 = (TextView) findViewById(R.id.titleReseau);
        TextView titleProgramme2 = (TextView) findViewById(R.id.titleProg);
        TextView descProgramme1 = (TextView) findViewById(R.id.reseauDesc);
        TextView descProgramme2 = (TextView) findViewById(R.id.progDesc);


        titleProgramme1.setText("Gestion de réseau");
        descProgramme1.setText(loadFromFile(informationsActivity.this, FILE_NAME_PROGRAMME1));

        titleProgramme2.setText("Conception et programmation");
        descProgramme2.setText(loadFromFile(informationsActivity.this, FILE_NAME_PROGRAMME2));



        Button retourner = (Button) findViewById(R.id.buttonRetour);

        retourner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intentMain = new Intent(informationsActivity.this, MainActivity.class);
                startActivity(intentMain);
            }
        });
    }

    private void saveToFile(Context context, String textToWrite, String file_name) {
        OutputStreamWriter outputStreamWriter = null;

        try {
            outputStreamWriter = new OutputStreamWriter(context.openFileOutput(file_name, context.MODE_PRIVATE));
            outputStreamWriter.write(textToWrite);
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed:" + e.toString());
        } finally {
            if (outputStreamWriter != null) {
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String loadFromFile(Context context, String file_name) {
        String ret = "";
        InputStream inputStream = null;
        try {
            inputStream = context.openFileInput(file_name);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                Scanner fileReader = new Scanner(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while(fileReader.hasNextLine()) {
                    receiveString = fileReader.nextLine();
                    stringBuilder.append(receiveString).append("\n");
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }

        } catch (FileNotFoundException e) {
            Log.e("File activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("File activity", "Can not read file: " + e.toString());
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}