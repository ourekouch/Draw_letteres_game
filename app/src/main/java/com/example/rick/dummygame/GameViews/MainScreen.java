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

import static com.example.rick.dummygame.State.MainAppActivity.Losingsound;
import static com.example.rick.dummygame.State.MainAppActivity.Winningsound;
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
    public int [] move (int x , int y,int mouvement ,int pas ){
        /*       1
              16    9
             8        2
           15           10
         7       .         3
           14           11
             6        4
               13   12
                  5
        */
        int[] arrive  = new int [2];
        int dx = (int)(game.getScreenHeight()/170); /* 100 pour 1776 x 1080 */
        int dy =(int)(game.getScreenWidth()/100);  /*100  pour 1776 x 1080 */
        switch (mouvement){
            case 1 :
                arrive[0]=(int)x;
                arrive[1]=(int)y-pas*dy;
                break;
            case 2 :
                arrive[0]=(int)x+pas*(dx/2);
                arrive[1]=(int)y-pas*(dy/2);
                break;
            case 3 :
                arrive[0]=(int)x+pas*dx;
                arrive[1]=(int)y;
                break;
            case 4 :
                arrive[0]=(int)x+pas*(dx/2);
                arrive[1]=(int)y+pas*(dy/2);
                break;
            case 5 :
                arrive[0]=(int)x;
                arrive[1]=(int)y+pas*dy;
                break;
            case 6 :
                arrive[0]=(int)x-pas*(dx/2);
                arrive[1]=(int)y+pas*(dy/2);
                break;
            case 7 :
                arrive[0]=(int)x-pas*dx;
                arrive[1]=(int)y;
                break;
            case 8 :
                arrive[0]=(int)x-pas*(dx/2);
                arrive[1]=(int)y-pas*(dy/2);
                break;
            case 9 :
                arrive[0]=(int)x+pas*(dx/3);
                arrive[1]=(int)y-pas*(2*dy/3);
                break;
            case 10 :
                arrive[0]=(int)x+pas*(2*dx/3);
                arrive[1]=(int)y-pas*(dy/3);
                break;
            case 11 :
                arrive[0]=(int)x+pas*(2*dx/3);
                arrive[1]=(int)y+pas*(dy/3);
                break;
            case 12 :
                arrive[0]=(int)x+pas*(dx/3);
                arrive[1]=(int)y+pas*(2*dy/3);
                break;
            case 13 :
                arrive[0]=(int)x-pas*(dx/3);
                arrive[1]=(int)y+pas*(2*dy/3);
                break;
            case 14 :
                arrive[0]=(int)x-pas*(2*dx/3);
                arrive[1]=(int)y+pas*(dy/3);
                break;
            case 15 :
                arrive[0]=(int)x-pas*(2*dx/3);
                arrive[1]=(int)y-pas*(dy/3);
                break;
            case 16 :
                arrive[0]=(int)x-pas*(dx/3);
                arrive[1]=(int)y-pas*(2*dy/3);
                break;
        }
        System.out.println(dx);
        System.out.println(dy);
        return arrive;

    }
    public int[][] getparcour( String lettre) {
        int[][] places = new int[10][2];
        int offset = (int)game.getScreenHeight()/10;
        switch (lettre) {
            case "p" : // "p"
                places[0][0] = game.getScreenWidth() / 3;
                places[0][1] = game.getScreenHeight() / 8 +( game.getScreenHeight()/2) - offset - offset/4;
                places[1] = move(places[0][0], places[0][1], 1, 15);
                places[2] = move(places[1][0], places[1][1], 1, 15);
                places[3] = move(places[2][0], places[2][1], 1, 15);
                places[4] = move(places[3][0], places[3][1], 1, 15);
                places[5] = move(places[4][0], places[4][1], 3, 30);
                places[6] = move(places[5][0], places[5][1], 4, 25);
                places[7] = move(places[6][0], places[6][1], 5, 10);
                places[8] = move(places[7][0], places[7][1], 6, 20);
                places[9] = move(places[8][0], places[8][1], 7, 15);
                break;
            case "a":
                places[0][0] = game.getScreenWidth() / 3 - offset/2 - offset/4;
                places[0][1] = game.getScreenHeight() / 8 +( game.getScreenHeight()/2) - offset;
                places[1] = move(places[0][0], places[0][1], 9, 35);
                places[2] = move(places[1][0], places[1][1], 9, 35);
                places[3] = move(places[2][0], places[2][1], 9, 35);
                places[4] = move(places[3][0], places[3][1], 12, 35);
                places[5] = move(places[4][0], places[4][1], 12, 35);
                places[6] = move(places[5][0], places[5][1], 12, 35);
                places[7] = move(places[1][0], places[1][1], 3, 5);
                places[8] = move(places[7][0], places[7][1], 3, 15);
                places[9] = move(places[8][0], places[8][1], 3, 15);
                break;
            case "b":
                places[2][0] = game.getScreenWidth() / 3 -offset/4;
                places[2][1] = game.getScreenHeight() / 8 +( game.getScreenHeight()/2) - offset -offset/6;
                places[1] = move(places[2][0], places[2][1], 1, 30);
                places[0] = move(places[1][0], places[1][1], 1, 30);
                places[3] = move(places[0][0], places[0][1], 3, 25);
                places[4] = move(places[3][0], places[3][1], 11, 30);
                places[5] = move(places[4][0], places[4][1], 13, 35);
                places[6] = move(places[5][0], places[5][1], 7, 20);
                places[7] = move(places[5][0], places[5][1], 4, 30);
                places[8] = move(places[7][0], places[7][1], 13, 30);
                places[9] = move(places[8][0], places[8][1], 7, 20);
                break;
            case "c":
                places[0][0] = 2*game.getScreenWidth() / 3 + 3*offset/4;
                places[0][1] = 2*game.getScreenHeight() / 8 -offset/8;
                places[1] = move(places[0][0], places[0][1], 8, 25);
                places[2] = move(places[1][0], places[1][1], 7, 25);
                places[3] = move(places[2][0], places[2][1], 6, 30);
                places[4] = move(places[3][0], places[3][1], 5, 15);
                places[5] = move(places[4][0], places[4][1], 5, 15);
                places[6] = move(places[5][0], places[5][1], 4, 20);
                places[7] = move(places[6][0], places[6][1], 11, 30);
                places[8] = move(places[7][0], places[7][1], 10, 25);
                places[9] = move(places[8][0], places[8][1], 2, 20);
                break;
            case "d":
                places[0][0] = game.getScreenWidth() / 3  - offset/3;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 20);
                places[2] = move(places[1][0], places[1][1], 5, 20);
                places[3] = move(places[2][0], places[2][1], 5, 20);
                places[4] = move(places[0][0], places[0][1], 3, 25);
                places[5] = move(places[4][0], places[4][1], 11, 35);
                places[6] = move(places[5][0], places[5][1], 12, 35);
                places[7] = move(places[6][0], places[6][1], 13, 37);
                places[8] = move(places[7][0], places[7][1], 14, 25);
                places[9] = move(places[8][0], places[8][1], 7, 15);
                break;
            case "e":
                places[0][0] = game.getScreenWidth() / 3  - offset/4;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 15);
                places[2] = move(places[1][0], places[1][1], 5, 15);
                places[3] = move(places[2][0], places[2][1], 5, 34);
                places[4] = move(places[0][0], places[0][1], 3, 25);
                places[5] = move(places[4][0], places[4][1], 3, 22);
                places[6] = move(places[2][0], places[2][1], 3, 25);
                places[7] = move(places[6][0], places[6][1], 3, 22);
                places[8] = move(places[3][0], places[3][1], 3, 25);
                places[9] = move(places[8][0], places[8][1], 3, 22);
                break;
            case "f":
                places[0][0] = game.getScreenWidth() / 3  ;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 15);
                places[2] = move(places[1][0], places[1][1], 5, 15);
                places[3] = move(places[2][0], places[2][1], 5, 10);
                places[4] = move(places[3][0], places[3][1], 5, 10);
                places[5] = move(places[4][0], places[4][1], 5, 10);
                places[6] = move(places[0][0], places[0][1], 3, 15);
                places[7] = move(places[6][0], places[6][1], 3, 20);
                places[8] = move(places[2][0], places[2][1], 3, 15);
                places[9] = move(places[8][0], places[8][1], 3, 15);
                break;
            case "g":
                places[0][0] = 2*game.getScreenWidth() / 3 + 3*offset/4;
                places[0][1] = 2*game.getScreenHeight() / 8 -offset/8;
                places[1] = move(places[0][0], places[0][1], 8, 25);
                places[2] = move(places[1][0], places[1][1], 7, 25);
                places[3] = move(places[2][0], places[2][1], 6, 30);
                places[4] = move(places[3][0], places[3][1], 5, 30);
                places[5] = move(places[4][0], places[4][1], 4, 35);
                places[6] = move(places[5][0], places[5][1], 3, 18);
                places[7] = move(places[6][0], places[6][1], 10, 27);
                places[8] = move(places[7][0], places[7][1], 1, 20);
                places[9] = move(places[8][0], places[8][1], 7, 18);
                break;
            case "h":
                places[0][0] = game.getScreenWidth() / 3  - offset /3;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 30);
                places[2] = move(places[1][0], places[1][1], 5, 30);
                places[3][0] = 2*places[0][0] + offset;
                places[3][1] =  places[0][1];
                places[4] = move(places[3][0], places[3][1], 5, 30);
                places[5] = move(places[4][0], places[4][1], 5, 30);
                places[6] = move(places[1][0], places[1][1], 3, 10);
                places[7] = move(places[6][0], places[6][1], 3, 10);
                places[8] = move(places[7][0], places[7][1], 3, 10);
                places[9] = move(places[8][0], places[8][1], 3, 10);
                break;
            case "i":
                places[0][0] = 2*game.getScreenWidth() / 4  ;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 7);
                places[2] = move(places[1][0], places[1][1], 5, 7);
                places[3] = move(places[2][0], places[2][1], 5, 7);
                places[4] = move(places[3][0], places[3][1], 5, 7);
                places[5] = move(places[4][0], places[4][1], 5, 7);
                places[6] = move(places[5][0], places[5][1], 5, 7);
                places[7] = move(places[6][0], places[6][1], 5, 7);
                places[8] = move(places[7][0], places[7][1], 5, 7);
                places[9] = move(places[8][0], places[8][1], 5, 7);
                break;
            case "j":
                places[0][0] = 2*game.getScreenWidth() / 3  ;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 10);
                places[2] = move(places[1][0], places[1][1], 5, 10);
                places[3] = move(places[2][0], places[2][1], 5, 10);
                places[4] = move(places[3][0], places[3][1], 5, 10);
                places[5] = move(places[4][0], places[4][1], 5, 10);
                places[6] = move(places[5][0], places[5][1], 5, 8);
                places[7] = move(places[6][0], places[6][1], 14, 15);
                places[8] = move(places[7][0], places[7][1], 7, 18);
                places[9] = move(places[8][0], places[8][1], 16, 28);
                break;
            case "k":
                places[0][0] = game.getScreenWidth() / 3  -offset/3;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 20);
                places[2] = move(places[1][0], places[1][1], 5, 20);
                places[3] = move(places[2][0], places[2][1], 5, 20);
                places[4][0] = 2*places[0][0] +2*offset/3;
                places[4][1] = places[0][1];
                places[5] = move(places[4][0], places[4][1], 6, 30);
                places[6] = move(places[5][0], places[5][1], 6, 30);
                places[7] = move(places[5][0], places[5][1], 5, 20);
                places[8] = move(places[7][0], places[7][1], 4, 20);
                places[9] = move(places[8][0], places[8][1], 12, 25);
                break;
            case "l":
                places[0][0] = game.getScreenWidth() / 3  ;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 10);
                places[2] = move(places[1][0], places[1][1], 5, 10);
                places[3] = move(places[2][0], places[2][1], 5, 10);
                places[4] = move(places[3][0], places[3][1], 5, 10);
                places[5] = move(places[4][0], places[4][1], 5, 10);
                places[6] = move(places[5][0], places[5][1], 5, 15);
                places[7] = move(places[6][0], places[6][1], 3, 13);
                places[8] = move(places[7][0], places[7][1], 3, 13);
                places[9] = move(places[8][0], places[8][1], 3, 13);
                break;
            case "m":
                places[0][0] = game.getScreenWidth() / 3 -2*offset/3 ;
                places[0][1] = game.getScreenHeight() / 8 +( game.getScreenHeight()/2) - offset - offset/4;
                places[1] = move(places[0][0], places[0][1], 1, 30);
                places[2] = move(places[1][0], places[1][1], 1, 30);
                places[3] = move(places[2][0], places[2][1], 4, 20);
                places[4] = move(places[3][0], places[3][1], 12, 30);
                places[5] = move(places[4][0], places[4][1], 12, 40);
                places[6] = move(places[5][0], places[5][1], 9, 43);
                places[7] = move(places[6][0], places[6][1], 9, 45);
                places[8] = move(places[7][0], places[7][1], 5, 30);
                places[9] = move(places[8][0], places[8][1], 5, 30);
                break;
            case "n":
                places[0][0] = game.getScreenWidth() / 3  -offset/3;
                places[0][1] = game.getScreenHeight() / 8 +( game.getScreenHeight()/2) - offset - offset/4;
                places[1] = move(places[0][0], places[0][1], 1, 20);
                places[2] = move(places[1][0], places[1][1], 1, 20);
                places[3] = move(places[2][0], places[2][1], 1, 20);
                places[4] = move(places[3][0], places[3][1], 4, 38);
                places[5] = move(places[4][0], places[4][1], 12, 38);
                places[6] = move(places[5][0], places[5][1], 4, 35);
                places[7] = move(places[6][0], places[6][1], 1, 20);
                places[8] = move(places[7][0], places[7][1], 1, 20);
                places[9] = move(places[8][0], places[8][1], 1, 20);
                break;
            case "o":
                places[0][0] = game.getScreenWidth() / 2 ;
                places[0][1] = 2*game.getScreenHeight() / 8 - offset;
                places[1] = move(places[0][0], places[0][1], 14, 30);
                places[2] = move(places[1][0], places[1][1], 6, 20);
                places[3] = move(places[2][0], places[2][1], 5, 20);
                places[4] = move(places[3][0], places[3][1], 12, 25);
                places[5] = move(places[4][0], places[4][1], 4, 23);
                places[6] = move(places[5][0], places[5][1], 3, 23);
                places[7] = move(places[6][0], places[6][1], 2, 26);
                places[8] = move(places[7][0], places[7][1], 1, 25);
                places[9] = move(places[8][0], places[8][1], 16, 30);
                break;
            case "q":
                places[0][0] = game.getScreenWidth() / 2 ;
                places[0][1] = 2*game.getScreenHeight() / 8 - offset;
                places[1] = move(places[0][0], places[0][1], 14, 45);
                places[2] = move(places[1][0], places[1][1], 5, 20);
                places[3] = move(places[2][0], places[2][1], 12, 40);
                places[4] = move(places[3][0], places[3][1], 3, 30);
                places[5] = move(places[4][0], places[4][1], 2, 30);
                places[6] = move(places[5][0], places[5][1], 1, 25);
                places[7] = move(places[6][0], places[6][1], 8, 20);
                places[8] = move(places[4][0], places[4][1], 15, 20);
                places[9] = move(places[4][0], places[4][1], 11, 20);
                break;
            case "r" : // "r"
                places[0][0] = game.getScreenWidth() / 3 - offset/3;
                places[0][1] = game.getScreenHeight() / 8 +( game.getScreenHeight()/2) - offset - offset/4;
                places[1] = move(places[0][0], places[0][1], 1, 30);
                places[2] = move(places[1][0], places[1][1], 1, 30);
                places[3] = move(places[2][0], places[2][1], 3, 25);
                places[4] = move(places[3][0], places[3][1], 11, 35);
                places[5] = move(places[4][0], places[4][1], 5, 10);
                places[6] = move(places[5][0], places[5][1], 6, 20);
                places[7] = move(places[6][0], places[6][1], 7, 15);
                places[8] = move(places[7][0], places[7][1], 4, 35);
                places[9] = move(places[8][0], places[8][1], 12, 25);
                break;
            case "s":
                places[0][0] = 2*game.getScreenWidth() / 3 + offset/4;
                places[0][1] = 2*game.getScreenHeight() / 8 -2*offset/8;
                places[1] = move(places[0][0], places[0][1], 8, 25);
                places[2] = move(places[1][0], places[1][1], 7, 25);
                places[3] = move(places[2][0], places[2][1], 13, 25);
                places[4] = move(places[3][0], places[3][1], 4, 20);
                places[5] = move(places[4][0], places[4][1], 11, 30);
                places[6] = move(places[5][0], places[5][1], 11, 30);
                places[7] = move(places[6][0], places[6][1], 5, 15);
                places[8] = move(places[7][0], places[7][1], 14, 32);
                places[9] = move(places[8][0], places[8][1], 15, 40);
                break;
            case "t":
                places[0][0] = 2*game.getScreenWidth() / 4  ;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 13);
                places[2] = move(places[1][0], places[1][1], 5, 13);
                places[3] = move(places[2][0], places[2][1], 5, 13);
                places[4] = move(places[3][0], places[3][1], 5, 13);
                places[5] = move(places[4][0], places[4][1], 5, 13);
                places[6][0] = game.getScreenWidth() / 3  - offset/4;
                places[6][1] = game.getScreenHeight() / 8 + offset/3;
                places[7] = move(places[6][0], places[6][1], 3, 15);
                places[8] = move(places[7][0], places[7][1], 3, 15);
                places[9] = move(places[8][0], places[8][1], 3, 15);
                break;
            case "u":
                places[0][0] = game.getScreenWidth() / 3  - offset/4;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 5, 17);
                places[2] = move(places[1][0], places[1][1], 5, 17);
                places[3] = move(places[2][0], places[2][1], 5, 17);
                places[4] = move(places[3][0], places[3][1], 4, 28);
                places[5] = move(places[4][0], places[4][1], 3, 20);
                places[6] = move(places[5][0], places[5][1], 2, 28);
                places[7] = move(places[6][0], places[6][1], 1 ,17);
                places[8] = move(places[7][0], places[7][1], 1, 17);
                places[9] = move(places[8][0], places[8][1], 1, 17);
                break;
            case "v":
                places[0][0] = game.getScreenWidth() / 3  - offset/4;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 12, 25);
                places[2] = move(places[1][0], places[1][1], 5, 14);
                places[3] = move(places[2][0], places[2][1], 12, 25);
                places[4] = move(places[3][0], places[3][1], 12, 30);
                places[5] = move(places[4][0], places[4][1], 9, 15);
                places[6] = move(places[5][0], places[5][1], 9, 20);
                places[7] = move(places[6][0], places[6][1], 9, 20);
                places[8] = move(places[7][0], places[7][1], 9, 20);
                places[9] = move(places[8][0], places[8][1], 9, 20);
                break;
            case "w":
                places[0][0] = game.getScreenWidth() / 3  - offset;
                places[0][1] = game.getScreenHeight() / 8 + offset;
                places[1] = move(places[0][0], places[0][1], 12, 33);
                places[2] = move(places[1][0], places[1][1], 12, 33);
                places[3] = move(places[2][0], places[2][1], 9, 33);
                places[4] = move(places[3][0], places[3][1], 9, 33);
                places[5] = move(places[4][0], places[4][1], 12, 33);
                places[6] = move(places[5][0], places[5][1], 12, 28);
                places[7] = move(places[6][0], places[6][1], 9, 20);
                places[8] = move(places[7][0], places[7][1], 9, 20);
                places[9] = move(places[8][0], places[8][1], 1, 15);
                break;
            case "x":
                places[0][0] = game.getScreenWidth() / 3  - offset /3;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 4, 30);
                places[2] = move(places[1][0], places[1][1], 4, 30);
                places[3] = move(places[2][0], places[2][1], 12, 30);
                places[4] = move(places[3][0], places[3][1], 12, 30);
                places[5][0] = 2*places[0][0] + offset;
                places[5][1] =  places[0][1];
                places[6] = move(places[5][0], places[5][1], 6, 30);
                places[7] = move(places[6][0], places[6][1], 6, 30);
                places[8] = move(places[7][0], places[7][1], 13, 30);
                places[9] = move(places[8][0], places[8][1], 13, 30);
                break;
            case "y":
                places[0][0] = game.getScreenWidth() / 3  - offset /3;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 12, 30);
                places[2] = move(places[1][0], places[1][1], 4, 25);
                places[3][0] = 2*places[0][0] + offset;
                places[3][1] =  places[0][1];
                places[4] = move(places[3][0], places[3][1], 13, 30);
                places[5] = move(places[4][0], places[4][1], 6, 25);
                places[6] = move(places[5][0], places[5][1], 5, 10);
                places[7] = move(places[6][0], places[6][1], 5, 10);
                places[8] = move(places[7][0], places[7][1], 5, 7);
                places[9] = move(places[8][0], places[8][1], 5, 7);
                break;
            case "z":
                places[0][0] = game.getScreenWidth() / 3  - offset /3;
                places[0][1] = game.getScreenHeight() / 8 + offset/3;
                places[1] = move(places[0][0], places[0][1], 3, 20);
                places[2] = move(places[1][0], places[1][1], 3, 15);
                places[3] = move(places[2][0], places[2][1], 3, 15);
                places[4] = move(places[3][0], places[3][1], 6, 40);
                places[5] = move(places[4][0], places[4][1], 6, 40);
                places[6] = move(places[5][0], places[5][1], 13, 40);
                places[7] = move(places[6][0], places[6][1], 3, 20);
                places[8] = move(places[7][0], places[7][1], 3, 15);
                places[9] = move(places[8][0], places[8][1], 3, 15);
                break;

        }
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
        lettre = new pen(game, (Image)MyLettre.lettres.get(0), game.getScreenWidth() / 6 , game.getScreenHeight() / 8, game.getScreenHeight()/2, game.getScreenHeight()/2);
        //Now that everything is good let's add the Sprite to the list that we have.
        //addSprite(rick);
        //addSprite(morty);
        addSprite(Bckg);
        addSprite(lettre);
        Validate = new valider(game, drawing.validate, (game.getScreenWidth() / 9) * 1, (game.getScreenHeight() / 6) * 5, 250, 250);
        addSprite(Validate);
        Reset = new valider(game, drawing.reset, (game.getScreenWidth() / 9) * 6, (game.getScreenHeight() / 6) * 5, 250, 250);
        addSprite(Reset);
        putcheckpoints(MyLettre.choix);
        Log.d(TAG, "Constructor Called");

    }
    private void putcheckpoints(String lettre){
        ch = new chemin[10];
        places=getparcour(lettre);
        for(int i =0;i<10;i++){
                ch[i] = new chemin(game,drawing.check, places[i][0],places[i][1], 150, 150);
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
                    try{Winningsound.start();}catch(Exception e){Log.d("Error", "audio file is missing");}
                    bravo = new valider(game, drawing.bravo, (game.getScreenWidth() / 9) * 3, (game.getScreenHeight() / 6) * 2, 500, 500);
                    addSprite(bravo);
                } else {
                    valider again;
                    try{Losingsound.start();}catch(Exception e){Log.d("Error", "audio file is missing");}
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

