package com.kronistas.marvelcharacters.utils;


import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.InputStream;
import java.net.URL;

public class Utils {

    public static void log (String TAG, String msj){
        Log.e("TULOG" + TAG, msj);
    }

    public static Drawable loadImageFromWebOperations(String url, String srcName) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, srcName);
            return d;
        } catch (Exception e) {
            return null;
        }
    }


}
