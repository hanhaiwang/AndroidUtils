package com.hanhaiwang.androidutil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Description: 通用的Adapter
 */
public abstract class CommonRecyclerAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    //数据怎么办？利用泛型
    protected List<T> mDatas;
    // 布局怎么办？直接从构造里面传递
    private int mLayoutId;

    public CommonRecyclerAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = datas;
        this.mLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 先inflate数据
        View itemView = mInflater.inflate(mLayoutId, parent, false);
        // 返回ViewHolder
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 绑定怎么办？回传出去
        convert(holder, mDatas.get(position));
    }

    /**
     * 利用抽象方法回传出去，每个不一样的Adapter去设置
     * @param item 当前的数据
     */
    public abstract void convert(ViewHolder holder, T item);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
