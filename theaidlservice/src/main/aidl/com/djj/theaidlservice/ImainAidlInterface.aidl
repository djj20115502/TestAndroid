// ImainAidlInterface.aidl
package com.djj.theaidlservice;

// Declare any non-default types here with import statements
import com.djj.theaidlservice.ItestAidlInterface;
interface ImainAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void setCallBack(ItestAidlInterface callback);
}
