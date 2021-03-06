package com.example.tarena.renter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by tarena on 2017/6/20.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<T> datas;
    public MyBaseAdapter(Context context, List<T> datas){
        this.context=context;
        this.datas=datas;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 追加一个新数据
     * @param t
     */
    public void add(T t){
        datas.add(t);
        notifyDataSetChanged();
    }

    /**
     * 追加或替换数据
     * @param list
     * @param isClear
     */
    public void addAll(List<T> list,boolean isClear){
        if (isClear){
            datas.clear();
        }
        datas.addAll(list);
        notifyDataSetChanged();
    }
    public void removeAll(){
        datas.clear();
        notifyDataSetChanged();
    }

}
