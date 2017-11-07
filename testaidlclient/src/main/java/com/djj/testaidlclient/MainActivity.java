package com.djj.testaidlclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static MainActivity sInstance;
    View mDecorView;
    Button mButton;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sInstance = this;
        setContentView(R.layout.activity_main);
        mDecorView = getWindow().getDecorView();
        mButton = (Button) mDecorView.findViewById(R.id.main_button);
        mTextView = (TextView) mDecorView.findViewById(R.id.textView);
        mButton.setOnClickListener(new MyCliclListener());
    }
}
