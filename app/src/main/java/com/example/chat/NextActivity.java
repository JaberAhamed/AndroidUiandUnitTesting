package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class NextActivity extends AppCompatActivity {


    EditText userName;
    EditText userEmail;
    DatePicker datePicker;
    SharedPreferencesHelper preferencesHelper;
    EmailValidator emailValidator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        userName = findViewById(R.id.userNameInput);
        userEmail = findViewById(R.id.emailInput);
        datePicker = findViewById(R.id.dateOfBirthInput);
        emailValidator = new EmailValidator();

        userEmail.addTextChangedListener(emailValidator);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        preferencesHelper=new SharedPreferencesHelper(sharedPreferences);

        //populateUi();
    }

    private void populateUi() {
        SharedPreferenceEntry sharedPreferenceEntry;
        sharedPreferenceEntry = SharedPreferencesHelper.getPersonalInfo();
        userName.setText(sharedPreferenceEntry.getName());
        Calendar dateOfBirth = sharedPreferenceEntry.getDateOfBirth();
        datePicker.init(dateOfBirth.get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH),
                dateOfBirth.get(Calendar.DAY_OF_MONTH), null);
        userEmail.setText(sharedPreferenceEntry.getEmail());
    }

    public void onSaveClick(View view) {
        if (!emailValidator.isValid()) {
            userEmail.setError("Invalid email");
            //Log.w(TAG, "Not saving personal information: Invalid email");
            return;
        }

        String name = userName.getText().toString();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
        String email = userEmail.getText().toString();

        SharedPreferenceEntry sharedPreferenceEntry =
                new SharedPreferenceEntry(name, dateOfBirth, email);

        boolean isSuccess = SharedPreferencesHelper.savePersonalInfo(sharedPreferenceEntry);
        if (isSuccess) {
            Toast.makeText(this, "Personal information saved", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Personal information NOT saved", Toast.LENGTH_LONG).show();
        }


    }

    public void onRevertClick(View view) {
        populateUi();
        Toast.makeText(this, "Personal information reverted", Toast.LENGTH_LONG).show();
    }
}

