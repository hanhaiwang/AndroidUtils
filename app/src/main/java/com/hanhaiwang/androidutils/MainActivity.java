package com.hanhaiwang.androidutils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hanhaiwang.androidutil.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<DataBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadRecyclerView();


    }

    /**
     * 万能适配器的调用方法
     */
    private void loadRecyclerView() {
        recyclerView = (RecyclerView) this.findViewById(R.id.recyclerview);
        if (list == null) {
            list = new ArrayList<>();
        }
        //模拟10条假数据
        for (int i = 0; i < 50; i++) {
            list.add(new DataBean("android", "http://inews.gtimg.com/newsapp_bt/0/876781763/1000"));
        }
        MyAdapter adapter = new MyAdapter(this,R.layout.item_recyclerview,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(MainActivity.this,"点击事件"+position,Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnItemLongClickListener(new RecyclerAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(RecyclerView parent, View view, int position) {
                Toast.makeText(MainActivity.this,"长按事件"+position,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
