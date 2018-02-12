package com.dddproduct.appstorelistdemo.api;

import com.dddproduct.appstorelistdemo.api.gson.AppDetail;
import com.dddproduct.appstorelistdemo.api.gson.AppList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by dickyleehk on 11/2/18.
 */

public interface APIList {
    @GET("/hk/rss/topfreeapplications/limit=100/json")
    Call<AppList> topFree();

    @GET("/hk/lookup?")
    Call<AppDetail> appDetail(@Query("id") String appId);

    @GET("/hk/rss/topgrossingapplications/limit=10/json")
    Call<AppList> topGrossing();
}
