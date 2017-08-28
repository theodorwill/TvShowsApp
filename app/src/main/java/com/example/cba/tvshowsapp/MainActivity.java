package com.example.cba.tvshowsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import com.example.cba.tvshowsapp.adapter.DataAdapter;
import com.example.cba.tvshowsapp.model.BaseData;
import com.example.cba.tvshowsapp.model.Episode;
import com.example.cba.tvshowsapp.service.ApiInterface;
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

    private static final String searchUrl = "/singlesearch/shows?q=";
    private static final String embedUrl = "&embed=episodes";
    private String searchResult = "";
    private RecyclerView recyclerView;
    private EditText searchField;
    private TextView showName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        searchField = (EditText)findViewById(R.id.searchEdit);
        showName = (TextView)findViewById(R.id.showName);

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchResult = searchField.getText().toString();
                fetchSearchResults();
            }
        });
    }

    public void fetchSearchResults(){

        recyclerView = (RecyclerView)findViewById(R.id.showRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tvmaze.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<BaseData> call = apiInterface.getShowInfo(searchUrl+searchResult+embedUrl);
        call.enqueue(new Callback<BaseData>() {
            @Override
            public void onResponse(Call<BaseData> call, Response<BaseData> response) {
                if (response.isSuccessful()){
                List<Episode> episodes = response.body().getEmbedded().getEpisodes();
                recyclerView.setAdapter(new DataAdapter(episodes, R.layout.item_view, getApplicationContext()));
                    String nameOfShow = response.body().getName();
                    showName.setText(nameOfShow);
            } else {
                    if(searchResult.equals("")) {
                        recyclerView.setAdapter(null);
                        showName.setText("");
                    } else{
                        recyclerView.setAdapter(null);
                        showName.setText("No results found");
                    }
                }
            }
            @Override
            public void onFailure(Call<BaseData> call, Throwable t) {
            }
        });
    }
}
