package com.example.cba.tvshowsapp.Adapter;

/**
 * Created by cba on 2017-06-17.
 */

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cba.tvshowsapp.Model.Episode;
import com.example.cba.tvshowsapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private List<Episode> episodes;
    private int rowLayout;
    private Context context;


    public static class DataViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView name;
        TextView number;
        TextView season;


        public DataViewHolder(View v) {
            super(v);
            moviesLayout = (LinearLayout) v.findViewById(R.id.movies_layout);
            name = (TextView) v.findViewById(R.id.name);
            number = (TextView) v.findViewById(R.id.number);
            season = (TextView) v.findViewById(R.id.season);

        }
    }

    public DataAdapter(List<Episode> episodes, int rowLayout, Context context) {
        this.episodes = episodes;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public DataAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new DataViewHolder(view);
    }


    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        holder.name.setText(episodes.get(position).getName());
        holder.number.setText("episode: "+Integer.toString(episodes.get(position).getNumber()));
        holder.season.setText("Season: "+Integer.toString(episodes.get(position).getSeason()));
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }
}
