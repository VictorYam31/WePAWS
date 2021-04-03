package com.victoryam.wepaws;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.victoryam.wepaws.Domain.VetReview;
import com.victoryam.wepaws.Utils.IResult;
import com.victoryam.wepaws.Utils.IReview;
import com.victoryam.wepaws.WebService.Model.ClinicReviewModel;
import com.victoryam.wepaws.WebService.WebServiceManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import kotlin.collections.ArrayDeque;

public class ResultDetailFragment extends Fragment {

    //    private Result result;
    private IResult iResult;
    private int categoryId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        String name = "";

        if (bundle != null) {
            iResult = bundle.getParcelable("IResult");
            categoryId = bundle.getInt("CategoryId");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_detail, container, false);
        initResultDetails(view, categoryId, this.iResult);

        return view;
    }

    private void initResultDetails(View view, int categoryId, IResult result) {
        ListView listView = (ListView) view.findViewById(R.id.result_detail_listview);
        List<IReview> reviewList = new ArrayList<>();

        try {
            WebServiceManager webServiceManager = new WebServiceManager();
            switch (categoryId) {
                case 1:
                    reviewList =
                            new ArrayList<IReview>(webServiceManager.get_clinic_review(String.valueOf(this.iResult.getIDForResult())));
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        ResultDetailAdapter resultDetailAdapter = new ResultDetailAdapter(this.getContext(), result, reviewList);
        listView.setAdapter(resultDetailAdapter);
    }

    public class ResultDetailAdapter extends BaseAdapter {
        private Context mContext;
        private IResult result;
        private int reviewPointer;
        private List<IReview> reviewList;
        private int reviewCount;

        public ResultDetailAdapter(Context c, IResult result, List<IReview> reviewList) {
            this.mContext = c;
            this.result = result;
            this.reviewList = reviewList;
            this.reviewPointer = 0;
            this.reviewCount = 0;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 0;
            } else if (position == 1) {
                return 1;
            } else if (position == 2) {
                return 2;
            } else if (position == (getCount() - 1)) {
                return 4;
            } else {
                return 3;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 5;
        }

        @Override
        public boolean isEnabled(int position) {
            return false;
        }

        @Override
        public int getCount() {
            if (reviewList.size() > 3) {
                reviewCount = 3;
            } else {
                reviewCount = reviewList.size();
            }
            return 3 + reviewCount + 1;  //3 + 1 is a must
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            Log.v("calling", String.valueOf(position));
            int type = this.getItemViewType(position);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (type == 0) {
                view = inflater.inflate(R.layout.result_detail_0, null);

                TextView resultName = (TextView) view.findViewById(R.id.result_detail_0_name);
                resultName.setText(String.valueOf(this.result.getNameForResult()));

                TextView goodRating = (TextView) view.findViewById(R.id.result_detail_0_good_number);
                goodRating.setText(String.valueOf(this.result.getPositiveCountForResult()));

                TextView mediocreRating = (TextView) view.findViewById(R.id.result_detail_0_mediocre_number);
                mediocreRating.setText(String.valueOf(this.result.getNeutralCountForResult()));

                TextView badRating = (TextView) view.findViewById(R.id.result_detail_0_bad_number);
                badRating.setText(String.valueOf(this.result.getNegativeCountForResult()));
            } else if (type == 1) {
                view = inflater.inflate(R.layout.result_detail_1, null);

                Calendar rightNow = Calendar.getInstance();
                int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);

                String status = "";
                boolean isOverNight = this.result.getIsOvernightForResult();

                if (isOverNight) {
                    status = "Open   24 Hours";
                } else {
                    if (currentHour > 21 || currentHour < 9) {
                        status = "Close   9:00 - 21:00";
                    } else {
                        status = "Open   9:00 - 21:00";
                    }
                }

                TextView openingHoursStatus = (TextView) view.findViewById(R.id.result_detail_1_opening_hours_status);
                openingHoursStatus.setText(status);

                TextView phoneText = (TextView) view.findViewById(R.id.result_detail_1_phone);
                phoneText.setText(this.result.getPhoneNumberForResult());

                TextView addressText = (TextView) view.findViewById(R.id.result_detail_1_address);
                addressText.setText(this.result.getAddressForResult());

                TextView descriptionText = (TextView) view.findViewById(R.id.result_detail_1_description);
                descriptionText.setText(this.result.getDescriptionForResult());
            } else if (type == 2) {
                view = inflater.inflate(R.layout.result_detail_2, null);

                TextView viewAll = (TextView) view.findViewById(R.id.result_detail_2_view_all);
                viewAll.setOnClickListener(new openReview());
            } else if (type == 3) {
                view = inflater.inflate(R.layout.review_display, null);

                TextView comment = (TextView) view.findViewById(R.id.review_display_comment);
                comment.setText(this.reviewList.get(reviewPointer).getReviewForReview());

                TextView userName = (TextView) view.findViewById(R.id.review_display_username);
                userName.setText(this.reviewList.get(reviewPointer).getLoginIDForReview());

                TextView dateText = (TextView) view.findViewById(R.id.review_display_date);
                dateText.setText(this.reviewList.get(reviewPointer).gerCreateDateForReview());

                ImageView image = (ImageView) view.findViewById(R.id.review_display_image);
                switch (this.reviewList.get(reviewPointer).getRateForReview()) {
                    case 1:
                        image.setImageResource(R.drawable.outline_sentiment_very_satisfied_24);
                        break;
                    case 0:
                        image.setImageResource(R.drawable.outline_sentiment_satisfied_24);
                        break;
                    case -1:
                        image.setImageResource(R.drawable.outline_sentiment_dissatisfied_24);
                        break;
                }

                if (reviewPointer < this.reviewCount - 1) {
                    reviewPointer++;
                } else {
                    reviewPointer = 0;
                }

//            Need a way to know if a review is: Good, Mediocre, Bad
//            image.setImageResource();
            } else {
                view = inflater.inflate(R.layout.result_detail_3, null);

                TextView viewAll = (TextView) view.findViewById(R.id.result_detail_3_view_all);
                viewAll.setText("View " + this.reviewList.size() + " reviews");

                Button writeReview = (Button) view.findViewById(R.id.result_detail_3_write_review);

                viewAll.setOnClickListener(new openReview());
                writeReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("name", iResult.getNameForResult());
                        Navigation.findNavController(view).navigate(R.id.action_ResultDetailFragment_to_writeReviewFragment, bundle);
                    }
                });
            }

            return view;
        }
    }

    private class openReview implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("name", iResult.getNameForResult());
            Navigation.findNavController(view).navigate(R.id.action_ResultDetailFragment_to_ReviewSummaryFragment, bundle);
        }
    }

}