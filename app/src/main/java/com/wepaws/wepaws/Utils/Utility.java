package com.wepaws.wepaws.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
