package com.example.avi.sangeetha;

/**
 * Created by Avi on 3/1/2016.
 */

import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<news_data> mDataset;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView description ;
        public ViewHolder(View v) {
            super(v);
            description = (TextView)v.findViewById(R.id.description);
        }
    }

    public RecyclerAdapter(Context context) {
        this.context = context;
        mDataset = News_singleton.getInstance().data;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final news_data currentdata = mDataset.get(position);
        holder.description.setText(currentdata.getInfo());
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
