package com.arbutus.exerboost.repository.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.arbutus.exerboost.utilities.AppConstants;

public class LocalController {

    private SharedPreferences preferences;
    public static LocalController controller;

    private LocalController(Context context){
        preferences = context.getSharedPreferences(AppConstants.SHARED_PREFERENCES_FILE_NAME,context.MODE_PRIVATE);
    }

    public static LocalController getInstance(Context context){
        if(controller == null){
            controller = new LocalController(context);
        }
        return controller;
    }
    public SharedPreferences getPreferences(){
        return preferences;
    }
}
