package com.bignerdranch.android.lightrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2017/8/9/009.
 */

public class LinearItemDivider extends RecyclerView.ItemDecoration {
    //系统自带的属性名
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    private Drawable mDrawable;
    public LinearItemDivider(Context context) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDrawable = a.getDrawable(0);
        a.recycle();
    }
}
