package com.example.drawing_apps.letters.GameViews;

import android.content.Context;
import android.util.Log;

import com.example.emobadaragaminglib.Base.Game;
import com.example.emobadaragaminglib.Base.Graphics;
import com.example.emobadaragaminglib.Base.Image;
import com.example.emobadaragaminglib.Base.Screen;

import com.example.drawing_apps.letters.Sprites.chemin;
import com.example.drawing_apps.letters.Sprites.pen;
import com.example.drawing_apps.letters.Sprites.valider;
import com.example.drawing_apps.letters.assets.MyLettre;
import com.example.drawing_apps.letters.assets.drawing;

import static com.example.drawing_apps.letters.State.MainAppActivity.Losingsound;
import static com.example.drawing_apps.letters.State.MainAppActivity.Winningsound;

public class MainScreen extends Screen {
    private final String TAG = "MainScreen: ";
    public static int help = 0;
    //private Rick rick;
    //private Morty morty;
    private pen lettre;
    private chemin[] ch;
    private chemin testc;
    private valider Validate;
    private valider Help;
    private valider Reset;
    private valider back;
    private valider Bckg;
    private int cp = 0;
    private int[][] places;
    private int myHack = 0; //used to get some time before re-rendering
    int errors = 0;
    int offset = (int) game.getScreenHeight() / 10;

    public MainScreen(Game game) {
        //This is gonna handle other stuff for you under the hood.We will see more of that next time.
        super(game);
        //Now that your Sprite is Ready, let's initialize it and control where we are going to put it
        Bckg = new valider(game, drawing.background, 0, 0, game.getScreenHeight(), game.getScreenWidth());
        lettre = new pen(game, (Image) MyLettre.lettres.get(0), game.getScreenWidth() / 6 - offset / 2, game.getScreenHeight() / 8 + offset, game.getScreenHeight() / 2, game.getScreenHeight() / 2);
        Validate = new valider(game, drawing.validate, (game.getScreenWidth() / 9) * 1, (game.getScreenHeight() / 6) * 5, 250, 250);
        Help = new valider(game, drawing.help, (game.getScreenWidth() / 9) * 1, (game.getScreenHeight() / 13), 250, 250);
        back = new valider(game, drawing.back, (game.getScreenWidth() / 9) * 6, (game.getScreenHeight() / 13), 250, 250);
        Reset = new valider(game, drawing.reset, (game.getScreenWidth() / 9) * 6, (game.getScreenHeight() / 6) * 5, 250, 250);
        //Now that everything is good let's add the Sprite to the list that we have.
        addSprite(Bckg);
        addSprite(lettre);
        addSprite(Validate);
        addSprite(Help);
        addSprite(back);
        addSprite(Reset);
        putcheckpoints(MyLettre.choix);
        Log.d(TAG, "Constructor Called");
    }

    private void putcheckpoints(String lettre) {
        ch = new chemin[10];
        places = getparcour(lettre);
        for (int i = 0; i < 10; i++) {
            ch[i] = new chemin(game, drawing.no_color, places[i][0], places[i][1], 150, 150);
            addSprite(ch[i]);
        }
    }

