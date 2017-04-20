package com.zhao.tracker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.zhao.tracker.service.UpdateTargetLocationService;

public class AwakeUTLServiceReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent it = new Intent(context,UpdateTargetLocationService.class);
        context.startService(it);
    }
}
