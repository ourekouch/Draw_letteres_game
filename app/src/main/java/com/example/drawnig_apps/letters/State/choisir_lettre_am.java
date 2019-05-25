package com.example.drawnig_apps.letters.State;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.drawnig_apps.letters.R;

public class choisir_lettre_am extends AppCompatActivity {
    Button[] lettres;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choisir_lettre_am);
        getSupportActionBar().setTitle("TAMAZIGHT - choisir une lettre");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lettres = new Button[26];
        String[] alpha =new String[]{"tam_a","tam_b","tam_c","tam_d","tam_e","tam_f","tam_g","tam_h","tam_i","tam_j","tam_k","tam_l","tam_m","tam_n","tam_o","tam_p","tam_q","tam_r","tam_s","tam_t","tam_u","tam_v","tam_w","tam_x","tam_y","tam_z"};
        for(int i=0; i<26;i++){
            int identifier = getResources().getIdentifier(alpha[i], "id", getPackageName());
            lettres[i] = findViewById(identifier);
            final String mylettre = alpha[i];
            lettres[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                Intent intent = new Intent(choisir_lettre_am.this,MainAppActivity.class);
                Bundle b = new Bundle();
                b.putString("lettre", mylettre); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                }
            });}
        }
    public void start(View view) {
        Intent i = new Intent(this,MainAppActivity.class);
        startActivity(i);
    }
}
