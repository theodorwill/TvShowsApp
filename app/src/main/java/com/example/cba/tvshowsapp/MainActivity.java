package com.example.cba.tvshowsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.cba.tvshowsapp.Adapter.DataAdapter;
import com.example.cba.tvshowsapp.Model.BaseData;
import com.example.cba.tvshowsapp.Model.Episode;
import com.example.cba.tvshowsapp.Service.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by cba on 2017-06-13.
 */

public class MainActivity extends AppCompatActivity {

    public static final String searchUrl = "/singlesearch/shows?q=";
    public static final String embedUrl = "&embed=episodes";
    public String searchResult = "";
    private RecyclerView recyclerView;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        searchResult = "game of thrones";

        fetchSearchResults();


    }


    public void fetchSearchResults(){

        recyclerView = (RecyclerView)findViewById(R.id.showRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        //Retrofit Instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<BaseData> call = apiInterface.getShowInfo(searchUrl+searchResult+embedUrl);
        call.enqueue(new Callback<BaseData>() {
            @Override
            public void onResponse(Call<BaseData> call, Response<BaseData> response) {
                int statusCode = response.code();
                List<Episode> episodes = response.body().getEmbedded().getEpisodes();
                recyclerView.setAdapter(new DataAdapter(episodes, R.layout.card_view, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<BaseData> call, Throwable t) {

            }
        });
    }



}
