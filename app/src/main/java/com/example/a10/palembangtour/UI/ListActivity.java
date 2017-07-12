package com.example.a10.palembangtour.UI;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.a10.palembangtour.Controller.RestManager;
import com.example.a10.palembangtour.Models.Adapter.ListAdapter;
import com.example.a10.palembangtour.Models.Result;
import com.example.a10.palembangtour.Models.Value;
import com.example.a10.palembangtour.Models.helper.DatabaseSqlite;
import com.example.a10.palembangtour.Models.helper.Utils;
import com.example.a10.palembangtour.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    private static final String TAGS = ListActivity.class.getSimpleName();

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private ListAdapter adapterViewList;
    private List<Result> listData = new ArrayList<>();
    private RestManager restManager;
    private SwipeRefreshLayout swipeRefreshList;
    private ProgressBar progressData;
    private String titleList;
    private Call<Value> call;
    private DatabaseSqlite mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        titleList = getIntent().getStringExtra("title");

        toolbar = (Toolbar) findViewById(R.id.toolbarList);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(titleList);

        configList();

        restManager = new RestManager();
        mDatabase = new DatabaseSqlite(this);

        if (Utils.isNetworkAvailable(getApplicationContext())) {
            loadDataList();
        } else {
            loadDataListDatabase();
        }
    }

    private void loadDataListDatabase() {

        listData = mDatabase.getDataList();

        for (int i = 0; i < listData.size(); i++) {
            Result result = listData.get(i);
            Log.d(TAGS, result.getNama() + "||");
        }
    }

    private void configList() {
        swipeRefreshList = (SwipeRefreshLayout) findViewById(R.id.swipe_list);
        progressData = (ProgressBar) findViewById(R.id.progres_data);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        adapterViewList = new ListAdapter(ListActivity.this, listData);
        recyclerView.setAdapter(adapterViewList);

        progressData.post(new Runnable() {
            @Override
            public void run() {
                progressData.setVisibility(View.VISIBLE);
            }
        });

        swipeRefreshList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    loadDataList();
                } else {
                    loadDataListDatabase();
                }

            }
        });

    }

    private void loadDataList() {

        Log.d("Res Call :", titleList.toString());

        switch (titleList) {
            case "Wisata Alam":
                call = restManager.getmRestService().getViewAlam();
                break;
            case "Wisata Sejarah dan Budaya":
                call = restManager.getmRestService().getViewSjrBudaya();
                break;
            case "Wisata Buatan Manusia":
                call = restManager.getmRestService().getViewBuatan();
                break;
            case "Wisata Makam Kesultanan":
                call = restManager.getmRestService().getViewMakam();
                break;
            case "Wisata Kuliner":
                call = restManager.getmRestService().getViewKuliner();
                break;
        }
        Log.d("Res Call :", call.toString());

        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {

                String value = response.body().getValue();

                if (value.equals("1")) {

                    listData = response.body().getResult();
                    Log.d("Res List :", listData.toString());
                    for (int i = 0; i < listData.size(); i++) {
                        Result result = listData.get(i);

                        SaveIntoDatabase task = new SaveIntoDatabase();
                        task.execute(result);

                        adapterViewList = new ListAdapter(ListActivity.this, listData);
                        recyclerView.setAdapter(adapterViewList);
                        progressData.setVisibility(View.GONE);
                        swipeRefreshList.setRefreshing(false);
                    }

                } else {
                    int sc = response.code();
                    switch (sc) {

                    }
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                Toast.makeText(ListActivity.this, "Please check your Signal", Toast.LENGTH_SHORT).show();
                progressData.setVisibility(View.GONE);
                swipeRefreshList.setRefreshing(false);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activitylist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
            case R.id.action_refresh:
                if (Utils.isNetworkAvailable(getApplicationContext())) {
                    loadDataList();
                } else {
                    loadDataListDatabase();
                }
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }


    public class SaveIntoDatabase extends AsyncTask<Result, Result, Boolean> {

        private final String TAGS = SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Result... params) {

            Result result = params[0];

            try {
                InputStream stream = new URL(result.getImage()).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                result.setPicture(bitmap);
                publishProgress(result);
            } catch (Exception e) {
                Log.d(TAGS, e.getMessage());
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Result... values) {
            super.onProgressUpdate(values);
            mDatabase.addData(values[0]);
            Log.d(TAGS, "Value Got " + values[0].getNama());
        }
    }
}
