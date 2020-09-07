package com.hanhaiwang.androidutil.widget.shopbutton;

/**
 * 加减View的接口
 */

public interface IOnAddDelListener {
    enum FailType {
        COUNT_MAX, COUNT_MIN
    }

    void onAddSuccess(int count);

    void onAddFailed(int count, FailType failType);

    void onDelSuccess(int count);

    void onDelFaild(int count, FailType failType);
}
