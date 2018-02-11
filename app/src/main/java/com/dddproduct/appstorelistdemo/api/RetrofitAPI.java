package com.dddproduct.appstorelistdemo.api;

import com.dddproduct.appstorelistdemo.api.gson.AppList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dickyleehk on 11/2/18.
 */

public class RetrofitAPI {

    static RetrofitAPI self;

    String API_DOMAIN = "https://itunes.apple.com/";

    Retrofit retrofit;
    APIList apiInterface;

    private RetrofitAPI(){
        retrofit= new Retrofit.Builder()
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

    public void getAppList(Callback<AppList> callback){
        Call<AppList> applist = apiInterface.appList();
        applist.enqueue(callback);
    }

}

