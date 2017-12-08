package com.joeydash.institutemobopsapp.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.joeydash.institutemobopsapp.Activities.MainActivity;

public class Manager {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private Context context;

    private static final String PREF_NAME = MainActivity.LOG_TAG;

    public Manager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }
    public String getPrefString(String stringPrefName,String stringDefault){
        return preferences.getString(stringPrefName,stringDefault);
    }
    public void setPreString(String stringPrefName,String stringPrefValue){
        editor.putString(stringPrefName, stringPrefValue);
        editor.commit();
    }
}
