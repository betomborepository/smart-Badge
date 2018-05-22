package preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * File used to handle the sharedpreferences
 * */

public class Spreference {
    Context context;

    public Spreference(Context context){
        this.context = context;
    }

    public void sPsetter(String name, int value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt(name, value);
        ed.apply();
    }
    public void sPsetter(String name, String value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(name, value);
        ed.apply();
    }
    public void sPsetter(String name, boolean value){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor ed = sp.edit();
        ed.putBoolean(name, value);
        ed.apply();
    }

    public String sPStringGetter(String name){
        SharedPreferences sp  = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(name, null);
    }
    public int sPIntGetter(String name){
        SharedPreferences sp  = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(name, 0);
    }
    public boolean sPBooleanGetter(String name){
        SharedPreferences sp  = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(name, false);
    }

    public void resetPref() {
        SharedPreferences sp  = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().clear().apply();

    }
}
