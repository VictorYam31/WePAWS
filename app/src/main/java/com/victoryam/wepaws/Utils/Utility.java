package com.victoryam.wepaws.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.victoryam.wepaws.Domain.Category;
import com.victoryam.wepaws.Domain.Clinic;
import com.victoryam.wepaws.Domain.Dining;
import com.victoryam.wepaws.Domain.Hotel;
import com.victoryam.wepaws.Domain.Park;
import com.victoryam.wepaws.Domain.Store;
import com.victoryam.wepaws.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Utility {

/*    public Utility() {

    }*/

    public int getLocale(Context mContext) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(mContext);
        return pref.getInt("lang", 0);
    }

    public String getUsernameFromSharePreference(Context mContext) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String name = preferences.getString("Name", "Guest");
        return name;
    }

    public void deleteUsernameFromSharePreference(Context mContext) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        preferences.edit().remove("Name").commit();
    }

    public void saveUsernameToSharePreference(Context mContext, String userName) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Name", userName);
        editor.apply();
    }
}
