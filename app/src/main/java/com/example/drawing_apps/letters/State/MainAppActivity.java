package com.example.drawing_apps.letters.State;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.e_mobadara.audiomanaging.moblibAudioFileManager;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.example.emobadaragaminglib.Implementation.AndroidSound;
import com.example.drawing_apps.letters.GameViews.MainScreen;
import com.example.drawing_apps.letters.R;
import com.example.drawing_apps.letters.assets.MyLettre;
import com.example.drawing_apps.letters.assets.Obstacles;
import com.example.drawing_apps.letters.assets.drawing;

import java.util.ArrayList;
import java.util.List;

public class MainAppActivity extends AndroidGame {

    public static MediaPlayer Losingsound;
    public static MediaPlayer Winningsound;

    @Override
    public Screen getInitScreen() {
        //Initialize the assets you will be working with on your screens here.
        //It is better to have all of them loaded, until you have performance problems then, umm,yeah
        //We will have to figure that out :')
        List lettres = new ArrayList();
        Bundle b = getIntent().getExtras();
        String value = new String(); // or other values
        if(b != null)
            value = b.getString("lettre");
        int identifier = getResources().getIdentifier(value, "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_1", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_2", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_3", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_4", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_5", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_6", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_7", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_8", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_9", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_10", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        identifier = getResources().getIdentifier(value+"_11", "drawable", getPackageName());
        lettres.add(getGraphics().newImage(identifier,Graphics.ImageFormat.ARGB8888,getResources()));
        MyLettre.setLettres(lettres);
        MyLettre.choix=value;
        MyLettre.voice = (AndroidSound) getAudio().createSound(R.raw.tinyrick);
        Obstacles.avatar = getGraphics().newImage(R.drawable.white,Graphics.ImageFormat.ARGB8888,getResources());
        Obstacles.voice = (AndroidSound) getAudio().createSound(R.raw.morty);
        drawing.reset=getGraphics().newImage(R.drawable.repeter,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.validate=getGraphics().newImage(R.drawable.valider,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.background=getGraphics().newImage(R.drawable.bckg3,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.bravo=getGraphics().newImage(R.drawable.bravo,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.help=getGraphics().newImage(R.drawable.help,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.again=getGraphics().newImage(R.drawable.again,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.check=getGraphics().newImage(R.drawable.black,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.no_color=getGraphics().newImage(R.drawable.no_color,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.back=getGraphics().newImage(R.drawable.bck_btn,Graphics.ImageFormat.ARGB8888,getResources());
        // Audio modules imported :
        Losingsound = moblibAudioFileManager.getRandomAudioFile(this,"encouragement","AR");
        Winningsound = moblibAudioFileManager.getRandomAudioFile(this,"good","AR");
        // The method is going to
        return new MainScreen(this);

    }

    @Override
    protected void onDestroy() {
        //Let's make life easy on your device and get rid of the memo we dont use
        //because Android system does not do that always.
        super.onDestroy();
        /*for(int j=0;j<12;j++) {
            MyLettre.lettres.get(j).dispose();
        }*/
        MyLettre.voice.dispose();
    }
}/*

public class MainAppActivity extends AppCompatActivity {
    private PaintView paintView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);
        paintView=(PaintView) findViewById(R.id.paintView);
        DisplayMetrics metrics =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        paintView.init(metrics);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.normal :
                paintView.normal();
                return true;
            case R.id.emboss :
                paintView.emboss();
                return true;
            case R.id.blur :
                paintView.blur();
                return true;
            case R.id.clear :
                paintView.clear();
                return true;

        }



        return super.onOptionsItemSelected(item);
    }
}*/
