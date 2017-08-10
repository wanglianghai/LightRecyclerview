package com.bignerdranch.android.lightrecyclerview;

import android.content.Context;
import android.support.v7.widget.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8/008.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<?> mList;
    private List<Integer> mHeight;
    private OnItemClickListener mClickListener;

    public HomeAdapter(Context context, List<?> list) {
        mContext = context;
        mList = list;
        mHeight = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            mHeight.add((int) (100 + Math.random() * 300));
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeHolder homeHolder = (HomeHolder) holder;
        homeHolder.bind(position + "", position);
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private class HomeHolder extends RecyclerView.ViewHolder{
        private TextView mTextView;

        private HomeHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_item);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.onItemClick(getAdapterPosition());
                }
            });
            /*if (getAdapterPosition() >= getItemCount() || getAdapterPosition() < 0) {
                return;
            }
            FrameLayout.LayoutParams p = (FrameLayout.LayoutParams) mTextView.getLayoutParams();
            p.height = mHeight.get(getAdapterPosition());
            mTextView.setLayoutParams(p);*/
        }

        public void bind(String s, int position) {
            mTextView.setText(s);
            FrameLayout.LayoutParams p = (FrameLayout.LayoutParams) mTextView.getLayoutParams();
            p.height = mHeight.get(position);
            mTextView.setLayoutParams(p);
        }
    }
}
