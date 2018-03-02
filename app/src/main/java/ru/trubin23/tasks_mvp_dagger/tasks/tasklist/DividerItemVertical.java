package ru.trubin23.tasks_mvp_dagger.tasks.tasklist;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Andrey on 02.03.2018.
 */

public class DividerItemVertical extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS =new int[]{android.R.attr.listDivider};

    private Drawable mDivider;

    public DividerItemVertical(Context context) {
        TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        mDivider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
    }

    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();

        for (int i=0;i<childCount;i++){
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();

            int left = child.getPaddingLeft();
            int right = parent.getWidth() - child.getPaddingRight();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
    }
}
