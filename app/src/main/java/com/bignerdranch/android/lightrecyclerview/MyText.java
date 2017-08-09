package com.bignerdranch.android.lightrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2017/8/8/008.
 */

public class MyText extends View {
    private static final String TAG = "MyText";
    public MyText(Context context) {
        super(context);
    }

    public MyText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyText);
        
        String text = a.getString(R.styleable.MyText_textString);
        int i = a.getInteger(R.styleable.MyText_textInt, 0);

        Log.i(TAG, "MyText: text: " + text + ", i: " + i);
        a.recycle();
    }
}
