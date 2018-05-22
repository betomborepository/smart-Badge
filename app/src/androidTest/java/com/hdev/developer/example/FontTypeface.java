package com.hdev.developer.example;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Created by hp on 21/05/2018.
 */

public class FontTypeface {

    private static Hashtable<String, Typeface> fontCache = new Hashtable<String, Typeface>();

    public static Typeface get(String name, Context context) {
        Typeface tf = fontCache.get(name);
        if(tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), name);
            }
            catch (Exception e) {
                return null;
            }
            fontCache.put(name, tf);
        }
        return tf;
    }

}