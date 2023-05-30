package com.example.tpo1.request;

import android.content.Context;
import android.content.SharedPreferences;

public class SpToken {
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences("token", 0);
        }

        return sp;
    }

    public static void guardarToken(Context context, String token){
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", "Bearer " + token);

        editor.commit();
    }

    public static String leerToken(Context context){
        SharedPreferences sp = conectar(context);
        String token = sp.getString("token", "");

        return token;
    }

    public static void eliminarToken(Context context) {
        SharedPreferences sp = conectar(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("token");
        editor.apply();
    }
}
