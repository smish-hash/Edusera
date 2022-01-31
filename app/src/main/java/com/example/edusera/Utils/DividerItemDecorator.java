package com.example.edusera.Utils;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DividerItemDecorator extends RecyclerView.ItemDecoration {
    private Drawable mDrawable;

    public DividerItemDecorator(Drawable mDrawable) {
        this.mDrawable = mDrawable;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int divLeft = parent.getPaddingLeft();
        int divRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int divTop = child.getBottom() + params.bottomMargin;
            int divBottom = divTop + mDrawable.getIntrinsicHeight();

            mDrawable.setBounds(divLeft, divTop, divRight, divBottom);
            mDrawable.draw(c);
        }
    }
}
