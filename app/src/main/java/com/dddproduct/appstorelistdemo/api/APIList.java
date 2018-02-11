package com.dddproduct.appstorelistdemo.api;

import com.dddproduct.appstorelistdemo.api.gson.AppList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dickyleehk on 11/2/18.
 */

public interface APIList {
    @GET("/hk/rss/topfreeapplications/limit=100/json")
    Call<AppList> appList();
}
