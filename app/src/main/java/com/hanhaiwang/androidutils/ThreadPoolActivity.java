package com.hanhaiwang.androidutils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.hanhaiwang.androidutil.utils.ThreadPoolUtil;

public class ThreadPoolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    /**
     * 线程池使用方法
     */
    private void testPool() {
        ThreadPoolUtil.post(new Runnable() {
            @Override
            public void run() {
                /**
                 * 这里写你需要执行的操作
                 */
            }
        });
    }
}
