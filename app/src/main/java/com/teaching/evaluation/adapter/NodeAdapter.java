package com.teaching.evaluation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teaching.evaluation.R;

import java.util.ArrayList;
import java.util.List;

public class NodeAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData = new ArrayList<>();
    private int mSelectIndex = -1;

    public NodeAdapter(Context mContext,List<String>data){
        this.mContext = mContext;
        this.mData = data;
    }

    public int getSelectIndex() {
        return mSelectIndex;
    }

    public void setSelectIndex(int mSelectIndex) {
        this.mSelectIndex = mSelectIndex;
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public Object getItem(int i) {
        return this.mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String data = this.mData.get(i);
        ViewHoler holer = null;
        if (view == null){
            holer = new ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.tree_node,null);
            holer.mText = (TextView) view.findViewById(R.id.node_name);
            view.setTag(holer);
        }else{
            holer = (ViewHoler) view.getTag();
        }
        if (mSelectIndex == i){
            holer.mText.setTextColor(Color.BLUE);
        }else{
            holer.mText.setTextColor(Color.BLACK);
        }
        holer.mText.setText(data);
        return view;
    }

    class ViewHoler{
        TextView mText;
    }


}
