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

    public static final int HORIZONTAL = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;

    public int mOrientation;
    private Drawable mDrawable;
    public LinearItemDivider(Context context, int orientation) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDrawable = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        //mOrientation初始值是0就是LinearLayoutManager.HORIZONTAL的值所有不会报异常
        //并且就应该判断传入的值是否合法
        /*if (mOrientation != HORIZONTAL && mOrientation != VERTICAL) {
            throw new IllegalArgumentException("invalid orientation");
        }*/
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException("invalid orientation");
        }

        mOrientation = orientation;
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
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else {
            horizontal(c, parent);
        }
    }

    private void horizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop(); //padding填充（视图自己的）
        final int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            //根据父视图的右边界算了margin值和padding的值（不正确）
            //item view是FrameLayout不是TextView
            /**
             * Right position of this view relative to its parent.
             *
             *视图右边的位置相对于他的父视图
             * @return The right edge of this view, in pixels.
             */

            final int rightChild = child.getRight();
            final int rightMarginChild = params.rightMargin;
            final int leftMargin = params.leftMargin;
            final int left = rightChild + rightMarginChild;
            final int right = left + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(c);
        }
    }

    private void drawVertical(Canvas c, RecyclerView parent) {
        //left padding的值
        final int left = parent.getPaddingLeft();
        final int right = parent.getRight() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            /**
             * {@link android.view.ViewGroup.MarginLayoutParams LayoutParams} subclass for children of
             * {@link RecyclerView}.
             * LayoutParams的子类用于RecyclerView的子视图。
             */
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            //底部的margin值,不覆盖这视图的内容
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
