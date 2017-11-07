package com.djj.testaidlclient;

import android.os.RemoteException;
import android.util.Log;

import com.djj.theaidlservice.ItestAidlInterface;

/**
 * Created by DongJunJie on 2016-8-23.
 */
public class InterfaceCallBack extends ItestAidlInterface.Stub {


    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble,
                           String aString) throws RemoteException {
    }

    @Override
    public int getCount(int count) throws RemoteException {
        Log.i("DJJ","count:"+count);
        return 0;
    }
}
