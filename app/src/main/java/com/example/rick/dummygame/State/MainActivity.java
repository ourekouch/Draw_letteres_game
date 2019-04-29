package com.example.rick.dummygame.State;

import android.provider.MediaStore;

import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Screen;
import com.example.emobadaragaminglib.Implementation.AndroidGame;
import com.example.emobadaragaminglib.Implementation.AndroidSound;
import com.example.rick.dummygame.GameViews.MainScreen;
import com.example.rick.dummygame.R;
import com.example.rick.dummygame.assets.MyLettre;
import com.example.rick.dummygame.assets.Obstacles;
import com.example.rick.dummygame.assets.drawing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AndroidGame {
    @Override
    public Screen getInitScreen() {
        //Initialize the assets you will be working with on your screens here.
        //It is better to have all of them loaded, until you have performance problems then, umm,yeah
        //We will have to figure that out :')
        List lettres = new ArrayList();
        lettres.add(getGraphics().newImage(R.drawable.p_0,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_1,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_2,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_3,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_4,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_5,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_6,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_7,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_8,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_9,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_10,Graphics.ImageFormat.ARGB8888,getResources()));
        lettres.add(getGraphics().newImage(R.drawable.p_11,Graphics.ImageFormat.ARGB8888,getResources()));
        MyLettre.setLettres(lettres);
        MyLettre.voice = (AndroidSound) getAudio().createSound(R.raw.tinyrick);
        Obstacles.avatar = getGraphics().newImage(R.drawable.white,Graphics.ImageFormat.ARGB8888,getResources());
        Obstacles.voice = (AndroidSound) getAudio().createSound(R.raw.morty);
        drawing.reset=getGraphics().newImage(R.drawable.repeter,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.validate=getGraphics().newImage(R.drawable.valider,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.background=getGraphics().newImage(R.drawable.bckg,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.bravo=getGraphics().newImage(R.drawable.bravo,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.again=getGraphics().newImage(R.drawable.again,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.check=getGraphics().newImage(R.drawable.black,Graphics.ImageFormat.ARGB8888,getResources());
        drawing.no_color=getGraphics().newImage(R.drawable.no_color,Graphics.ImageFormat.ARGB8888,getResources());
        //The method is going to
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

public class MainActivity extends AppCompatActivity {
    private PaintView paintView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
