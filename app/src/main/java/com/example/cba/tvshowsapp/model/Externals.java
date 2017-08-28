package com.example.cba.tvshowsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by cba on 2017-06-15.
 */

    public class Externals {

        @SerializedName("tvrage")
        @Expose
        private Integer tvrage;
        @SerializedName("thetvdb")
        @Expose
        private Integer thetvdb;
        @SerializedName("imdb")
        @Expose
        private String imdb;

        public Integer getTvrage() {
            return tvrage;
        }

        public void setTvrage(Integer tvrage) {
            this.tvrage = tvrage;
        }

        public Integer getThetvdb() {
            return thetvdb;
        }

        public void setThetvdb(Integer thetvdb) {
            this.thetvdb = thetvdb;
        }

        public String getImdb() {
            return imdb;
        }

        public void setImdb(String imdb) {
            this.imdb = imdb;
        }

    }

