package com.hanhaiwang.androidutils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hanhaiwang.androidutil.widget.CountNumberView;

public class CountNumberActivity extends AppCompatActivity {

    private CountNumberView mTvCountNum1;
    private CountNumberView mTvCountNum2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countnumber);

        loadCountNumber();
    }

    /**
     * 数字动画的使用方法
     */
    private void loadCountNumber() {
        mTvCountNum1 = findViewById(R.id.mTvCountNum1);
        mTvCountNum2 = findViewById(R.id.mTvCountNum2);

        mTvCountNum1.showNumberWithAnimation(3201.23f, CountNumberView.FLOATREGEX);
        mTvCountNum2.showNumberWithAnimation(65535f, CountNumberView.INTREGEX);
    }
}
