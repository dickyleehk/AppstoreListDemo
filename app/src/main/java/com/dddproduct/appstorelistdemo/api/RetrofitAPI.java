package com.dddproduct.appstorelistdemo.api;

import com.dddproduct.appstorelistdemo.api.gson.AppDetail;
import com.dddproduct.appstorelistdemo.api.gson.AppList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dickyleehk on 11/2/18.
 */

public class RetrofitAPI {

    private static RetrofitAPI self;

    String API_DOMAIN = "https://itunes.apple.com/";

    private APIList apiInterface;

    private RetrofitAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_DOMAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(APIList.class);
    }

    public static RetrofitAPI getInstance(){
        if(self == null){
            self = new RetrofitAPI();
        }
        return self;
    }

    public void getTop100Free(Callback<AppList> callback){
        Call<AppList> appList = apiInterface.topFree();
        appList.enqueue(callback);
    }
    public void getTop10Grossing(Callback<AppList> callback){
        Call<AppList> appList = apiInterface.topGrossing();
        appList.enqueue(callback);
    }
    public void getAppDetail(String appId, Callback<AppDetail> callback){
        Call<AppDetail> appList = apiInterface.appDetail(appId);
        appList.enqueue(callback);
    }

}

