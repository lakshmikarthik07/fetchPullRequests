package com.test.procore.fetchgithubdata.utils;


import android.app.Activity;

import android.view.View;
import android.view.ViewGroup;

import com.test.procore.fetchgithubdata.R;

public class SpinnerUtil {

    public ViewGroup addSpinnerToActivity(Activity activity) {
        ViewGroup activityViewGroup = (ViewGroup) activity.getWindow().getDecorView();
        ViewGroup spinnerViewGroup = (ViewGroup) activity.getLayoutInflater().inflate(R.layout.layout_spinner, null);
        activityViewGroup.addView(spinnerViewGroup);
        return spinnerViewGroup;
    }

    public void showSpinner(final ViewGroup spinnerViewGroup) {
        if (spinnerViewGroup != null) {
            spinnerViewGroup.setVisibility(View.VISIBLE);
        }
    }

    public void hideSpinner(ViewGroup spinnerViewGroup) {
        if (spinnerViewGroup != null) {
            spinnerViewGroup.setVisibility(View.GONE);
        }
    }

}
