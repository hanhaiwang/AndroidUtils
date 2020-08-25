package com.hanhaiwang.androidutil.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.core.widget.NestedScrollView;

/**
 * 添加滑动回调
 */
public class CustomNestedScrollView extends NestedScrollView {

    public Callbacks mCallbacks;

    public CustomNestedScrollView(Context context) {
        super(context);
    }

    public CustomNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //定义接口用于回调
    public interface Callbacks {
        /**
         * @param x    Current horizontal scroll origin. 当前滑动的x轴距离
         * @param y    Current vertical scroll origin. 当前滑动的y轴距离  像上滑t增大，向下滑t减小
         * @param oldx Previous horizontal scroll origin. 上一次滑动的x轴距
         * @param oldy Previous vertical scroll origin. 上一次滑动的y轴距离
         */
        void onScrollChanged(int x, int y, int oldx, int oldy);
    }

    public void setCallbacks(Callbacks callbacks) {
        this.mCallbacks = callbacks;
    }

    /**
     * @param l    Current horizontal scroll origin. 当前滑动的x轴距离
     * @param t    Current vertical scroll origin. 当前滑动的y轴距离  像上滑t增大，向下滑t减小
     * @param oldl Previous horizontal scroll origin. 上一次滑动的x轴距
     * @param oldt Previous vertical scroll origin. 上一次滑动的y轴距离
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mCallbacks != null) {
            mCallbacks.onScrollChanged(l, t, oldl, oldt);
        }
    }

    //本来是在Activity中调用的，现我的想法，是想在这里直接实现，在Activity中调用就会简单很多了
  /*  public class MyCallbacks implements Callbacks{
        @Override
        public void onScrollChanged(int x, int y, int oldx, int oldy) {
            //向上滑和向下滑在这个范围之内都需要改变透明度
            float alpha = 0f;
            if (y > 0 && y < mHeight) {
                float scale = (float) y / mHeight;//算出滑动距离比
                alpha = (255 * scale);//得到透明度
                ll_common_title.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
            }
            if (y >= mHeight && alpha <= 255) {
                //矫正计算的误差,防止滑动过快，在滑动距离大于mHeight后，透明度百分比没有达到100%
                ll_common_title.setBackgroundColor(Color.argb(255, 255, 255, 255));
            }


            if (oldy - y < 0) {
                //向上滑
                if (y >= (3 * mHeight) / 6) {//这个距离是实验出来的大概的距离,可自行调整
                    //向上滑的距离大于次距离是改变标题栏图标颜色和字体颜色，改变状态栏图标颜色
                    iv_return.setImageResource(R.mipmap.return_icon);
                    tv_title.setTextColor(TranStatusActivity.this.getResources().getColor(R.color.black));
                    QMUIStatusBarHelper.setStatusBarLightMode(TranStatusActivity.this);//深色字体
                }
            } else {
                //向下滑
                if (y <= (3 * mHeight) / 6) {
                    //向下滑的距离小 于次距离是改变标题栏图标颜色和字体颜色，改变状态栏图标颜色
                    iv_return.setImageResource(R.mipmap.return_icon_1);
                    tv_title.setTextColor(TranStatusActivity.this.getResources().getColor(R.color.white));
                    QMUIStatusBarHelper.setStatusBarDarkMode(TranStatusActivity.this);//深色字体
                }
                if (y <= 0) {
                    //标题栏处于最顶部
                    ll_common_title.setBackgroundColor(Color.argb(0, 255, 255, 255));
                }
            }
        }
    }*/
}
