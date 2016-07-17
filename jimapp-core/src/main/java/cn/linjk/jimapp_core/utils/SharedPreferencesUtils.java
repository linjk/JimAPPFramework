package cn.linjk.jimapp_core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Map;

import cn.linjk.jimapp_core.app.JimAppBase;

/**
 * Created by LinJK on 7/17/16.
 */
public class SharedPreferencesUtils {

    private static final String TAG          = "SharedPreferencesUtils";
    private static final String DEFAULT_NAME = AndroidUtils.getPackageName();

    public static void savePreference(String key, String value) {
        if (value != null) {
            SharedPreferences.Editor editor = getEditor();

            editor.putString(key, value);

            editor.commit();
        }
        else {
            Log.e(TAG, "value == null !");
        }
    }

    public static void savePreference(String key, Boolean value) {
        if (value != null) {
            SharedPreferences.Editor editor = getEditor();

            editor.putBoolean(key, value);

            editor.commit();
        }
    }

    public static void savePreference(String key, Integer value) {
        if (value != null) {
            SharedPreferences.Editor editor = getEditor();

            editor.putInt(key, value);

            editor.commit();
        }
    }

    public static void savePreference(String key, Long value) {
        if (value != null) {
            SharedPreferences.Editor editor = getEditor();

            editor.putLong(key, value);

            editor.commit();
        }
    }

    public static void savePreference(String key, Float value) {
        if (value != null) {
            SharedPreferences.Editor editor = getEditor();

            editor.putFloat(key, value);

            editor.commit();
        }
    }

    public static SharedPreferences.Editor getEditor(String name) {
        return getSharedPreferences(name).edit();
    }

    public static SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }


    public static Map<String, ?> loadAllPreferences() {
        return getSharedPreferences().getAll();
    }

    public static String loadPreference(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    public static String loadPreference(String key) {
        return getSharedPreferences().getString(key, null);
    }

    public static boolean hasPreference(String key) {
        return getSharedPreferences().contains(key);
    }

    public static Boolean loadPreferenceAsBoolean(String key, Boolean defaultValue) {
        Boolean value = defaultValue;

        if (hasPreference(key)) {
            value = getSharedPreferences().getBoolean(key, false);
        }

        return value;
    }

    public static Boolean loadPreferenceAsBoolean(String key) {
        return loadPreferenceAsBoolean(key, null);
    }

    public static Long loadPreferenceAsLong(String key, Long defaultValue) {
        Long value = defaultValue;

        if (hasPreference(key)) {
            value = getSharedPreferences().getLong(key, 0L);
        }

        return value;
    }

    public static Long loadPreferenceAsLong(String key) {
        return loadPreferenceAsLong(key, null);
    }

    public static Integer loadPreferenceAsInteger(String key, Integer defaultValue) {
        Integer value = defaultValue;

        if (hasPreference(key)) {
            value = getSharedPreferences().getInt(key, 0);
        }

        return value;
    }

    public static Integer loadPreferenceAsInteger(String key) {
        return loadPreferenceAsInteger(key, null);
    }

    public static Float loadPreferenceAsFloat(String key, Float defaultValue) {
        Float value = defaultValue;

        if (hasPreference(key)) {
            value = getSharedPreferences().getFloat(key, 0);
        }

        return value;
    }

    public static Float loadPreferenceAsFloat(String key) {
        return loadPreferenceAsFloat(key, null);
    }

    public static void removePreferences(String... keys) {
        SharedPreferences.Editor editor = getEditor();

        for (String key : keys) {
            editor.remove(key);
        }

        editor.commit();
    }

    public static void removeAllPreferences(String name) {
        SharedPreferences.Editor editor = getEditor();

        editor.clear();

        editor.commit();
    }

    public static void removeAllPreferences() {
        removeAllPreferences(DEFAULT_NAME);
    }

    //================================================

    private static SharedPreferences getSharedPreferences(String name) {
        return JimAppBase.get().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getSharedPreferences() {
        return getSharedPreferences(DEFAULT_NAME);
    }
}
