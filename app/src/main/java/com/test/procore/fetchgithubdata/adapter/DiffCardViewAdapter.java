package com.test.procore.fetchgithubdata.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.procore.fetchgithubdata.R;

import java.util.List;

public class DiffCardViewAdapter extends RecyclerView.Adapter<DiffCardViewAdapter.ViewHolder> {

   private List<String> numberofcards;
   private Context currentContext;

    public DiffCardViewAdapter(List<String> diff_content,Context context) {
        context = currentContext;
        this.numberofcards = diff_content;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View diffItem = inflater.inflate(R.layout.layout_show_differences, viewGroup, false);
        return new DiffCardViewAdapter.ViewHolder(diffItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       // if ((position%2)==0)
        //holder.cardView.setCardBackgroundColor(R.color.colorAccent);
        String currentitem = numberofcards.get(position);
        holder.content.setText(currentitem);

    }

    @Override
    public int getItemCount() {
        return numberofcards.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView content;

        ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            content = itemView.findViewById(R.id.diffContentView);
        }
    }
}
