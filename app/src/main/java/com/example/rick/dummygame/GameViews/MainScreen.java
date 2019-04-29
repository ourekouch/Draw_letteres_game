package com.example.rick.dummygame.GameViews;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Screen;

import com.example.emobadaragaminglib.Components.Sprite;
import com.example.rick.dummygame.Sprites.Morty;
import com.example.rick.dummygame.Sprites.Rick;
import com.example.rick.dummygame.Sprites.chemin;
import com.example.rick.dummygame.Sprites.pen;
import com.example.rick.dummygame.Sprites.valider;
import com.example.rick.dummygame.assets.MyLettre;
import com.example.rick.dummygame.assets.MyLettre;
import com.example.rick.dummygame.assets.Obstacles;
import com.example.rick.dummygame.assets.drawing;
import com.example.rick.dummygame.assets.lettre;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class MainScreen extends Screen {
    private final String TAG = "MainScreen: ";
    //private Rick rick;
    //private Morty morty;
    private pen lettre;
    private chemin[] ch;
    private chemin testc;
    private valider Validate;
    private valider Reset;
    private valider Bckg;
    private int mX;
    private int mY;
    private int cp=0;
    private int[][] places;
    private int myHack = 0; //used to get some time before re-rendering
    int errors = 0;
    public int[][] getparcour( String lettre) {
        int [][] places=new int[10][2];
        places[0][0]=402; places[0][1]=1225;
        places[1][0]=402; places[1][1]=1080;
        places[2][0]=402; places[2][1]=940;
        places[3][0]=402; places[3][1]=780;
        places[4][0]=402; places[4][1]=638;
        places[5][0]=670; places[5][1]=638;
        places[6][0]=780; places[6][1]=722;
        places[7][0]=780; places[7][1]=870;
        places[8][0]=640; places[8][1]=940;
        places[9][0]=450;places[9][1]=940;
        return places;
    }

    public MainScreen(Game game) {
        //This is gonna handle other stuff for you under the hood.We will see more of that next time.
        super(game);
        //ch=new chemin[10];
        //Now that your Sprite is Ready, let's initialize it and control where we are going to put it
        //rick = new Rick(game,Hero.avatar,game.getScreenHeight()/2,game.getScreenWidth()/2,100,100);
        //morty = new Morty(game,Obstacles.avatar,mX,mY,100,100);
        Bckg = new valider(game, drawing.background, 0, 0, game.getScreenHeight(), game.getScreenWidth());
        lettre = new pen(game, (Image)MyLettre.lettres.get(0), game.getScreenHeight() / 8, game.getScreenWidth() / 2, 800, 800);
        //Now that everything is good let's add the Sprite to the list that we have.
        //addSprite(rick);
        //addSprite(morty);
        addSprite(Bckg);
        addSprite(lettre);
        Validate = new valider(game, drawing.validate, (game.getScreenWidth() / 9) * 1, (game.getScreenHeight() / 6) * 5, 250, 250);
        addSprite(Validate);
        Reset = new valider(game, drawing.reset, (game.getScreenWidth() / 9) * 6, (game.getScreenHeight() / 6) * 5, 250, 250);
        addSprite(Reset);
        putcheckpoints();
        Log.d(TAG, "Constructor Called");

    }
    private void putcheckpoints(){
        ch = new chemin[10];
        places=getparcour("p");
        for(int i =0;i<10;i++){
                ch[i] = new chemin(game,drawing.no_color, places[i][0],places[i][1], 120, 120);
                addSprite(ch[i]);
        }
    }
    @Override
    public void render(float deltaTime) {
        //With each time you interact with rick, we want to re Render it. Cz one face is just not enough.
        //We get the graphics which is like a wizard that will do what ever he knows. press ctr+space to see
        //other stuff that it can do.
        Graphics g = game.getGraphics();
        //Redrawing Rick multiple times
        /*Uncomment this line and see what happens */
        g.drawARGB(0, 0, 255, 0);
        /*if(myHack==10) {
            mX = (int) Math.floor(Math.random() * g.getWidth());
            mY = (int) Math.floor(Math.random() * g.getHeight());
            morty.setX(mX);
            morty.setY(mY);
            myHack=0;
        }*/
        myHack++;
        //if(rickGotHit()){
        //    Obstacles.voice.play(1);
        // }
    }

    @Override
    public void handleDragging(int x, int y, int pointer) {
        if (cp <= 9) {
            if (ch[cp].contain(x, y)) {
                lettre.setImage((Image) MyLettre.lettres.get(cp + 2));
                cp++;
            }
        }
    }

    @Override
    public void handleTouchDown(int x, int y, int pointer) {
        /*
         * In this method I check which Sprite I did touch and act accordingly
         * */
        //super.handleTouchDown(x, y, pointer);
        //super.handleDragging(x, y, pointer);
        if (!(Validate.contain(x, y)) && !(Reset.contain(x, y))) {
            if (cp <= 9) {
                if (ch[cp].contain(x, y)) {
                    lettre.setImage((Image) MyLettre.lettres.get(cp + 2));
                    System.out.println(cp);
                    cp++;
                }
            }

            //System.out.println(morty2.getX());
            //System.out.println(morty2.getY());
        } else {
            if ((Validate.contain(x, y))) {
                if (cp == 10) {
                    valider bravo;
                    bravo = new valider(game, drawing.bravo, (game.getScreenWidth() / 9) * 3, (game.getScreenHeight() / 6) * 2, 500, 500);
                    addSprite(bravo);
                } else {
                    valider again;
                    again = new valider(game, drawing.again, (game.getScreenWidth() / 9) * 3, (game.getScreenHeight() / 6) * 2, 500, 500);
                    addSprite(again);
                }
                //game.setScreen(new MainScreen(game));
                //resume();
            } else {
                if ((Reset.contain(x, y))) {
                    game.setScreen(new MainScreen(game));
                    cp=0;
                }else{
                    for(int i =0;i<10;i++){
                        ch[i].setImage(drawing.validate);
                    }
                    for(int i =0;i<10;i++){
                        ch[i].setImage(drawing.check);
                    }
                }
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    //The handle dragging is activated anytime you drag on your screen.
    /*@Override
    public void handleDragging(int x, int y, int pointer) {
        super.handleDragging(x, y, pointer);
        Hero.voice.play(1);
    }*/

    @Override
    public void dispose() {
        super.dispose();
        System.gc();
    }

    @Override
    public void backButton() {
        pause();
    }

    /*boolean rickGotHit(){
        if(rick.contain(morty.getX(),morty.getY())) return true;
        return false;
    }*/
    /*public double distance_2(int x1, int y1, int x2, int y2) {
        double d = sqrt(pow((x1 + y1), 2) + pow((x2 + y2), 2));
        return d;
    }

    public int distance(Sprite lettre, Sprite[] drawing, int distance, int nombre) {
        Sprite s = drawing[0];
        int x1 = lettre.getX();
        int y1 = lettre.getY();
        while (s != null) {
            int x2 = s.getX();
            int y2 = s.getY();
            double d = distance2(x1, y1, x2, y2);
        }
    }*/
}

