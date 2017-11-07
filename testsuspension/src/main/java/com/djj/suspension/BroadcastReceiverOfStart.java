package com.djj.suspension;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by DongJunJie on 2016-11-18.
 */

public class BroadcastReceiverOfStart extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent actIntent = new Intent(context, SuspensionService.class);
//        Toast.makeText(context, "开机广播", Toast.LENGTH_SHORT).show();
        context.startService(actIntent);
    }
}
