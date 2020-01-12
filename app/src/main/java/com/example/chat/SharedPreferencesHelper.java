package com.example.chat;

import android.content.SharedPreferences;

import java.util.Calendar;

public  class SharedPreferencesHelper {

    static final String KEY_NAME = "key_name";
    static final String KEY_DOB = "key_dob_millis";
    static final String KEY_EMAIL = "key_email";

    private static SharedPreferences mSharedPreferences;

    public SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    public static boolean savePersonalInfo(SharedPreferenceEntry sharedPreferenceEntry){

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_NAME, sharedPreferenceEntry.getName());
        editor.putLong(KEY_DOB, sharedPreferenceEntry.getDateOfBirth().getTimeInMillis());
        editor.putString(KEY_EMAIL, sharedPreferenceEntry.getEmail());

        return editor.commit();
    }

    public static SharedPreferenceEntry getPersonalInfo() {
        // Get data from the SharedPreferences.
        String name = mSharedPreferences.getString(KEY_NAME, "");
        Long dobMillis =
                mSharedPreferences.getLong(KEY_DOB, Calendar.getInstance().getTimeInMillis());
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTimeInMillis(dobMillis);
        String email = mSharedPreferences.getString(KEY_EMAIL, "");
        // Create and fill a SharedPreferenceEntry model object.
        return new SharedPreferenceEntry(name, dateOfBirth, email);
    }
}
