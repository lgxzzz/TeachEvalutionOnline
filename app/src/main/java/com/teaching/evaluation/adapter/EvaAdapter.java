package com.teaching.evaluation.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.teaching.evaluation.R;
import com.teaching.evaluation.bean.Evaluation;

import java.util.ArrayList;
import java.util.List;

public class EvaAdapter extends BaseAdapter {
    private Context mContext;
    private List<Evaluation> mData = new ArrayList<>();

    public EvaAdapter(Context mContext, List<Evaluation>data){
        this.mContext = mContext;
        this.mData = data;
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
        Evaluation data = this.mData.get(i);
        ViewHoler holer = null;
        if (view == null){
            holer = new ViewHoler();
            view = LayoutInflater.from(mContext).inflate(R.layout.evaluation_list_item,null);
            holer.mName = (TextView) view.findViewById(R.id.eva_name);
            holer.mTime = (TextView) view.findViewById(R.id.eva_time);
            holer.mContent = (TextView) view.findViewById(R.id.eva_content);
            view.setTag(holer);
        }else{
            holer = (ViewHoler) view.getTag();
        }

        holer.mName.setText("用户"+data.getUser_number());
        holer.mTime.setText(data.getCourse_time());
        holer.mContent.setText(data.getContent());
        return view;
    }

    class ViewHoler{
        TextView mName;
        TextView mTime;
        TextView mContent;
    }


}
