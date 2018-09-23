package com.test.procore.fetchgithubdata.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.test.procore.fetchgithubdata.R;
import com.test.procore.fetchgithubdata.adapter.DiffCardViewAdapter;
import com.test.procore.fetchgithubdata.serviceinterface.IApiDiffServiceCall;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.test.procore.fetchgithubdata.serviceinterface.IApiDiffServiceCall.GITHUB_DIFF_BASEURL;
import static com.test.procore.fetchgithubdata.serviceinterface.IApiPRListService.GITHUB_BASE_URL;


public class GetDiffActivity extends AppCompatActivity {

    private RecyclerView diff_recyclerView;
    private RecyclerView.Adapter diffCardViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<String> diffSubStringList = new ArrayList<>();

    private static final String TAG = GetDiffActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_diff);
        diff_recyclerView = findViewById(R.id.diff_recycler_view);
        Intent intent = getIntent();
        String callurl = intent.getExtras().getString("diffUrlFromIntent");
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(GITHUB_DIFF_BASEURL)
                .build();

        IApiDiffServiceCall IDiffService = retrofit.create(IApiDiffServiceCall.class);
        Call<String> getDifferencesCall = IDiffService.getStringResponse("14291.diff");
        getDifferencesCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> getDifferencesCall, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    String responseString = response.body();
                    setRecyclerView(stringProcess(responseString));
                }
            }
            @Override
            public void onFailure(@NonNull Call<String> getDifferencesCall, Throwable t) {
                //TODO  Toast message !!
            }
        });
    }
    private List<String> stringProcess(String responseString) {
        String[] parts = responseString.trim().split("diff --git");
        List<String> returnString= Arrays.asList(parts);
       // returnString.remove(0);
        return returnString;
    }

    private void setRecyclerView(List<String> Test) {

        diffCardViewAdapter = new DiffCardViewAdapter(Test, this);
        layoutManager = new LinearLayoutManager(this);
        diff_recyclerView.setLayoutManager(layoutManager);
        diff_recyclerView.setHasFixedSize(true);
        diff_recyclerView.setAdapter(diffCardViewAdapter);
    }
}
