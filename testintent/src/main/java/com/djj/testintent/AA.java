package com.djj.testintent;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DongJunJie on 2017-9-27.
 */

public class AA extends View {



    public AA(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AA(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
