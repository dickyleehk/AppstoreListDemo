package com.dddproduct.appstorelistdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dddproduct.appstorelistdemo.api.RetrofitAPI;
import com.dddproduct.appstorelistdemo.api.gson.AppList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testCall();
            }
        });
    }

    private void testCall(){
        RetrofitAPI.getInstance().getAppList(new Callback<AppList>() {
            @Override
            public void onResponse(Call<AppList> call, Response<AppList> response) {
                Log.d("TEST", "RESPONSE");
            }

            @Override
            public void onFailure(Call<AppList> call, Throwable t) {
                Log.d("TEST", "FAIL");
            }
        });
    }
}
