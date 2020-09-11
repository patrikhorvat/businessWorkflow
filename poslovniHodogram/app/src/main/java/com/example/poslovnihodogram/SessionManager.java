package com.example.poslovnihodogram;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by patrik on 9.3.2018..
 */

public class SessionManager {
    private static final String KEY_USER_ID = "user_id";
    private static final String DEFAULT_USER_ID = "0";
    private static final String KEY_USER_NAME = "key_user_name";
    private static final String DEFAULT_USER_NAME = "";
    private static final String DEFAULT_LAST_NAME = "";
    private static final String KEY_LAST_NAME = "key_last_name";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;
    private static final String PREF_NAME = "login";
    private static final String KEY_IS_LOGIN = "isLogin";
    private static final String KEY_FIRST_TIME_IN_APP = "key_first_time_in_app";
    private static final String DEFAULT_FIRST_TIME_IN_APP = "yes";

    // Constructor
    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    private static SharedPreferences getPreferences(Context c) {
        SharedPreferences sp = c.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return  sp;
    }

    public static boolean isUserLogin(Context context) {
        return getPreferences(context).getBoolean(KEY_IS_LOGIN, false);
    }

    public static void setUserLoggedIn(Context c, boolean isLogin) {
        SharedPreferences sp = getPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_IS_LOGIN, isLogin);
        editor.commit();

    }

    public static  void clearSession(Context c) {
        SharedPreferences sp = getPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public static void setFirstTimeInApp(Context c, String value){
        SharedPreferences sp = getPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_FIRST_TIME_IN_APP, value);
        editor.commit();
    }
    public static String getFirstTimeInApp(Context c){
        return getPreferences(c).getString(KEY_FIRST_TIME_IN_APP,DEFAULT_FIRST_TIME_IN_APP );
    }

    public static String getUserId(Context c) {
        return getPreferences(c).getString(KEY_USER_ID, DEFAULT_USER_ID);
    }

    public static void setUserId(Context c, String value) {
        SharedPreferences sp = getPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_USER_ID, value);
        editor.commit();
    }

    public static void setUserName(Context c, String value){
        SharedPreferences sp = getPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_USER_NAME, value);
        editor.commit();
    }

    public static String getuserName(Context c){
        return  getPreferences(c).getString(KEY_USER_NAME, DEFAULT_USER_NAME);
    }

    public static void setLastName(Context c, String value){
        SharedPreferences sp = getPreferences(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_LAST_NAME, value);
        editor.commit();
    }

    public static String getLastName(Context c){
        return  getPreferences(c).getString(KEY_LAST_NAME, DEFAULT_LAST_NAME);
    }
}


