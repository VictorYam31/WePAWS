package com.victoryam.wepaws;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.victoryam.wepaws.Domain.VetReview;
import com.victoryam.wepaws.Utils.IReview;

import java.util.List;

public class ReviewSummaryFragment extends Fragment {

    private String name;
    private List<IReview> reviewList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.name = this.getArguments().getString("Name");
            this.reviewList = this.getArguments().getParcelableArrayList("ReviewList");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.review_summary, container, false);

        TextView reviewSummaryTitle = (TextView) view.findViewById(R.id.review_summary_title);
        Button writeReview = (Button) view.findViewById(R.id.result_summary_write_review);
        reviewSummaryTitle.setText(getResources().getString(R.string.review_summary_title) + this.name);
        writeReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                Navigation.findNavController(view).navigate(R.id.action_ReviewSummaryFragment_to_writeReviewFragment, bundle);
            }
        });

        initReviews(view, reviewList);
        return view;
    }

    private void initReviews(View view, List<IReview> reviewList) {
        ListView listView = (ListView)view.findViewById(R.id.review_sumamry_listview);

        ResultSummaryAdapter resultDetailAdapter = new ResultSummaryAdapter(this.getContext(), reviewList);
        listView.setAdapter(resultDetailAdapter);
    }
}