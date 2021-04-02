package com.victoryam.wepaws;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

}