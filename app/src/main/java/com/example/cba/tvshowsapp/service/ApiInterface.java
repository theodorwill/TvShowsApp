package com.example.cba.tvshowsapp.service;

import com.example.cba.tvshowsapp.model.BaseData;
import com.example.cba.tvshowsapp.model.Embedded;
import com.example.cba.tvshowsapp.model.Episode;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by cba on 2017-06-13.
 */

public interface ApiInterface {
    @GET
    Call <BaseData> getShowInfo(@Url String url);

    //Test 1
    @GET
    Call <Embedded> getEmbeddedData(@Url String url);

    //Test 2
    @GET
    Call <Episode> getEpisodeData(@Url String url);

}
