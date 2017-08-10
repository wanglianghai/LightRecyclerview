package com.bignerdranch.android.lightrecyclerview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public class RecyclerViewActivity extends AppCompatActivity implements ShowRecyclerView {
    private RecyclerView recyclerView;
    private PresenterAdapter presenterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        presenterAdapter = new PresenterAdapter(this, this);
        presenterAdapter.showDate();
    }

    @Override
    public void showRecycler(HomeAdapter adapter) {
        recyclerView.setAdapter(adapter);
        vertical();
        //horizontal();
//        grid();
//        waterfall();
    }

    @Override
    public void showRecyclerDialog() {
        new AlertDialog.Builder(this)
                .setTitle("sure delete?")
                .setPositiveButton("sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenterAdapter.remove();
                    }
                })
                .setNegativeButton("cancel", null)
                .show();
    }

    private void waterfall() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        //     recyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        recyclerView.addItemDecoration(new GridItemDivider(this));
    }

    private void grid() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.addItemDecoration(new GridItemDivider(this));
    }

    private void horizontal() {
        recyclerView.addItemDecoration(new LinearItemDivider(this, LinearItemDivider.HORIZONTAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void vertical() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //android part
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
//        myVertical();
    }

    private void myVertical() {
        recyclerView.addItemDecoration(new LinearItemDivider(this, 1));
    }
}
