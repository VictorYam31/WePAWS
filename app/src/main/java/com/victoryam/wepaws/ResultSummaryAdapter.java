package com.victoryam.wepaws;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.victoryam.wepaws.Domain.VetReview;

public class ResultSummaryAdapter extends BaseAdapter {

    private Context mContext;
    private VetReview[] reviews;

    public ResultSummaryAdapter(Context mContext, VetReview[] reviews) {
        this.mContext = mContext;
        this.reviews = reviews;
    }

    @Override
    public int getCount() {
        return reviews.length;
    }

    @Override
    public Object getItem(int position) {
        return reviews[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.review_display, null);

        TextView comment = (TextView) view.findViewById(R.id.review_display_comment);
        ImageView image = (ImageView) view.findViewById(R.id.review_display_image);
        comment.setText(reviews[position].getReview());
//            Need a way to know if a review is: Good, Mediocre, Bad
//            image.setImageResource();

        return view;
    }
}
