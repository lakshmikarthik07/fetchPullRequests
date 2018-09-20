package com.test.procore.fetchgithubdata.utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static android.content.ContentValues.TAG;

//TODO I want to Read the Diff data from the URL

public class DiffReader { //extends AsyncTask<String, Void, Void> {
//
//    @Override
//    protected void doInBackground(String diff_url) {
//        try {
//            URL oracle = new URL(diff_url);
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(oracle.openStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null)
//                Log.d(TAG, inputLine);
//            in.close();
//        } catch (Exception ex) {
//            Log.d(TAG, ex.toString());
//            //return null;
//        } finally {
//            //is.close();
//        }
//    }
}
