package com.example.chat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.ArrayList;

import ir.beigirad.zigzagview.ZigzagView;

public class MainActivity extends AppCompatActivity {

    EditText chatETID;
    ImageView sendBtnID;
    int v=45;
    ArrayList<Integer>integers = new ArrayList<>();

    LinearLayout linearLayout;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatETID = (EditText) findViewById(R.id.chatETID);
        sendBtnID =(ImageView) findViewById(R.id.sendBtnID);


        //linearLayout.setBackgroundColor(Color.RED);
        chatETID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                sendBtnID.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




        Button button = findViewById(R.id.btn_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),NextActivity.class));
            }
        });

    }

   /* public void next(View view) {
        startActivity(new Intent(this,NextActivity.class));

    }*/
}
