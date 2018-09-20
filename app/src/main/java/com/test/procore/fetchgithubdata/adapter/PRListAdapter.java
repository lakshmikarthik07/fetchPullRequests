package com.test.procore.fetchgithubdata.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.procore.fetchgithubdata.GetDiffActivity;
import com.test.procore.fetchgithubdata.R;
import com.test.procore.fetchgithubdata.utils.JsonPojoClass;

import java.util.List;

public class PRListAdapter extends RecyclerView.Adapter<PRListAdapter.ViewHolder> {

    private List<JsonPojoClass> prList;
    private  Context context;
    public PRListAdapter(List<JsonPojoClass> prListFromActivity,Context currentContext) {
        context = currentContext;
        this.prList = prListFromActivity;
    }

    @Override
    public int getItemCount() {
        return prList.size();
    }

    @Override
    @NonNull
    public PRListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowItem = inflater.inflate(R.layout.pull_request_list_item, parent, false);
        return new PRListAdapter.ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(PRListAdapter.ViewHolder holder, int position) {
        JsonPojoClass currentListItem = prList.get(position);
        holder.title.setText(currentListItem.getTitle());
        holder.number.setText(currentListItem.getNumber());
        holder.id.setText(context.getString(R.string.pr_id)+currentListItem.getId());
        holder.state.setText(context.getString(R.string.current_state)+currentListItem.getState());

        holder.listRowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GetDiffActivity.class);
 //               intent.putExtra(currentListItem.getDiff_url());
                context.startActivity(intent);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout listRowItem;
        private TextView  title;
        private TextView  number;
        private TextView  id;
        private TextView  state;

        ViewHolder(View itemView) {
            super(itemView);
            listRowItem = itemView.findViewById(R.id.listRowItem);

            title = itemView.findViewById(R.id.layout_title);
            number = itemView.findViewById(R.id.layout_number);
            id = itemView.findViewById(R.id.layout_id);
            state = itemView.findViewById(R.id.layout_state);
        }

    }
}
