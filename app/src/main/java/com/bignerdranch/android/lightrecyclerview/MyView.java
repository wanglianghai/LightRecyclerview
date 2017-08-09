package com.bignerdranch.android.lightrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/8/008.
 */
// 自定义一个CustomView(extends View )类
// 编写values/attrs.xml，在其中编写styleable和item等标签元素
// 在布局文件中CustomView使用自定义的属性（注意namespace）
// 在CustomView的构造方法中通过TypedArray获取, 不获取没有值去设置，父类View里面有设置值的
public class MyView extends View {
    private Paint mPaint = new Paint();
    private static final String mString = "Welcome to Mr Wei's blog";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyView);

        int color = a.getColor(R.styleable.MyView_textColor, Color.GREEN);
        float size = a.getDimension(R.styleable.MyView_textSize, 36);

        mPaint.setColor(color);
        mPaint.setTextSize(size);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText(mString, 0, 110, mPaint);

        //设置画笔颜色
        mPaint.setColor(Color.YELLOW);
        //设置填充
        mPaint.setStyle(Paint.Style.FILL);

        //画一个矩形,前俩个是矩形左上角坐标，后面俩个是右下角坐标
        canvas.drawRect(0, 0, 100, 100, mPaint);
    }
}
