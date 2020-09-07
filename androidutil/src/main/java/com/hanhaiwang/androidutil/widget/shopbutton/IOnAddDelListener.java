package com.hanhaiwang.androidutil.widget.shopbutton;

public interface IOnAddDelListener {
    void onAddSuccess(int var1);

    void onAddFailed(int var1, IOnAddDelListener.FailType var2);

    void onDelSuccess(int var1);

    void onDelFaild(int var1, IOnAddDelListener.FailType var2);

    public static enum FailType {
        COUNT_MAX,
        COUNT_MIN;

        private FailType() {
        }
    }
}
