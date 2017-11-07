package com.djj.testinterface;

import android.os.RemoteException;
import android.util.Log;

import com.djj.theaidlservice.ImainAidlInterface;
import com.djj.theaidlservice.ItestAidlInterface;

/**
 * Created by DongJunJie on 2016-8-23.
 */
public class MyBinder extends ImainAidlInterface.Stub {

    ItestAidlInterface mCallBack;

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble,
                           String aString) throws RemoteException {
    }

    @Override
    public void setCallBack(ItestAidlInterface callback) throws RemoteException {
        mCallBack = callback;
    }

    public void makeCall(int count) {
        Log.i("DJJ","makeCall:"+count);
        if (mCallBack != null) {
            try {
                mCallBack.getCount(count);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
