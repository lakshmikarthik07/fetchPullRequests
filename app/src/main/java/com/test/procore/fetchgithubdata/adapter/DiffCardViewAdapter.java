package com.test.procore.fetchgithubdata.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.procore.fetchgithubdata.R;
import com.test.procore.fetchgithubdata.activities.GetPRListActivity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

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
        if (position != 0) {
            spanableProcess(holder, position);
        } else
            holder.content.setText("LIST OF THE DIFFERENCES");
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

    private void spanableProcess(ViewHolder holder, int position) {

        String[] induvLineStrings = numberofcards.get(position).split("\n");

        BackgroundColorSpan bgc_green = new BackgroundColorSpan(Color.GREEN);
        BackgroundColorSpan bgc_blue = new BackgroundColorSpan(Color.BLUE);
        BackgroundColorSpan bgc_red = new BackgroundColorSpan(Color.RED);
        BackgroundColorSpan bgc_transp = new BackgroundColorSpan(Color.TRANSPARENT);

        // TODO Have to solve the loop issue
        //Pattern p = Pattern.compile("aaa", Pattern.CASE_INSENSITIVE);
        //Matcher m = p.matcher(notes);

        for (String curStr : induvLineStrings) {
            // Log.d(TAG,curStr+"************EOD***********"+"\n");
            SpannableString ss = new SpannableString(curStr);
            if (curStr.indexOf('-') >= 0) {
                ss.setSpan(bgc_red, curStr.indexOf('-'), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (curStr.indexOf('+') >= 0) {
                ss.setSpan(bgc_green, curStr.indexOf('+'), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (curStr.indexOf('@') >= 0) {
                ss.setSpan(bgc_blue, curStr.indexOf('@'), ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                ss.setSpan(bgc_transp, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            holder.content.append(ss);
            holder.content.append("\n");
        }
    }
    // TODO Have to solve the loop issue
}
