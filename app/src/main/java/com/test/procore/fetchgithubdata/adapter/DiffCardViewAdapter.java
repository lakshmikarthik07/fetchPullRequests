package com.test.procore.fetchgithubdata.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
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

    private List<String> numberOfCards;

    public DiffCardViewAdapter(List<String> diff_content) {
        this.numberOfCards = diff_content;
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
            holder.content.setText(spanableProcess(position));
        } else
            holder.content.setText("LIST OF THE DIFFERENCES");
    }

    @Override
    public int getItemCount() {
        return numberOfCards.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // private CardView cardView;
        private TextView content;

        ViewHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.diffContentView);
        }
    }

    // Process Methods

    private SpannableStringBuilder spanableProcess(int position) {

        String[] induvLineStrings = numberOfCards.get(position).split("\n");
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder();

        BackgroundColorSpan bgc_green = new BackgroundColorSpan(Color.GREEN);
        BackgroundColorSpan bgc_blue = new BackgroundColorSpan(R.color.colorPrimary);
        BackgroundColorSpan bgc_red = new BackgroundColorSpan(Color.RED);
        BackgroundColorSpan bgc_transp = new BackgroundColorSpan(Color.TRANSPARENT);

        for (String curStr : induvLineStrings) {
            SpannableString ss = new SpannableString(curStr);
            if (curStr.length() > 0) {
                if (curStr.indexOf('@') == 0) {
                    ss.setSpan(bgc_blue, 0, curStr.length(), Spanned.SPAN_POINT_POINT);
                } else if (curStr.indexOf('+') == 0) {
                    ss.setSpan(bgc_green, 0, curStr.length(), Spanned.SPAN_POINT_POINT);
                    //ss.setSpan();
                } else if (curStr.indexOf('-') == 0) {
                    ss.setSpan(bgc_red, 0, curStr.length(), Spanned.SPAN_POINT_POINT);
                } else {
                    ss.setSpan(bgc_transp, 0,curStr.length(), Spanned.SPAN_POINT_POINT);
                }
            }
            ssBuilder.append(ss);
            ssBuilder.append("\n");
        }
        return ssBuilder;
    }
}
