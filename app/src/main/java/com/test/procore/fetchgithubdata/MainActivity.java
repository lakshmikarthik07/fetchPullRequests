package com.test.procore.fetchgithubdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewDebug;

import com.test.procore.fetchgithubdata.ServiceInterface.IApiService;
import com.test.procore.fetchgithubdata.adapter.PRListAdapter;
import com.test.procore.fetchgithubdata.utils.JsonPojoClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.test.procore.fetchgithubdata.ServiceInterface.IApiService.GITHUB_BASE_URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter prListAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.my_recycler_view);

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IApiService IApiService = retrofit.create(IApiService.class);

        Call<List<JsonPojoClass>> call = IApiService.getPullRequestList();
        call.enqueue(new Callback<List<JsonPojoClass>>() {
            @Override
            public void onResponse(Call<List<JsonPojoClass>> call, Response<List<JsonPojoClass>> response) {
                List<JsonPojoClass> lists = response.body();
                setRecyclerView(lists);
            }
            @Override
            public void onFailure(Call<List<JsonPojoClass>> call, Throwable t){

            }
        });
    }

    private void setRecyclerView(List<JsonPojoClass> prList) {

        prListAdapter = new PRListAdapter(prList,this);
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(itemDecorator);
        recyclerView.setAdapter(prListAdapter);
    }

}