    public int[] move(int x, int y, int mouvement, int pas) {
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
        int[] arrive = new int[2];
        int dx = (int) (game.getScreenHeight() / 170); /* 100 pour 1776 x 1080 */
        int dy = (int) (game.getScreenWidth() / 100);  /*100  pour 1776 x 1080 */
        switch (mouvement) {
            case 1:
                arrive[0] = (int) x;
                arrive[1] = (int) y - pas * dy;
                break;
            case 2:
                arrive[0] = (int) x + pas * (dx / 2);
                arrive[1] = (int) y - pas * (dy / 2);
                break;
            case 3:
                arrive[0] = (int) x + pas * dx;
                arrive[1] = (int) y;
                break;
            case 4:
                arrive[0] = (int) x + pas * (dx / 2);
                arrive[1] = (int) y + pas * (dy / 2);
                break;
            case 5:
                arrive[0] = (int) x;
                arrive[1] = (int) y + pas * dy;
                break;
            case 6:
                arrive[0] = (int) x - pas * (dx / 2);
                arrive[1] = (int) y + pas * (dy / 2);
                break;
            case 7:
                arrive[0] = (int) x - pas * dx;
                arrive[1] = (int) y;
                break;
            case 8:
                arrive[0] = (int) x - pas * (dx / 2);
                arrive[1] = (int) y - pas * (dy / 2);
                break;
            case 9:
                arrive[0] = (int) x + pas * (dx / 3);
                arrive[1] = (int) y - pas * (2 * dy / 3);
                break;
            case 10:
                arrive[0] = (int) x + pas * (2 * dx / 3);
                arrive[1] = (int) y - pas * (dy / 3);
                break;
            case 11:
                arrive[0] = (int) x + pas * (2 * dx / 3);
                arrive[1] = (int) y + pas * (dy / 3);
                break;
            case 12:
                arrive[0] = (int) x + pas * (dx / 3);
                arrive[1] = (int) y + pas * (2 * dy / 3);
                break;
            case 13:
                arrive[0] = (int) x - pas * (dx / 3);
                arrive[1] = (int) y + pas * (2 * dy / 3);
                break;
            case 14:
                arrive[0] = (int) x - pas * (2 * dx / 3);
                arrive[1] = (int) y + pas * (dy / 3);
                break;
            case 15:
                arrive[0] = (int) x - pas * (2 * dx / 3);
                arrive[1] = (int) y - pas * (dy / 3);
                break;
            case 16:
                arrive[0] = (int) x - pas * (dx / 3);
                arrive[1] = (int) y - pas * (2 * dy / 3);
                break;
        }
        System.out.println(dx);
        System.out.println(dy);
        return arrive;

    }

