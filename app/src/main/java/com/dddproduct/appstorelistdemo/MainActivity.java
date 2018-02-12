package com.dddproduct.appstorelistdemo;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.dddproduct.appstorelistdemo.adapter.MainAdapter;
import com.dddproduct.appstorelistdemo.api.RetrofitAPI;
import com.dddproduct.appstorelistdemo.api.gson.AppList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainAdapter.ReachEndListener {

    static final int LOAD_MORE_NUMBER = 10;

    MainAdapter mainAdapter;
    List<Object> apiResult, list;

    SwipeRefreshLayout srlMain;
    RecyclerView rcvMain;
    EditText etvSearch;


    int waitingList = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        apiCall();
    }

    private void init(){
        srlMain = (SwipeRefreshLayout) findViewById(R.id.srl_main);
        rcvMain = (RecyclerView) findViewById(R.id.rcv_main);
        etvSearch = (EditText) findViewById(R.id.etv_search);

        mainAdapter = new MainAdapter(this);
        mainAdapter.setListener(this);

        apiResult = new ArrayList<>();
        apiResult.add(new Object());

        rcvMain.setLayoutManager(new LinearLayoutManager(this));
        rcvMain.setAdapter(mainAdapter);

        srlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiCall();
            }
        });

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
    }

    private void apiCall() {
        srlMain.setRefreshing(true);
        testCallA();
        testCallB();
    }

    private void testCallA(){
        waitingList += 1;

        RetrofitAPI.getInstance().getTop10Grossing(new Callback<AppList>() {
            @Override
            public void onResponse(Call<AppList> call, Response<AppList> response) {
                if(response.isSuccessful() &&
                        response.body() != null &&
                        response.body().getFeed() != null ){
                    apiResult.remove(0);
                    apiResult.add(0, response.body().getFeed().getEntry());
                    apiFinish();
                }else{
                    onFailure(call, new Throwable("API return fail."));
                }
            }

            @Override
            public void onFailure(Call<AppList> call, Throwable t) {
                apiFail();
            }
        });
    }

    private void testCallB(){
        waitingList += 1;

        RetrofitAPI.getInstance().getTop100Free(new Callback<AppList>() {
            @Override
            public void onResponse(Call<AppList> call, Response<AppList> response) {
                if(response.isSuccessful() &&
                        response.body() != null &&
                        response.body().getFeed() != null ){
                    while (apiResult.size() > 1){
                        apiResult.remove(1);
                    }
                    apiResult.addAll(response.body().getFeed().getEntry());
                    apiFinish();
                }else{
                    onFailure(call, new Throwable("API return fail."));
                }
            }

            @Override
            public void onFailure(Call<AppList> call, Throwable t) {
                apiFail();
            }
        });
    }

    private void apiFinish(){
        waitingList -= 1;
        if(waitingList <= 0){

            list = new ArrayList<>();
            onReachEnd(0);

            srlMain.setRefreshing(false);
            mainAdapter.setList(list);
        }
    }

    private void apiFail(){
        Toast.makeText(MainActivity.this, "API Call failed, please try again later", Toast.LENGTH_SHORT).show();
        apiFinish();
    }

    @Override
    public void onReachEnd(final int lastIndex) {
        if(list.size() >= apiResult.size()){
            Toast.makeText(MainActivity.this, "End of top 100 free", Toast.LENGTH_SHORT).show();
            return;
        }

        if(lastIndex != 0) //Do not show in first result
            Toast.makeText(MainActivity.this, "Loading more...", Toast.LENGTH_SHORT).show();

        srlMain.setRefreshing(true);
        srlMain.postDelayed(new Runnable() {
            @Override
            public void run() {
                srlMain.setRefreshing(false);

                int min = Math.min(LOAD_MORE_NUMBER, apiResult.size() - list.size());
                if(lastIndex == 0)min += 1; //fix for first item is Grossing list

                List updateList = apiResult.subList(lastIndex, lastIndex+min);
                list.addAll(updateList);
                mainAdapter.addToList(updateList);
            }
        }, lastIndex == 0? 0 : 2000); //instant show for first 10 data, delay 2sec for fake loading
    }
}
