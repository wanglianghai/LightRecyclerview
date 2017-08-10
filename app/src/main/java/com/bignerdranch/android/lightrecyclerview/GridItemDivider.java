package com.bignerdranch.android.lightrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/8/9/009.
 */

//这个类在调用完覆盖方法会被销毁1
public class GridItemDivider extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[] {android.R.attr.listDivider};
    //1所有写这里没有
    private int spanCount;
    private int childCount;

    private Drawable mDivider;
    public GridItemDivider(Context context) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        init(parent);
        horizontal(c, parent);
        vertical(c, parent);
    }

    private void vertical(Canvas c, RecyclerView parent) {
        childCount -= getLastColNum();
        for (int i = 0; i < childCount; i++) {

            View v = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) v.getLayoutParams();
            /**
             * The right margin in pixels of the child. Margin values should be positive.
             * 这孩子右边的空白像素，空白像素值应该是正数*/
            final int left = v.getLeft() + params.leftMargin;
            final int right = v.getRight() + params.rightMargin;
            final int top = v.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void horizontal(Canvas c, RecyclerView parent) {
        for (int i = 0; i < childCount; i++) {
            if (spanCount - 1 == i % spanCount) {
                continue;
            }

            View v = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) v.getLayoutParams();
            /**
             * The right margin in pixels of the child. Margin values should be positive.
             * 这孩子右边的空白像素，空白像素值应该是正数*/
            final int left = v.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            final int top = v.getTop() + params.topMargin;
            final int bottom = v.getBottom() + params.bottomMargin;

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    //先执行
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        init(parent);
        horizontalOffsets(outRect);
        verticalOffsets(outRect);
    }

    private void verticalOffsets(Rect outRect) {
        childCount -= getLastColNum();
        for (int i = 0; i < childCount; i++) {
            outRect.set(0, 0, mDivider.getIntrinsicHeight(), 0);
        }
    }

    private int getLastColNum() {
        int lastColNum = childCount % spanCount;
        if (lastColNum == 0) {
            lastColNum = spanCount;
        }
        return lastColNum;
    }

    private void horizontalOffsets(Rect outRect) {
        for (int i = 0; i < childCount; i++) {
            if (spanCount - 1 == i % spanCount) {
                continue;
            }
            outRect.set(0, 0, mDivider.getIntrinsicHeight(), 0);
        }
    }

    private void init(RecyclerView parent) {
        childCount = parent.getChildCount();
        GridLayoutManager g = (GridLayoutManager) parent.getLayoutManager();
        spanCount = g.getSpanCount();
    }
}
