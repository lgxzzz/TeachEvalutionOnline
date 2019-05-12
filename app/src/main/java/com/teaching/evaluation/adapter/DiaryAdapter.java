package com.teaching.evaluation.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import com.teaching.evaluation.R;
import com.teaching.evaluation.bean.Diary;

import java.util.List;


public class DiaryAdapter extends BaseRecyclerViewAdapter<Diary> {

    private OnDeleteClickLister mDeleteClickListener;

    public DiaryAdapter(Context context, List<Diary> data) {
        super(context, data, R.layout.item_diary);
    }

    @Override
    protected void onBindData(RecyclerViewHolder holder, Diary bean, int position) {
        View view = holder.getView(R.id.diary_delete);
        view.setTag(position);
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDeleteClickListener != null) {
                        mDeleteClickListener.onDeleteClick(v, (Integer) v.getTag());
                    }
                }
            });
        }
        ((TextView) holder.getView(R.id.diary_time)).setText(bean.getTime());
        ((TextView) holder.getView(R.id.diary_title)).setText(bean.getTitle());
        ((TextView) holder.getView(R.id.diary_content)).setText(bean.getContent());
    }

    public void setOnDeleteClickListener(OnDeleteClickLister listener) {
        this.mDeleteClickListener = listener;
    }

    public interface OnDeleteClickLister {
        void onDeleteClick(View view, int position);
    }
}