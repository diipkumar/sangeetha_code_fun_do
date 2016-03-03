package com.example.avi.sangeetha;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.avi.sangeetha.Utility.BaseActivity;

public class MainActivity extends BaseActivity {

    public  RecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRecycler();
        SyncData();
    }

    private void SyncData() {
        Loads_news data = new Loads_news(this,"karnataka");
        data.execute((Void) null);
    }

    private void setRecycler() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        setRecyclerViewLayoutManager(mRecyclerView);
        mAdapter = new RecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setRecyclerViewLayoutManager(RecyclerView recyclerView) {
        int scrollPosition = 0;
        if (recyclerView.getLayoutManager() != null) {
            scrollPosition =((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.scrollToPosition(scrollPosition);
    }

}


