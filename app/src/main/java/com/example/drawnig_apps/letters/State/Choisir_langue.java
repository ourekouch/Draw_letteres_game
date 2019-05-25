package com.example.drawnig_apps.letters.State;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.drawnig_apps.letters.R;

public class Choisir_langue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_langue);
        getSupportActionBar().setTitle("Choisir une langue");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void Langue_ar(View view) {
        Intent i = new Intent(this,choisir_lettre_fr.class);
        startActivity(i);
    }

    public void  Langue_fr(View view) {
        MediaPlayer bvn_fr=MediaPlayer.create(this, R.raw.bvn_fr);
        Intent i = new Intent(this,choisir_lettre_fr.class);
        bvn_fr.start();
        startActivity(i);
    }

    public void Langue_am(View view) {
        MediaPlayer bvn_tam=MediaPlayer.create(this, R.raw.bvn_am);
        Intent i = new Intent(this,choisir_lettre_am.class);
        bvn_tam.start();
        startActivity(i);
    }

    public void  Langue_en(View view) {
        Intent i = new Intent(this,choisir_lettre_fr.class);
        startActivity(i);
    }
}
