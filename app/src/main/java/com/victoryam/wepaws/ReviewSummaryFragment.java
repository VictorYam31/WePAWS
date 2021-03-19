package com.victoryam.wepaws;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.victoryam.wepaws.Domain.VetReview;

public class ReviewSummaryFragment extends Fragment {

    private String name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.name = this.getArguments().getString("name");
        }
        else {
            Log.v("empty arguments", "no name?");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_summary, container, false);

        TextView reviewSummaryTitle = (TextView) view.findViewById(R.id.review_summary_title);
        reviewSummaryTitle.setText(getResources().getString(R.string.review_summary_title) + this.name);

//        dummy reviews
        VetReview r1 = new VetReview();
        r1.setReview("Good doctor");
        VetReview r2 = new VetReview();
        r2.setReview("Too expensive");
        VetReview r3 = new VetReview();
        r3.setReview("Unprofessional");
        VetReview[] reviews = {r1, r2, r3};
//
        initReviews(view, reviews);

        return view;
    }

    private void initReviews(View view, VetReview[] reviews) {
        ListView listView = (ListView)view.findViewById(R.id.review_sumamry_listview);

        ResultSummaryAdapter resultDetailAdapter = new ResultSummaryAdapter(this.getContext(), reviews);
        listView.setAdapter(resultDetailAdapter);
    }

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
}