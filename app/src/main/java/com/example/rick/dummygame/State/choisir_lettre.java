package com.example.rick.dummygame.State;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rick.dummygame.R;
import com.example.rick.dummygame.assets.lettre;

public class choisir_lettre extends AppCompatActivity {
    Button[] lettres;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_lettre);
        lettres = new Button[26];
        String[] alpha =new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for(int i=0; i<26;i++){
            int identifier = getResources().getIdentifier(alpha[i], "id", getPackageName());
            lettres[i] = findViewById(identifier);
            lettres[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                Intent intent = new Intent(choisir_lettre.this,MainActivity.class);
                startActivity(intent);
                }
            });}
        }
    public void start(View view) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
