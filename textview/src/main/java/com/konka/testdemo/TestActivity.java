package com.konka.testdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.konka.kkuserpaydemo.R;

/**
 * Created by DongJunJie on 2016-12-13.
 */
public class TestActivity extends Activity {
    PresenterOfTest mpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity_main);
        mpPresenter = new PresenterOfTest(this, getWindow().getDecorView());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("djjtest", "requestCode:" + requestCode + " resultCode:" + resultCode + " " +
                "data:");
        mpPresenter.onPayBack(requestCode, resultCode, data);
    }
}
