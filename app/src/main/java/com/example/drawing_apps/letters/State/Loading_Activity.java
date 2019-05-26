package com.example.drawing_apps.letters.State;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.e_mobadara.audiomanaging.AudioSettingsActivity;
import com.example.drawing_apps.letters.R;

public class Loading_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }

    public void start(View view) {
        Intent i = new Intent(this,Choisir_langue.class);
        startActivity(i);
    }

    public void  AudioSettings(View view) {
        Intent i = new Intent(this,AudioSettingsActivity.class);
        startActivity(i);
    }
}