package com.example.hp.home.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 10-05-2016.
 */


    public class Example{

        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("results")
        @Expose
        private List<MovieModel> results = new ArrayList<MovieModel>();
        @SerializedName("total_results")
        @Expose
        private Integer totalResults;
        @SerializedName("total_pages")
        @Expose
        private Integer totalPages;

        /**
         *
         * @return
         * The page
         */
        public Integer getPage() {
            return page;
        }

        /**
         *
         * @param page
         * The page
         */
        public void setPage(Integer page) {
            this.page = page;
        }

        /**
         *
         * @return
         * The results
         */
        public List<MovieModel> getResults() {
            return results;
        }

        /**
         *
         * @param results
         * The results
         */
        public void setResults(List<MovieModel> results) {
            this.results = results;
        }

        /**
         *
         * @return
         * The totalResults
         */
        public Integer getTotalResults() {
            return totalResults;
        }

        /**
         *
         * @param totalResults
         * The total_results
         */
        public void setTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
        }

        /**
         *
         * @return
         * The totalPages
         */
        public Integer getTotalPages() {
            return totalPages;
        }

        /**
         *
         * @param totalPages
         * The total_pages
         */
        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }



}
