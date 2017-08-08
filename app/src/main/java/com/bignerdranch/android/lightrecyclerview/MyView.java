package com.bignerdranch.android.lightrecyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/8/008.
 */

public class MyView extends View {
    private Paint mPaint = new Paint();
    private Context mContext;
    private static final String mString = "Welcome to Mr Wei's blog";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔颜色
        mPaint.setColor(Color.YELLOW);
        //设置填充
        mPaint.setStyle(Paint.Style.FILL);

        //画一个矩形,前俩个是矩形左上角坐标，后面俩个是右下角坐标
        canvas.drawRect(0, 0, 100, 100, mPaint);

        mPaint.setColor(Color.RED);
        canvas.drawText(mString, 0, 110, mPaint);
    }
}
