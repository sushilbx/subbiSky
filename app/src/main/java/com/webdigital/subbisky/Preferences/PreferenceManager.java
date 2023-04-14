package com.webdigital.subbisky.Preferences;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;

public class PreferenceManager {
    SharedPreferences prefrencemanager;
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // Shared preferences file name
    private static final String PREF_NAME = "Subbisky";
    // All Shared Preferences Keys
    private static final String NAME = "name";
    public PreferenceManager(Context context) {
        this._context = context;
        prefrencemanager = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = prefrencemanager.edit();
    }

    public String getName(String name) {
        return prefrencemanager.getString(NAME, null);
    }

    public void setName(String name) {
        editor.putString(NAME, name);
        editor.commit();
    }

    }

