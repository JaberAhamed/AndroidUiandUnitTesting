package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

public class CheckActivity extends AppCompatActivity {
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);


       /* ReceiverName receiverName=new ReceiverName();
        intentFilter = new IntentFilter("com.journaldev.broadcastreceiver.SOME_ACTION");
        registerReceiver(receiverName,intentFilter);*/

       // Toast.makeText(this, String.valueOf(isConnected()), Toast.LENGTH_SHORT).show();

        if("huawei".equalsIgnoreCase(android.os.Build.MANUFACTURER)) {
            AlertDialog.Builder builder  = new AlertDialog.Builder(this);
            builder.setTitle("text").setMessage("text")
                    .setPositiveButton("protected", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
                            startActivity(intent);
                            //sp.edit().putBoolean("protected",true).commit();
                        }
                    }).create().show();
        }
    }


    public boolean isConnected() {

        ConnectivityManager cm = (ConnectivityManager) this .getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }


}
