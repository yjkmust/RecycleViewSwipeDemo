package com.yaojie.swiperecyclerviewdemo;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

/**
 * Created by GEOFLY on 2017/5/25.
 */

public class MyAdapter extends SwipeMenuAdapter<MyAdapter.ViewHolder> {
    private List<bean> mData;
    private OnItemClickListener mOnItemClickListener;
    public MyAdapter(List<bean> data){
        mData = data;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public ViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        ViewHolder viewHolder = new ViewHolder(realContentView);
        viewHolder.mOnItemClickListener = mOnItemClickListener;
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0:mData.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        ImageView ivImage;
        OnItemClickListener mOnItemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivImage = (ImageView) itemView.findViewById(R.id.iv_image);
        }
        public void setData(bean data) {
            this.tvTitle.setText(data.getName());
            this.ivImage.setImageResource(data.getImageId());
        }
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
}
