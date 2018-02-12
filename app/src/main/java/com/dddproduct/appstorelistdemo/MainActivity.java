package com.dddproduct.appstorelistdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

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
    EditText etvSearch;

    List<Object> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        testCallA();
        testCallB();
    }

    private void init(){
        rcvMain = (RecyclerView) findViewById(R.id.rcv_main);
        etvSearch = (EditText) findViewById(R.id.etv_search);
        etvSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mainAdapter.setKeyword(s.toString());
            }
        });

        mainAdapter = new MainAdapter(this);

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
