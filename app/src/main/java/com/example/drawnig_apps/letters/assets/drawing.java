package com.example.drawnig_apps.letters.assets;

import android.content.Context;
import android.content.Intent;

import com.example.emobadaragaminglib.Base.Image;
import com.example.drawnig_apps.letters.GameViews.MainScreen;
import com.example.drawnig_apps.letters.State.choisir_lettre_fr;

public class drawing {
        public static Image validate;
        public static Image reset;
        public static Image background;
        public static Image bravo;
        public static Image again;
        public static Image check;
        public static Image help;
        public static Image back;
        public static Image no_color;

        public static void goback(MainScreen mainScreen,Context ctx) {
                Intent i = new Intent(ctx,choisir_lettre_fr.class);
                ctx.startActivity(i);
        }



}
