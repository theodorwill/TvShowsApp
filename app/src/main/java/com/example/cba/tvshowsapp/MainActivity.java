package com.example.cba.tvshowsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cba.tvshowsapp.Adapter.DataAdapter;
import com.example.cba.tvshowsapp.Model.BaseData;
import com.example.cba.tvshowsapp.Model.Episode;
import com.example.cba.tvshowsapp.Service.ApiInterface;
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
    private Button confirm;
    private EditText searchField;
    private TextView showName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        confirm = (Button)findViewById(R.id.confirmButton);
        searchField = (EditText)findViewById(R.id.searchEdit);
        confirm = (Button)findViewById(R.id.confirmButton);
        showName = (TextView)findViewById(R.id.showName);

        confirm.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
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
                recyclerView.setAdapter(new DataAdapter(episodes, R.layout.card_view, getApplicationContext()));
                    String nameOfShow = response.body().getName();
                    showName.setText(nameOfShow);
            } else {
                    Toast.makeText(getApplicationContext(), "No result found for "+searchResult, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<BaseData> call, Throwable t) {
            }
        });
    }
}