    public int[][] getparcour(String lettre) {
        int[][] places = new int[10][2];
        //int offset = (int)game.getScreenHeight()/10;
        switch (lettre) {
            case "p": // "p"
                places[0][0] = game.getScreenWidth() / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + (game.getScreenHeight() / 2) - offset / 4;
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
                places[0][0] = game.getScreenWidth() / 3 - offset / 2 - offset / 4 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + (game.getScreenHeight() / 2);
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
                places[2][0] = game.getScreenWidth() / 3 - offset / 4 - offset / 2;
                places[2][1] = game.getScreenHeight() / 8 + (game.getScreenHeight() / 2) - offset / 6;
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
                places[0][0] = 2 * game.getScreenWidth() / 3 + 3 * offset / 4 - offset / 2;
                places[0][1] = 2 * game.getScreenHeight() / 8 - offset / 8 + offset;
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
                places[0][0] = game.getScreenWidth() / 3 - offset / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
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
                places[0][0] = game.getScreenWidth() / 3 - offset / 4 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
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
                places[0][0] = game.getScreenWidth() / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
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
                places[0][0] = 2 * game.getScreenWidth() / 3 + 3 * offset / 4 - offset / 2;
                places[0][1] = 2 * game.getScreenHeight() / 8 - offset / 8 + offset;
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
                places[0][0] = game.getScreenWidth() / 3 - offset / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
                places[1] = move(places[0][0], places[0][1], 5, 30);
                places[2] = move(places[1][0], places[1][1], 5, 30);
                places[3][0] = 2 * places[0][0] + offset + offset/2;
                places[3][1] = places[0][1];
                places[4] = move(places[3][0], places[3][1], 5, 30);
                places[5] = move(places[4][0], places[4][1], 5, 30);
                places[6] = move(places[1][0], places[1][1], 3, 10);
                places[7] = move(places[6][0], places[6][1], 3, 10);
                places[8] = move(places[7][0], places[7][1], 3, 10);
                places[9] = move(places[8][0], places[8][1], 3, 10);
                break;
            case "i":
                places[0][0] = 2 * game.getScreenWidth() / 4 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
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
                places[0][0] = 2 * game.getScreenWidth() / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
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
                places[0][0] = game.getScreenWidth() / 3 - offset / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
                places[1] = move(places[0][0], places[0][1], 5, 20);
                places[2] = move(places[1][0], places[1][1], 5, 20);
                places[3] = move(places[2][0], places[2][1], 5, 20);
                places[4][0] = 2 * places[0][0] + 2 * offset / 3 + offset/2;
                places[4][1] = places[0][1];
                places[5] = move(places[4][0], places[4][1], 6, 30);
                places[6] = move(places[5][0], places[5][1], 6, 30);
                places[7] = move(places[5][0], places[5][1], 5, 20);
                places[8] = move(places[7][0], places[7][1], 4, 20);
                places[9] = move(places[8][0], places[8][1], 12, 25);
                break;
            case "l":
                places[0][0] = game.getScreenWidth() / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
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
                places[0][0] = game.getScreenWidth() / 3 - 2 * offset / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + (game.getScreenHeight() / 2) - offset / 4;
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
                places[0][0] = game.getScreenWidth() / 3 - offset / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + (game.getScreenHeight() / 2) - offset / 4;
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
                places[0][0] = game.getScreenWidth() / 2 - offset / 2;
                places[0][1] = 2 * game.getScreenHeight() / 8;
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
                places[0][0] = game.getScreenWidth() / 2 - offset / 2;
                places[0][1] = 2 * game.getScreenHeight() / 8;
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
            case "r": // "r"
                places[0][0] = game.getScreenWidth() / 3 - offset / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + (game.getScreenHeight() / 2) - offset - offset / 4 + offset;
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
                places[0][0] = 2 * game.getScreenWidth() / 3 + offset / 4 - offset / 2;
                places[0][1] = 2 * game.getScreenHeight() / 8 - 2 * offset / 8 + offset;
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
                places[4][0] = 2 * game.getScreenWidth() / 4 - offset / 2;
                places[4][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
                places[5] = move(places[4][0], places[4][1], 5, 13);
                places[6] = move(places[5][0], places[5][1], 5, 13);
                places[7] = move(places[6][0], places[6][1], 5, 13);
                places[8] = move(places[7][0], places[7][1], 5, 13);
                places[9] = move(places[8][0], places[8][1], 5, 13);
                places[0][0] = game.getScreenWidth() / 3 - offset / 4 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
                places[1] = move(places[0][0], places[0][1], 3, 15);
                places[2] = move(places[1][0], places[1][1], 3, 15);
                places[3] = move(places[2][0], places[2][1], 3, 15);
                break;
            case "u":
                places[0][0] = game.getScreenWidth() / 3 - offset / 4 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
                places[1] = move(places[0][0], places[0][1], 5, 17);
                places[2] = move(places[1][0], places[1][1], 5, 17);
                places[3] = move(places[2][0], places[2][1], 5, 17);
                places[4] = move(places[3][0], places[3][1], 4, 28);
                places[5] = move(places[4][0], places[4][1], 3, 20);
                places[6] = move(places[5][0], places[5][1], 2, 28);
                places[7] = move(places[6][0], places[6][1], 1, 17);
                places[8] = move(places[7][0], places[7][1], 1, 17);
                places[9] = move(places[8][0], places[8][1], 1, 17);
                break;
            case "v":
                places[0][0] = game.getScreenWidth() / 3 - offset / 4 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
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
                places[0][0] = game.getScreenWidth() / 3 - offset - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset + offset;
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
                places[0][0] = game.getScreenWidth() / 3 - offset / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
                places[1] = move(places[0][0], places[0][1], 4, 30);
                places[2] = move(places[1][0], places[1][1], 4, 30);
                places[3] = move(places[2][0], places[2][1], 12, 30);
                places[4] = move(places[3][0], places[3][1], 12, 30);
                places[5][0] = 2 * places[0][0] + offset + offset/2;
                places[5][1] = places[0][1];
                places[6] = move(places[5][0], places[5][1], 6, 30);
                places[7] = move(places[6][0], places[6][1], 6, 30);
                places[8] = move(places[7][0], places[7][1], 13, 30);
                places[9] = move(places[8][0], places[8][1], 13, 30);
                break;
            case "y":
                places[0][0] = game.getScreenWidth() / 3 - offset / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
                places[1] = move(places[0][0], places[0][1], 12, 30);
                places[2] = move(places[1][0], places[1][1], 4, 25);
                places[3][0] = 2 * places[0][0] + offset + offset/2;
                places[3][1] = places[0][1];
                places[4] = move(places[3][0], places[3][1], 13, 30);
                places[5] = move(places[4][0], places[4][1], 6, 25);
                places[6] = move(places[5][0], places[5][1], 5, 10);
                places[7] = move(places[6][0], places[6][1], 5, 10);
                places[8] = move(places[7][0], places[7][1], 5, 7);
                places[9] = move(places[8][0], places[8][1], 5, 7);
                break;
            case "z":
                places[0][0] = game.getScreenWidth() / 3 - offset / 3 - offset / 2;
                places[0][1] = game.getScreenHeight() / 8 + offset / 3 + offset;
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


    @Override
    public void render(float deltaTime) {
        //With each time you interact with rick, we want to re Render it. Cz one face is just not enough.
        //We get the graphics which is like a wizard that will do what ever he knows. press ctr+space to see
        //other stuff that it can do.
        Graphics g = game.getGraphics();
        g.drawARGB(0, 0, 255, 0);
        if (myHack == 10 & help < 12 && help > 0) {
            lettre.setImage((Image) MyLettre.lettres.get(help));
            System.out.println(help + "help");
            help++;
            myHack = 0;
        }
        if (help == 12 && myHack == 10) {
            help = 0;
            lettre.setImage((Image) MyLettre.lettres.get(0));
            myHack = 0;
        }
        myHack++;
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
        if ((Help.contain(x, y))) {
            if (help == 0) help = 1;
            cp = 0;
            game.setScreen(new MainScreen(game));
        }
        if ((back.contain(x, y))) {
            game.getInitScreen();
            game.setScreen(new MainScreen(game));
            drawing.goback(this, (Context) game);
        }

        if (!(Validate.contain(x, y)) && !(Reset.contain(x, y))) {
            if (cp <= 9) {
                if (ch[cp].contain(x, y)) {
                    lettre.setImage((Image) MyLettre.lettres.get(cp + 2));
                    System.out.println(cp);
                    cp++;
                }
            }


            System.out.println("touch down");
            //System.out.println(morty2.getX());
            //System.out.println(morty2.getY());
        } else {
            if ((Validate.contain(x, y))) {
                System.out.println("validate");
                if (cp == 10) {
                    valider bravo;
                    try {
                        Winningsound.start();
                    } catch (Exception e) {
                        Log.d("Error", "audio file is missing");
                    }
                    bravo = new valider(game, drawing.bravo, (game.getScreenWidth() / 9) * 3, (game.getScreenHeight() / 6) * 2, 500, 500);
                    addSprite(bravo);
                } else {
                    valider again;
                    try {
                        Losingsound.start();
                    } catch (Exception e) {
                        Log.d("Error", "audio file is missing");
                    }
                    again = new valider(game, drawing.again, (game.getScreenWidth() / 9) * 3, (game.getScreenHeight() / 6) * 2, 500, 500);
                    addSprite(again);
                }
                //game.setScreen(new MainScreen(game));
                //resume();
            } else {
                if ((Reset.contain(x, y))) {
                    System.out.println("Reset");
                    game.setScreen(new MainScreen(game));
                    cp = 0;

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


    @Override
    public void dispose() {
        super.dispose();
        System.gc();
    }

    @Override
    public void backButton() {
        pause();

    }
}


