package com.hanhaiwang.androidutil.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 解决GridView只显示一行的问题
 */
public class MultipleRowsGridView extends GridView {

    public MultipleRowsGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultipleRowsGridView(Context context) {
        super(context);
    }

    public MultipleRowsGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 解决GridView只显示一行的问题
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);

        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
