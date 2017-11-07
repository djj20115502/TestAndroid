package com.djj.testaidlclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.djj.theaidlservice.ImainAidlInterface;
import com.djj.theaidlservice.ItestAidlInterface;

/**
 * Created by DongJunJie on 2016-8-23.
 */
public class MyCliclListener implements View.OnClickListener {

    /**
     * 服务器端传递的对象
     */
    ImainAidlInterface mMainInerface;
    ItestAidlInterface mTest;
    private ServiceConnection serConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMainInerface = null;
            Log.i("DJJ", "onServiceDisconnected");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("DJJ", "AIDLClient.onServiceConnected()...");
            mMainInerface = ImainAidlInterface.Stub.asInterface(service);
            mTest = new InterfaceCallBack();
            try {
                mMainInerface.setCallBack(mTest);
                Log.i("DJJ", "mMainInerface.setCallBack(mTest)");
            } catch (RemoteException e) {
                Log.i("DJJ", "插入错误！");
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onClick(View view) {
        Log.i("DJJ", "onClick");
        if (mMainInerface == null) {
            Intent mIntent = new Intent("android.intent.action.theaidlservice");
            MainActivity.sInstance.bindService(mIntent, serConn, Activity.BIND_AUTO_CREATE);
        } else {
            Log.i("DJJ", "已连接");
        }
    }
}
