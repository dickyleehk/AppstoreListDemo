package com.dddproduct.appstorelistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.dddproduct.appstorelistdemo.adapter.MainAdapter;
import com.dddproduct.appstorelistdemo.api.RetrofitAPI;
import com.dddproduct.appstorelistdemo.api.gson.AppList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    MainAdapter mainAdapter;
    RecyclerView rcvMain;

    List<Object> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testCallA();
                testCallB();
            }
        });
    }

    private void init(){
        mainAdapter = new MainAdapter(this);

        rcvMain = (RecyclerView) findViewById(R.id.rcv_main);
        rcvMain.setLayoutManager(new LinearLayoutManager(this));
        rcvMain.setAdapter(mainAdapter);

        list = new ArrayList<>();
        list.add(new Object());
        mainAdapter.setList(list);
    }

    private void testCallA(){
        RetrofitAPI.getInstance().getTop10Grossing(new Callback<AppList>() {
            @Override
            public void onResponse(Call<AppList> call, Response<AppList> response) {
                if(response.isSuccessful() &&
                        response.body() != null &&
                        response.body().getFeed() != null ){
                    list.remove(0);
                    list.add(0, response.body().getFeed().getEntry());
                    mainAdapter.setList(list);
                }else{
                    onFailure(call, new Throwable("API return fail."));
                }
            }

            @Override
            public void onFailure(Call<AppList> call, Throwable t) {
                Log.d("TEST", "FAIL");
            }
        });
    }

    private void testCallB(){
        RetrofitAPI.getInstance().getTop100Free(new Callback<AppList>() {
            @Override
            public void onResponse(Call<AppList> call, Response<AppList> response) {
                if(response.isSuccessful() &&
                        response.body() != null &&
                        response.body().getFeed() != null ){
                    while (list.size() > 1){
                        list.remove(1);
                    }
                    list.addAll(response.body().getFeed().getEntry());
                    mainAdapter.setList(list);
                }else{
                    onFailure(call, new Throwable("API return fail."));
                }
            }

            @Override
            public void onFailure(Call<AppList> call, Throwable t) {
                Log.d("TEST", "FAIL");
            }
        });
    }


}
