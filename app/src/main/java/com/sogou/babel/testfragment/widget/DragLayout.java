package com.sogou.babel.testfragment.widget;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class DragLayout extends FrameLayout {

    private ViewDragHelper mDragHelper;
    private DragListener mListener;

//    @IdRes
//    private int  mDragViewID = NO_ID;
//    private View mDragView;

    // 记录最后的位置
//    private float mDragViewLastX = -1;
//    private float mDragViewLastY = -1;

    public DragLayout(Context context) {
        this(context, null);
    }

    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DragLayout, defStyleAttr, 0);
//        mDragViewID = a.getResourceId(R.styleable.DragLayout_drag_view_id, NO_ID);
//        a.recycle();

        mDragHelper = ViewDragHelper.create(this, 1.f, new ViewDragHelper.Callback() {

            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                if (!(child instanceof ImageView)) {
                    if (mListener != null) {
                        mListener.onDragStart(child);
                    }
                }
                return false;
            }

            @Override
            public int getViewHorizontalDragRange(@NonNull View child) {
                return 1;
            }

            @Override
            public int getViewVerticalDragRange(@NonNull View child) {
                if (child instanceof ImageView) {
                    return 1;
                } else {
                    return super.getViewVerticalDragRange(child);
                }
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {


                int newLeft = 0;

                if (child instanceof  ImageView) {
                    newLeft = clampMenuViewHorizontal(child, left);
                } else {
                    newLeft = clampContentViewHorizontal(child, left);
                }

                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
                final int topPadding = getPaddingTop();
                final int bottomPadding = getPaddingBottom();
                final int childHeight = child.getHeight();
                final int height = getHeight();

                int newTop;
                if (top < topPadding) {
                    newTop = topPadding;
                } else {
                    if (top > (height - bottomPadding - childHeight)) {
                        newTop = height - bottomPadding - childHeight;
                    } else {
                        newTop = top;
                    }
                }

                return newTop;
            }

            @Override
            public void onViewReleased(@NonNull View child, float xvel, float yvel) {
                if (child instanceof ImageView) {
                    onMenuViewRelease(child);
                } else {
                    onContentViewDrag(child);

                }
            }
        });
    }

    public void setDragListener(DragListener listener) {
        mListener = listener;
    }

    private void onContentViewDrag(@NonNull View child) {
        final int leftPadding = getPaddingLeft();
        final int rightPadding = getPaddingRight();
        final int width = getWidth();
        final float left = child.getX();

        int newLeft;
        if (left < width / 2.f) {
            newLeft = leftPadding;

            if (mListener != null) {
                mListener.onDragOver(false, child);
            }
        } else {
            newLeft = width - rightPadding;


            if (mListener != null) {
                mListener.onDragOver(true, child);
            }
        }

        mDragHelper.smoothSlideViewTo(child, newLeft, (int) child.getY());
        invalidate();
    }

    private void onMenuViewRelease(@NonNull View child) {
        final int leftPadding = getPaddingLeft();
        final int rightPadding = getPaddingRight();
        final int childWidth = child.getWidth();
        final int width = getWidth();
        final float left = child.getX();

        int newLeft;
        if ((left + childWidth / 2.0f) < width / 2.f) {
            newLeft = leftPadding;
        } else {
            newLeft = width - rightPadding - childWidth;
        }

        mDragHelper.smoothSlideViewTo(child, newLeft, (int) child.getY());
        invalidate();
    }

    private int clampContentViewHorizontal(@NonNull View child, int left) {
        final int leftPadding = getPaddingLeft();
        final int rightPadding = getPaddingRight();
        final int width = getWidth();

        int newLeft;
        if (left < leftPadding) {
            newLeft = leftPadding;
        } else if (left > (width - rightPadding)) {
            newLeft = width - rightPadding;
        } else {
            newLeft = left;
        }
        return newLeft;
    }

    private int clampMenuViewHorizontal(@NonNull View child, int left) {
        final int leftPadding = getPaddingLeft();
        final int rightPadding = getPaddingRight();
        final int childWidth = child.getWidth();
        final int width = getWidth();

        int newLeft;
        if (left < leftPadding) {
            newLeft = leftPadding;
        } else {
            if (left > (width - rightPadding - childWidth)) {
                newLeft = width - rightPadding - childWidth;
            } else {
                newLeft = left;
            }
        }
        return newLeft;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

//        restoreChildPosition();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

//        if (mDragViewID != NO_ID) {
//            mDragView = findViewById(mDragViewID);
//        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mDragHelper.processTouchEvent(ev);
        return true;
    }

    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

//    public void setDragViewId(@IdRes int id) {
//        mDragViewID = id;
//    }

//    public void restoreChildPosition() {
//        if (mDragViewLastX == -1 && mDragViewLastX == -1) { // 初始位置
//            mDragViewLastX = getMeasuredWidth() - mDragView.getMeasuredWidth();
//            mDragViewLastX = getMeasuredHeight() * 2 / 3;
//        }
//
//        mDragView.layout((int) mDragViewLastX, (int) mDragViewLastX, (int) mDragViewLastX + mDragView.getMeasuredWidth(), (int) mDragViewLastX + mDragView.getMeasuredHeight());
//    }

    public interface DragListener {
        void onDragStart(View view);
        void onDragOver(boolean isDragSuccess, View view);
    }

}