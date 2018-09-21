package com.test.procore.fetchgithubdata.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.procore.fetchgithubdata.R;
import com.test.procore.fetchgithubdata.activities.GetPRListActivity;

import java.util.List;

public class DiffCardViewAdapter extends RecyclerView.Adapter<DiffCardViewAdapter.ViewHolder> {

    private List<String> numberofcards;
    private Context currentContext;

    public DiffCardViewAdapter(List<String> diff_content, Context context) {
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
        holder.content.setText(spanableProcess(position));
    }

    @Override
    public int getItemCount() {
        return numberofcards.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView content;

        ViewHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            content = itemView.findViewById(R.id.diffContentView);
        }
    }

    // Process Methods

    private SpannableString spanableProcess(int position) {
        BackgroundColorSpan bgc_green = new BackgroundColorSpan(Color.GREEN);
        BackgroundColorSpan bgc_transp = new BackgroundColorSpan(Color.TRANSPARENT);
        BackgroundColorSpan bgc_red = new BackgroundColorSpan(Color.RED);

        SpannableString ss = new SpannableString(numberofcards.get(position));
        String curStr = numberofcards.get(position);

        //TODO  Need Correct Spannable-Logic !!

//        if (curStr.contains("-"))
//            ss.setSpan(bgc_red, curStr.indexOf('-'), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        else if (curStr.contains("+"))
//            ss.setSpan(bgc_green, curStr.indexOf('+'), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        else
//            ss.setSpan(bgc_transp, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return ss;
    }
}
