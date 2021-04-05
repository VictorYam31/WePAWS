package com.victoryam.wepaws;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.victoryam.wepaws.Utils.IReview;
import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.WebServiceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WriteReviewFragment extends Fragment {

    private int categoryId;
    private int id;
    private String name;
    private String userName;

    EditText reviewContent;

    private int rating = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.name = this.getArguments().getString("Name");
            this.id = this.getArguments().getInt("ID");
            this.categoryId = this.getArguments().getInt("CategoryId");
        } else {
            Log.v("empty arguments", "no name?");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.write_review, container, false);
        loadPreference();

        TextView title = (TextView) view.findViewById(R.id.write_review_title);
        title.setText(this.name);

        reviewContent = (EditText) view.findViewById(R.id.review_content);
        reviewContent.setHint("Write Review By " + userName);

        RadioGroup ratingButton = (RadioGroup) view.findViewById(R.id.rating_button);
        ratingButton.check(R.id.write_review_mediocre_radio_button);
        ratingButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.write_review_good_radio_button:
                        rating = 1;
                        break;
                    case R.id.write_review_mediocre_radio_button:
                        rating = 0;
                        break;
                    case R.id.write_review_bad_radio_button:
                        rating = -1;
                        break;
                }
            }
        });

        TextView submit = (TextView) view.findViewById(R.id.write_review_submit);
        submit.setOnClickListener(new addReview());
        return view;
    }

    private class addReview implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String success = "";
            String reviewContentString = String.valueOf(reviewContent.getText());
            ArrayList<IReview> reviewList = new ArrayList<IReview>();

            WebServiceManager webServiceManager = new WebServiceManager();

            try {
                switch (categoryId) {
                    case 1:
                        success = webServiceManager.add_clinic_review(String.valueOf(id), userName, reviewContentString, String.valueOf(rating));
                        reviewList =
                                new ArrayList<IReview>(webServiceManager.get_clinic_review(String.valueOf(id)));
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", categoryId);
            bundle.putInt("ID", id);
            bundle.putString("Name", name);
            bundle.putParcelableArrayList("ReviewList", reviewList);
            Navigation.findNavController(v).navigate(R.id.ReviewSummaryFragment, bundle);
        }
    }

    private void loadPreference() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        String name = preferences.getString("Name", "Guest");
        this.userName = name;
    }
}