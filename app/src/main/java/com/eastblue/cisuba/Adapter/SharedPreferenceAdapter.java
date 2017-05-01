package com.eastblue.cisuba.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by josujong on 2017. 5. 1..
 */

public class SharedPreferenceAdapter {
    static final String PREF_USER_EMAIL = "user_email";
    static final String PREF_USER_NAME = "user_name";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserEmail(Context ctx, String userEmail) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, userEmail);
        editor.commit();
    }

    public static String getUserEmail(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL,"");
    }

    public static void setUserName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME,"");
    }

    public static void clearUserEmail(Context ctx) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }
}
