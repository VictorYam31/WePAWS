package com.victoryam.wepaws;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.victoryam.wepaws.Domain.VetReview;
import com.victoryam.wepaws.Utils.IReview;

import java.util.List;

public class ResultSummaryAdapter extends BaseAdapter {
    private Context mContext;
    private List<IReview> reviewList;

    public ResultSummaryAdapter(Context mContext, List<IReview> reviewList) {
        this.mContext = mContext;
        this.reviewList = reviewList;
    }

    @Override
    public int getCount() {
        return reviewList.size();
    }

    @Override
    public Object getItem(int position) {
        return reviewList.get(position);
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
        comment.setText(this.reviewList.get(position).getReviewForReview());

        TextView userName = (TextView) view.findViewById(R.id.review_display_username);
        userName.setText(this.reviewList.get(position).getLoginIDForReview());

        TextView dateText = (TextView) view.findViewById(R.id.review_display_date);
        dateText.setText(this.reviewList.get(position).gerCreateDateForReview());

        ImageView image = (ImageView) view.findViewById(R.id.review_display_image);
        switch (this.reviewList.get(position).getRateForReview()) {
            case 1:
                image.setImageResource(R.drawable.outline_sentiment_very_satisfied_24);
                image.setColorFilter(mContext.getResources().getColor(R.color.good));
                break;
            case 0:
                image.setImageResource(R.drawable.outline_sentiment_satisfied_24);
                image.setColorFilter(mContext.getResources().getColor(R.color.mediocre));
                break;
            case -1:
                image.setImageResource(R.drawable.outline_sentiment_dissatisfied_24);
                image.setColorFilter(mContext.getResources().getColor(R.color.bad));
                break;
        }
        return view;
    }
}
