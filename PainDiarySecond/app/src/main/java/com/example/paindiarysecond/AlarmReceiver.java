package com.example.paindiarysecond;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"The set time is up, please fill out the form as soon as possible. ",Toast.LENGTH_LONG).show();
        Log.e("alarm status","succeed");

    }
}
