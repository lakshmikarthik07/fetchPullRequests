package com.test.procore.fetchgithubdata.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.test.procore.fetchgithubdata.R;
import com.test.procore.fetchgithubdata.utils.JsonPojoClass;

import java.util.List;

public class PRListAdapter extends RecyclerView.Adapter<PRListAdapter.ViewHolder> {

    private List<JsonPojoClass> categories;
    private  Context context;
    public PRListAdapter(List<JsonPojoClass> categories,Context currentContext) {
        context = currentContext;
        this.categories = categories;
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    @Override
    @NonNull
    public PRListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryViewTest = inflater.inflate(R.layout.pull_request_list_item, parent, false);
        return new PRListAdapter.ViewHolder(categoryViewTest);
    }

    @Override
    public void onBindViewHolder(PRListAdapter.ViewHolder holder, int position) {
//        holder.appName.setText(currentCategory);
//        holder.arrowImage.setVisibility(View.VISIBLE);
        holder.listRowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, BrowseCategoryActivity.class);
//                intent.putExtra(BrowseCategoryActivity.EXTRA_APP_CATEGORY_ID, diff_url);
//                context.startActivity(intent);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout listRowItem;

        ViewHolder(View view) {
            super(view);
        }

    }
}
