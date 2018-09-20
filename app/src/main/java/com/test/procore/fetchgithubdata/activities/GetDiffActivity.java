package com.test.procore.fetchgithubdata.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.test.procore.fetchgithubdata.R;
import com.test.procore.fetchgithubdata.adapter.DiffCardViewAdapter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


public class GetDiffActivity extends AppCompatActivity {

    private RecyclerView diff_recyclerView;
    private RecyclerView.Adapter diffCardViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //private List<String> lisdOfDifferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_diff);
        diff_recyclerView = findViewById(R.id.diff_recycler_view);

        List<String> test = new ArrayList<String>();

        test.add(getString(R.string.test_str));
        test.add("harish");
        test.add("Susheel");test.add("Raj shekar");test.add("Nirmal");
//        test.add("Divya");
//        test.add("Narsimha");
//        test.add("Mummy");
//        test.add("Kalyan");
//        test.add("Daddy");

        setRecyclerView(test);
    }

    private void setRecyclerView(List<String> Test) {

        diffCardViewAdapter = new DiffCardViewAdapter(Test,this);

        layoutManager = new LinearLayoutManager(this);
        diff_recyclerView.setLayoutManager(layoutManager);
        diff_recyclerView.setHasFixedSize(true);

        diff_recyclerView.setAdapter(diffCardViewAdapter);
    }
}




//TODO OkHttp Content

//      Observable.fromCallable(() -> {
//          Request request = new Request.Builder()
//                  .url(url)
//                  .build();
//          try {
//              Response response = sHttpClient.newCall(request).execute();
//              return response.isSuccessful();
//          } catch (IOException e) {
//              Log.e("Network request", "Failure", e);
//          }
//
//          return false;
//      })
//              .subscribeOn(Schedulers.io())
//              .observeOn(AndroidSchedulers.mainThread())
//              .subscribe((result) -> {
//                  //Use result for something
//              });