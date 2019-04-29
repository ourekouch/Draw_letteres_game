package com.example.rick.dummygame.State;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.rick.dummygame.R;

public class Loading_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
    }

    public void start(View view) {
        Intent i = new Intent(this,choisir_lettre.class);
        startActivity(i);
    }
}
