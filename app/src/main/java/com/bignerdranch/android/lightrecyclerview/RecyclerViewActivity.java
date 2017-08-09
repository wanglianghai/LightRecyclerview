package com.bignerdranch.android.lightrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements ShowRecyclerView {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        Presenter presenter = new Presenter(this);
        presenter.showDate();
    }

    @Override
    public void showRecycler(List<?> list) {
        recyclerView.setAdapter(new HomeAdapter(this, list));
        //vertical();
        recyclerView.addItemDecoration(new LinearItemDivider(this, LinearItemDivider.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void vertical() {
        recyclerView.addItemDecoration(new LinearItemDivider(this, 1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
