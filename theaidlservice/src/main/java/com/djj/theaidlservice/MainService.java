package com.djj.theaidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.djj.testinterface.MyBinder;

/**
 * Created by DongJunJie on 2016-8-23.
 */
public class MainService extends Service {

    MyBinder myBinder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("DJJ", "onBind");
        startThread();
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("DJJ", "onCreate");
        myBinder = new MyBinder();
    }

    /**
     * 这里开启一个打印的线程
     */
    public void startThread() {
        Thread myThread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while (i < 100) {
                            i += 10;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (myBinder != null) {
                                myBinder.makeCall(i);
                                Log.i("DJJ", " myBinder.makeCall(i):" + i);
                            }
                        }
                    }
                }
        );
        myThread.start();
    }
}
