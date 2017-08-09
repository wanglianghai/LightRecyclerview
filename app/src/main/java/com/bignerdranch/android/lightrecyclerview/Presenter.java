package com.bignerdranch.android.lightrecyclerview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/8/008.
 */

public class Presenter {
    private ShowRecyclerView mRecyclerView;
    public Presenter(ShowRecyclerView recyclerView) {
        mRecyclerView = recyclerView;
    }

    public void showDate() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add(i + "");
        }
        mRecyclerView.showRecycler(strings);
    }
}
