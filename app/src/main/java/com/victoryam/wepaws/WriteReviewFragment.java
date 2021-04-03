package com.victoryam.wepaws;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WriteReviewFragment extends Fragment {

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

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.write_review, container, false);

        TextView title = (TextView) view.findViewById(R.id.write_review_title);
        title.setText(getResources().getString(R.string.write_review_title) + this.name);

        TextView submit = (TextView) view.findViewById(R.id.write_review_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("submit button", "clicked");
            }
        });

        return view;
    }

}