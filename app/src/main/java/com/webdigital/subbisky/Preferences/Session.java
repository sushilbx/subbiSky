package com.webdigital.subbisky.Preferences;

import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;

import com.webdigital.subbisky.MainActivity;
import com.webdigital.subbisky.activity.Login;
import com.webdigital.subbisky.MainActivity;
import com.webdigital.subbisky.activity.Login;

import java.util.HashMap;
public class Session {
    private SharedPreferences prefs;
    Context _context;
    SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "Subbisky";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_USER_ID="user_id";
    public static final String KEY_USER_TYPE="user_type";
    public static final String TOKEN="";
    public static final String SELLER_CATEGORY_ID="";
    public static final String MainUserType="usertype";

    private static final String fromDate = "fromDate";
    private static final String toDate = "toDate";
    private static final String roomJson="room";
    private static final String adultJson="adult";
    private static final String childJson="child";

//    SharedPreferences sharedPreferences;
    String ACCESS_TOKEN_TYPE="access_token_type";
    String ACCESS_TOKEN="access_token";
    public Session(Context cntx) {
        this._context=cntx;
        prefs = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = prefs.edit();
    }

    public void setRoomInfo(String from, String to, String room, String adult, String child) {
        editor.putString(fromDate,from).commit();
        editor.putString(toDate,to).commit();
        editor.putString(roomJson,room).commit();
        editor.putString(adultJson,adult).commit();
        editor.putString(childJson,child).commit();
    }

    public String getFrom() {return prefs.getString(fromDate, null);}

    public String getTo() {
        return prefs.getString(toDate, null);
    }

    public String getRoomJson() {
        return prefs.getString(roomJson, null);
    }

    public String getAdultJson() {
        return prefs.getString(adultJson, null);
    }

    public String getChildJson() {
        return prefs.getString(childJson, null);
    }

    public void saveFCM(String token) {
        editor.putString(KEY_USER_ID, token).commit();
    }

    public void createLoginSession(String user_id,String user_type)
    {
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_USER_ID, user_id);
        editor.putString(KEY_USER_TYPE,user_type);
        editor.commit();
    }
    public void saveSession(String accessTokenType,String accessToken){
        editor.putBoolean(IS_LOGIN,true).commit();
        editor.putString(ACCESS_TOKEN_TYPE,accessTokenType).commit();
        editor.putString(ACCESS_TOKEN,accessToken).commit();
    }

    public String getAccessToken(){
        return prefs.getString(ACCESS_TOKEN,null);
    }
    public String getAccessTokenType(){
        return prefs.getString(ACCESS_TOKEN_TYPE,null);
    }
    public void createLoginSessionToken(String Token)
    {
        editor.putString(TOKEN, Token);
        editor.commit();
    }

    public void createUserTypeSessionToken(String mainusertype)
    {
        editor.putString(MainUserType, mainusertype);
        editor.commit();
    }

    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, Login.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
        else
        {
            Intent intent = new Intent(_context, MainActivity.class);
            _context.startActivity(intent);
        }
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_USER_ID, prefs.getString(KEY_USER_ID,null));
        user.put(KEY_USER_TYPE,prefs.getString(KEY_USER_TYPE,null));
        user.put(TOKEN,prefs.getString(TOKEN,null));
        user.put(MainUserType,prefs.getString(MainUserType,null));
        return user;
    }



    //  Clear session details
    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    //Get Login State
    public boolean isLoggedIn(){
        return prefs.getBoolean(IS_LOGIN, false);
//        return prefs.getBoolean(MainUserType, false);

    }
//    public boolean userType(){
//        return prefs.getBoolean(MainUserType, false);
////        return prefs.getBoolean(MainUserType, false);
//
//    }
    public String getSellerCategoryId(int id) {
        editor.putInt(SELLER_CATEGORY_ID, id);
        editor.commit();
        return null;
    }
}
