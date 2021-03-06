package com.test.procore.fetchgithubdata.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.test.procore.fetchgithubdata.R;
import com.test.procore.fetchgithubdata.serviceinterface.IApiPRListService;
import com.test.procore.fetchgithubdata.adapter.PRListAdapter;
import com.test.procore.fetchgithubdata.utils.JsonPojoClass;
import com.test.procore.fetchgithubdata.utils.SpinnerUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.test.procore.fetchgithubdata.serviceinterface.IApiPRListService.GITHUB_BASE_URL;

public class GetPRListActivity extends AppCompatActivity {

    private TextView textHeader;
    private RecyclerView recyclerView;

    SpinnerUtil spinnerUtil = new SpinnerUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textHeader = findViewById(R.id.mainHeaderView);
        recyclerView = findViewById(R.id.my_recycler_view);

        spinnerUtil.addSpinnerToActivity(this);
        spinnerUtil.showSpinner(getWindow().getDecorView().findViewById(R.id.spinner_root));

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(GITHUB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IApiPRListService IApiPRListService = retrofit.create(IApiPRListService.class);

        Call<List<JsonPojoClass>> call = IApiPRListService.getPullRequestList();
        call.enqueue(new Callback<List<JsonPojoClass>>() {
            @Override
            public void onResponse(@NonNull Call<List<JsonPojoClass>> call, @NonNull Response<List<JsonPojoClass>> response) {
                spinnerUtil.hideSpinner(getWindow().getDecorView().findViewById(R.id.spinner_root));
                List<JsonPojoClass> lists = response.body();
                textHeader.setText(getString(R.string.title_precursor) + GITHUB_BASE_URL);
                setRecyclerView(lists);
            }
            @Override
            public void onFailure(Call<List<JsonPojoClass>> call, Throwable t) {

            }
        });
    }

    // Process Methods

    private void setRecyclerView(List<JsonPojoClass> prList) {
        RecyclerView.Adapter prListAdapter;
        RecyclerView.LayoutManager layoutManager;

        prListAdapter = new PRListAdapter(prList, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(prListAdapter);
    }

}
