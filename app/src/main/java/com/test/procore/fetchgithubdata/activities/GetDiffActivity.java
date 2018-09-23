package com.test.procore.fetchgithubdata.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.procore.fetchgithubdata.R;
import com.test.procore.fetchgithubdata.adapter.DiffCardViewAdapter;
import com.test.procore.fetchgithubdata.serviceinterface.IApiDiffServiceCall;
import com.test.procore.fetchgithubdata.utils.SpinnerUtil;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.test.procore.fetchgithubdata.serviceinterface.IApiDiffServiceCall.GITHUB_DIFF_BASEURL;


public class GetDiffActivity extends AppCompatActivity {

    private RecyclerView diff_recyclerView;
    private String intentRecieve;

    SpinnerUtil spinnerUtil = new SpinnerUtil();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_diff);
        diff_recyclerView = findViewById(R.id.diff_recycler_view);
        spinnerUtil.addSpinnerToActivity(this);
        spinnerUtil.showSpinner(getWindow().getDecorView().findViewById(R.id.spinner_root));

        Intent intent = getIntent();
        if (intent.getExtras() != null)
            intentRecieve = intent.getExtras().getString("diffUrlFromIntent");

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(GITHUB_DIFF_BASEURL)
                .build();

        IApiDiffServiceCall IDiffService = retrofit.create(IApiDiffServiceCall.class);
        Call<String> getDifferencesCall = IDiffService.getStringResponse(intentRecieve);
        getDifferencesCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> getDifferencesCall, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    spinnerUtil.hideSpinner(getWindow().getDecorView().findViewById(R.id.spinner_root));
                    String responseString = response.body();
                    if (responseString != null)
                        setRecyclerView(stringSplitProcess(responseString));
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> getDifferencesCall, Throwable t) {
                //TODO  Toast message !!
            }
        });
    }

    // Process Methods

    private List<String> stringSplitProcess(String responseString) {
        String[] parts = responseString.trim().split("diff --git");
        return Arrays.asList(parts);
    }

    private void setRecyclerView(List<String> diffList) {
        RecyclerView.Adapter diffCardViewAdapter;
        RecyclerView.LayoutManager layoutManager;

        diffCardViewAdapter = new DiffCardViewAdapter(diffList);
        layoutManager = new LinearLayoutManager(this);
        diff_recyclerView.setLayoutManager(layoutManager);
        diff_recyclerView.setHasFixedSize(true);

        diff_recyclerView.setAdapter(diffCardViewAdapter);
    }
}
