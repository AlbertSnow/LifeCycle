package com.sogou.babel.testfragment.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sogou.babel.testfragment.R;

import java.util.List;

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHolder> {
    private List<String> mDatas;

    FeedbackAdapter(List<String> datas) {
        mDatas = datas;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_feedback_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new MyViewHolder(textView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTextView.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        MyViewHolder(TextView itemView) {
            super(itemView);
            mTextView = itemView;
        }
    }

}
