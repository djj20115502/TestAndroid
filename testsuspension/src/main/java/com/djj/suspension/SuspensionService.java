package com.djj.suspension;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by DongJunJie on 2016-11-18.
 */

public class SuspensionService extends Service {
    private WindowManager wManager;// 窗口管理者
    private WindowManager.LayoutParams mParams;// 窗口的属性
    private View myView;
    private boolean flag = true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Log.i("djjtest",
                "-----------------------------------onCreate" +
                        "-----------------------------------------------");
        wManager = (WindowManager) getApplicationContext().getSystemService(
                Context.WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;// 系统提示window
        // mParams.format = PixelFormat.TRANSLUCENT;// 支持透明
        // mParams.format = PixelFormat.RGBA_8888;
        mParams.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;// 焦点
        mParams.flags |= WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE;
        Point point = new Point();
        wManager.getDefaultDisplay().getSize(point);
        mParams.width = point.x;
        mParams.height = point.y;
        mParams.x = 0;// 窗口位置的偏移量
        mParams.y = 0;
        // mParams.alpha = 0.1f;//窗口的透明度
        myView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_main, null);
        myView.setBackgroundResource(R.mipmap.ic_launcher);
        myView.setFocusable(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Log.i("djjtest",
                "-----------------------------------onStartCommand" +
                        "-----------------------------------------------");
        if (flag) {
            flag = false;
            wManager.addView(myView, mParams);// 添加窗口
            myView.post(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    myView.requestFocus();
                }
            });
            Log.i("djjtest",
                    "----------------------------------- " +
                            "添加窗口-----------------------------------------------");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        if (myView.getParent() != null)
            wManager.removeView(myView);// 移除窗口
        super.onDestroy();
    }
}
