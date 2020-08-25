package com.hanhaiwang.androidutils;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.hanhaiwang.androidutil.adapter.RecyclerHolder;
import com.hanhaiwang.androidutil.adapter.CommonRecyclerAdapter;

import java.util.List;

/**
 * 自定义自已的适配器并继承CommonRecyclerAdapter<T>
 */
public class MyAdapter extends CommonRecyclerAdapter<DataBean> {

    public MyAdapter(Context context, int itemLayoutId, List<DataBean> list) {
        super(context, itemLayoutId, list);
    }

    @Override
    public void convert(RecyclerHolder holder, DataBean item, int position) {
        //标题
        String title = item.getTitle();
        holder.setText(R.id.tv, TextUtils.isEmpty(title) ? "-" : title);//三木判断下返回是否为空
        //图片地址
        String url = item.getUrl();
        holder.setImageByUrl(R.id.iv, url);
    }
}
