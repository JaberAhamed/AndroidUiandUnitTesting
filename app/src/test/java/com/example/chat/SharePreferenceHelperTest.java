package com.example.chat;

import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SharePreferenceHelperTest {

    private static final String TEST_NAME = "Test Name";
    private static final String TEST_EMAIL = "test@gmail.com";

    private static final Calendar TEST_DATE_OF_BIRTH = Calendar.getInstance();
    static {
        TEST_DATE_OF_BIRTH.set(1980, 1, 1);
    }

    private SharedPreferenceEntry msharedPreferenceEntry;
    private SharedPreferencesHelper mpreferencesHelper;
    private SharedPreferencesHelper mBrokenPreferencesHelper;

    @Mock
    SharedPreferences mSharedPreferences;

    @Mock
    SharedPreferences mBrokenSharedPreferences;



    @Mock
    SharedPreferences.Editor mMockEditor;


    @Mock
    SharedPreferences.Editor mMockBrokenEditor;


    @Before
    public void initMocks(){

        msharedPreferenceEntry = new SharedPreferenceEntry(TEST_NAME,TEST_DATE_OF_BIRTH,TEST_EMAIL);

        mpreferencesHelper = createMockSharePreference();
        mBrokenPreferencesHelper = createBrokenMockSharedPreference();



    }



    private SharedPreferencesHelper createBrokenMockSharedPreference() {

        when(mMockBrokenEditor.commit()).thenReturn(false);
        // Return the broken MockEditor when requesting it.
        when(mBrokenSharedPreferences.edit()).thenReturn(mMockBrokenEditor);
        return new SharedPreferencesHelper(mBrokenSharedPreferences);
    }

    @Test
    public void sharedPreferencesHelper_SavePersonalInformationFailed_ReturnsFalse() {
        // Read personal information from a broken SharedPreferencesHelper
        boolean success =
                mBrokenPreferencesHelper.savePersonalInfo(msharedPreferenceEntry);
        assertThat("Makes sure writing to a broken SharedPreferencesHelper returns false", success,
                is(false));
    }


    @Test
    public void sharedPreferencesHelper_SaveAndReadPersonalInformation() {
        // Save the personal information to SharedPreferences
        boolean success = mpreferencesHelper.savePersonalInfo(msharedPreferenceEntry);
        assertThat("Checking that SharedPreferenceEntry.save... returns true",
                success, is(true));
        // Read personal information from SharedPreferences
        SharedPreferenceEntry savedSharedPreferenceEntry =
                mpreferencesHelper.getPersonalInfo();
        // Make sure both written and retrieved personal information are equal.
        assertThat("Checking that SharedPreferenceEntry.name has been persisted and read correctly",
                msharedPreferenceEntry.getName(),
                is(equalTo(savedSharedPreferenceEntry.getName())));
        assertThat("Checking that SharedPreferenceEntry.dateOfBirth has been persisted and read "
                        + "correctly",
                msharedPreferenceEntry.getDateOfBirth(),
                is(equalTo(savedSharedPreferenceEntry.getDateOfBirth())));
        assertThat("Checking that SharedPreferenceEntry.email has been persisted and read "
                        + "correctly",
                msharedPreferenceEntry.getEmail(),
                is(equalTo(savedSharedPreferenceEntry.getEmail())));
    }

    private SharedPreferencesHelper createMockSharePreference() {

        when(mSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_NAME), anyString()))
                .thenReturn(msharedPreferenceEntry.getName());
        when(mSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_EMAIL), anyString()))
                .thenReturn(msharedPreferenceEntry.getEmail());
        when(mSharedPreferences.getLong(eq(SharedPreferencesHelper.KEY_DOB), anyLong()))
                .thenReturn(msharedPreferenceEntry.getDateOfBirth().getTimeInMillis());
        // Mocking a successful commit.


        // Return the MockEditor when requesting it.

        when(mMockEditor.commit()).thenReturn(true);
        when(mSharedPreferences.edit()).thenReturn(mMockEditor);
        return new SharedPreferencesHelper(mSharedPreferences);
    }


}
