package com.qiniu.qplayer2.repository.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.StringRes;
import androidx.preference.PreferenceManager;


public class SharedPreferencesHelper {
    private Context applicationContext;
    private SharedPreferences sharedPreferences;
    private void setApplicationContext(Context applicationContext){
        this.applicationContext=applicationContext;
    }
    private void setSharedPreferences(SharedPreferences sharedPreferences){
        this.sharedPreferences=sharedPreferences;
    }
    public SharedPreferencesHelper(Context context,SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        applicationContext = context.getApplicationContext();
    }

    public SharedPreferencesHelper(Context context, String prefName) {
        this(context,context.getSharedPreferences(prefName, Context.MODE_PRIVATE));
    }

    public SharedPreferencesHelper(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        applicationContext = context.getApplicationContext();
    }

    // ----- String -----
    public String optString(String key,String defaultValue){
        return sharedPreferences.getString(key, defaultValue);
    }

    public String optString(int keyResId,String defaultValue) {
        String key = applicationContext.getString(keyResId);
        return optString(key, defaultValue);
    }

    public void setString(String key,String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    // ----- integer -----
    public int optInteger(String key,int defaultValue){
        try {
            return sharedPreferences.getInt(key, defaultValue);
        } catch (ClassCastException e) {
            try {
                String strValue = sharedPreferences.getString(key, null);
                if (TextUtils.isEmpty(strValue))
                    return defaultValue;
                else {
                    try {
                        return Integer.valueOf(strValue);
                    } catch (NumberFormatException e2) {
                        return defaultValue;
                    }
                }
            } catch (ClassCastException e1) {
//                DebugLog.printStackTrace(e1)
            }
        }
        return defaultValue;
    }

    // ----- float -----
    public float optFloat(String key,float defaultValue) {
        try {
            return sharedPreferences.getFloat(key, defaultValue);
        } catch (ClassCastException e) {

        }
        return defaultValue;
    }

    public int optInteger(int keyResId,int defaultValue) {
        String key = applicationContext.getString(keyResId);
        return optInteger(key, defaultValue);
    }

    public void setInteger(String key,int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    // ----- boolean -----
    public boolean optBoolean(String key,boolean defaultValue) {
        try {
            return sharedPreferences.getBoolean(key, defaultValue);
        } catch (ClassCastException e) {
            try {
                String strValue = sharedPreferences.getString(key, null);
                if (TextUtils.isEmpty(strValue))
                    return defaultValue;
                else{
                    try {
                        return java.lang.Boolean.valueOf(strValue);
                    } catch (NumberFormatException e2) {
                        return defaultValue;
                    }
                }
            } catch (ClassCastException e1) {
//                DebugLog.printStackTrace(e1)
            }
        }
        return defaultValue;
    }

    public boolean optBoolean(int keyResId,boolean defaultValue) {
        String key = applicationContext.getString(keyResId);
        return optBoolean(key, defaultValue);
    }

    public void setBoolean(String key,boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }
    //todo 方法名应该是写错了的
    public void setBoolean(String key,float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }


    public void setBoolean(int keyResId,boolean value) {
        sharedPreferences.edit().putBoolean(applicationContext.getString(keyResId), value)
                .apply();
    }

    public long optLong(String key,long value) {
        try {
            return sharedPreferences.getLong(key, value);
        } catch (ClassCastException e) {
            try {
                String strValue = sharedPreferences.getString(key, null);
                if (TextUtils.isEmpty(strValue))
                    return value;
                try {
                    return Long.valueOf(strValue);
                } catch (NumberFormatException e2) {
                    return value;
                }
            } catch (ClassCastException e1) {
                return value;
            }
        }
    }

    public void setLong(String key,long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void remove(@StringRes int keyResId) {
        remove(applicationContext.getString(keyResId));
    }

    public SharedPreferences.Editor edit() {
        return sharedPreferences.edit();
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }
}
