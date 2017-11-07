package com.konkaj.testrxjava;

import android.app.Activity;
import android.os.Bundle;


/**
 * Created by DongJunJie on 2017-2-28.
 */

public class RxJavaActivity extends Activity {


    public static final String TAG = RxJavaActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Functions.testRequested();
    }
}
