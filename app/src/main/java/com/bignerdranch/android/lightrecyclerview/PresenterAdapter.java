package com.bignerdranch.android.lightrecyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8/008.
 */

public class PresenterAdapter implements OnItemClickListener {
    private ShowRecyclerView mRecyclerView;
    private List<String> strings;
    private HomeAdapter mAdapter;
    private int position;
    public PresenterAdapter(ShowRecyclerView recyclerView, RecyclerViewActivity activity) {
        mRecyclerView = recyclerView;
        strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add(i + "");
        }
        mAdapter = new HomeAdapter(activity, strings);
        mAdapter.setItemClickListener(this);
    }

    public void showDate() {
        mRecyclerView.showRecycler(mAdapter);
    }

    @Override
    public void onItemClick(int position) {
        this.position = position;
        mRecyclerView.showRecyclerDialog();
    }

    public void remove() {
        mAdapter.removeData(position);
    }
}
