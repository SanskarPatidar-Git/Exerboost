package com.arbutus.exerboost.repository.local;

import android.content.SharedPreferences;

import com.arbutus.exerboost.activity.auth.login.model.response.User;

public class LocalSets {

    public static void setUserData(SharedPreferences preferences , User userData , String authToken){

        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("id",userData.getId());
        editor.putString("role",userData.getRole());
        editor.putString("username",userData.getUsername());
        editor.putString("email", userData.getEmail());
        editor.putString("contact",userData.getContact());

        editor.putString("auth_token",authToken);
        editor.apply();
    }

    public static void setToken(SharedPreferences preferences , String authToken){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("auth_token",authToken);
        editor.apply();
    }

    public static String getAuthToken(SharedPreferences preferences){
        return preferences.getString("auth_token",null);
    }

}
