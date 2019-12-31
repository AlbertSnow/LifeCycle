package com.sogou.babel.testfragment.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class LeftAlignRecyclerView extends RecyclerView {

    public LeftAlignRecyclerView(Context context) {
        super(context);
    }

    public LeftAlignRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LeftAlignRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        int viewCount = getChildCount();

        for (int i = 0; i < viewCount; i++) {
            View childView = getChildAt(i);
            if (childView == null) {
                continue;
            }

            int leftParams = 0;

            int j = i - 1;
            if (j >= 0) {
                View preView = getChildAt(j);
                if (preView != null && (preView.getTop() == childView.getTop())) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) preView.getLayoutParams();
                    leftParams += preView.getRight() + params.rightMargin;
                }
            }

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) childView.getLayoutParams();
            leftParams += params.leftMargin;

            int width = childView.getWidth();
            childView.setLeft(leftParams);
            childView.setRight(leftParams + width);
        }

    }


}