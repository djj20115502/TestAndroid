package com.djj.testintent;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    LayoutInflater.from(MainActivity.this).inflate(R.layout.test, null);
                    hookWebView();
                    Toast.makeText(MainActivity.this, "加载也没问题", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        try {
            WebView a = new WebView(this);
            Toast.makeText(this, "没问题", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void testIntent() {
        Intent i = new Intent("com.djj.textview.djj");
        Bundle b = new Bundle();
        i.putExtra("djj", "你好啊");
        startActivity(i);
//                startActivityForResult(i,1);
    }

    public static void hookWebView() {
        int sdkInt = Build.VERSION.SDK_INT;
        try {
            Class e = Class.forName("android.webkit.WebViewFactory");
            Field field = e.getDeclaredField("sProviderInstance");
            field.setAccessible(true);
            Object sProviderInstance = field.get((Object) null);
            if (sProviderInstance != null) {

                return;
            }
            Method getProviderClassMethod;
            if (sdkInt > 22) {
                getProviderClassMethod = e.getDeclaredMethod("getProviderClass", new Class[0]);
            } else {
                if (sdkInt != 22) {

                    return;
                }
                getProviderClassMethod = e.getDeclaredMethod("getFactoryClass", new Class[0]);
            }
            getProviderClassMethod.setAccessible(true);
            Class providerClass = (Class) getProviderClassMethod.invoke(e, new Object[0]);
            Class delegateClass = Class.forName("android.webkit.WebViewDelegate");
            Constructor providerConstructor = providerClass.getConstructor(new
                    Class[]{delegateClass});
            if (providerConstructor != null) {
                providerConstructor.setAccessible(true);
                Constructor declaredConstructor = delegateClass.getDeclaredConstructor(new
                        Class[0]);
                declaredConstructor.setAccessible(true);
                sProviderInstance = providerConstructor.newInstance(new
                        Object[]{declaredConstructor.newInstance(new Object[0])});
                Log.i("sProviderInstance:{}", sProviderInstance.toString());
                field.set("sProviderInstance", sProviderInstance);
            }

        } catch (Throwable var9) {
            var9.printStackTrace();
        }
    }
}
