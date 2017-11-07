// ItestAidlInterface.aidl
package com.djj.theaidlservice;

// Declare any non-default types here with import statements

interface ItestAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

/**
* 获取当前的状态
* */
    int getCount(int count);
}
