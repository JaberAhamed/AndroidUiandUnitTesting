package com.example.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class ReceiverName extends BroadcastReceiver {

    private static final String LOG_TAG = "NetworkChangeReceiver";
    private boolean isConnected = false;
    @Override
    public void onReceive(Context context, Intent intent) {

       // Log.v(LOG_TAG, "Receieved notification about network status");
        isNetworkAvailable(context);

       // ConnectivityManager cm = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        /*if (cm == null)

            return;*/

       /* ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            try {
                Toast.makeText(context, "Network is connected", Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "Network is changed or reconnected", Toast.LENGTH_LONG).show();
        }*/
        /*if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()) {
            // Send here

            Toast.makeText(context, String.valueOf(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected()), Toast.LENGTH_SHORT).show();
        } else {
            // Do nothing or notify user somehow
            Toast.makeText(context, "Not Connected", Toast.LENGTH_SHORT).show();
        }*/

    }
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        if (!isConnected) {
                           // Log.v(LOG_TAG, "Now you are connected to Internet!");
                            Toast.makeText(context, "Internet availablle via Broadcast receiver", Toast.LENGTH_SHORT).show();
                            isConnected = true;
                            // do your processing here ---
                            // if you need to post any data to the server or get
                            // status
                            // update from the server
                        }
                        return true;
                    }
                }
            }
        }
       // Log.v(LOG_TAG, "You are not connected to Internet!");
        Toast.makeText(context, "Internet NOT availablle via Broadcast receiver", Toast.LENGTH_SHORT).show();
        isConnected = false;
        return false;
    }



}
