package com.bignerdranch.android.lightrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

/**
 * Draw any appropriate decorations into the Canvas supplied to the RecyclerView.
 * 画每一个适当的装饰进recyclerView提供的帆布中。
 * Any content drawn by this method will be drawn before the item views are drawn,
 * 每一个被这方法画的内容将画完在item views画完前，
 * and will thus appear underneath the views.
 * 因此在视图下面显示
 */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //left padding的值
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            android.support.v7.widget.RecyclerView v = new android.support.v7.widget.RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    //设置item间距
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDrawable.getIntrinsicHeight());
    }
}
