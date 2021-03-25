package com.example.inscriptionetudiants;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class progressBarActivity extends AppCompatActivity  {

    ProgressBar progressBar;
    ProgressBar horizontalProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setCustomView(R.layout.custom_bar);

        TextView textActionBar = (TextView) findViewById(R.id.actionBar_text);
        textActionBar.setText("Inscription en cours...");

        progressBar = (ProgressBar) findViewById(R.id.progressBarH);
        progressBar.setVisibility(View.INVISIBLE);

        horizontalProgressBar = (ProgressBar) findViewById(R.id.progressBarH);

        ShowProgressTask showTask = new ShowProgressTask();
        showTask.execute();
    }

    private class ShowProgressTask extends AsyncTask<Void, Integer, Integer> {
        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(100);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    return -1;
                }
            }
            return 100;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0];
            horizontalProgressBar.setProgress(progress);
        }

        @Override
        protected void onPostExecute(Integer result) {
            Intent intentDone = new Intent(progressBarActivity.this, showEtudiantActivity.class);
            Bundle bundle = getIntent().getBundleExtra("infos");
            intentDone.putExtra("infos", bundle);
            startActivity(intentDone);
        }
    }

}