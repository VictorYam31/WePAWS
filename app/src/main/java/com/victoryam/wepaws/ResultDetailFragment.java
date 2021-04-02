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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        int categoryId = -1;
        String name = "";

        if (bundle != null) {
            iResult = bundle.getParcelable("IResult");
        }

//        if (this.getArguments() != null) {
//            this.result = this.getArguments().getParcelable("ResultDetailFragmentArg");
//        }
//        else {
//            Log.v("empty arguments", "no results?");
//        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_detail, container, false);

        initResultDetails(view, this.iResult);

        return view;
    }

    private void initResultDetails(View view, IResult result) {
        ListView listView = (ListView) view.findViewById(R.id.result_detail_listview);

//        //        dummy reviews
//        VetReview r1 = new VetReview();
//        r1.setReview("Good doctor");
//        VetReview r2 = new VetReview();
//        r2.setReview("Too expensive");
//        VetReview r3 = new VetReview();
//        r3.setReview("Unprofessional");
//        VetReview[] shortReviews = {r1, r2, r3};
//        //

        List<ClinicReviewModel> clinicReviewModelList = new ArrayList<ClinicReviewModel>();

        try {
            WebServiceManager webServiceManager = new WebServiceManager();
            clinicReviewModelList =
                    new ArrayList<ClinicReviewModel>(webServiceManager.get_clinic_review(String.valueOf(this.iResult.getIDForResult())));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }


        ResultDetailAdapter resultDetailAdapter = new ResultDetailAdapter(this.getContext(), result, clinicReviewModelList);
        listView.setAdapter(resultDetailAdapter);
    }

    public class ResultDetailAdapter extends BaseAdapter {

        private Context mContext;
        private IResult result;
        private int reviewPointer;
        private List<ClinicReviewModel> clinicReviewModelList;
        private final int reviewCount = 3;

        public ResultDetailAdapter(Context c, IResult result, List<ClinicReviewModel> clinicReviewModelList) {
            this.mContext = c;
            this.result = result;
            this.clinicReviewModelList = clinicReviewModelList;
            this.reviewPointer = 0;
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
//            if (position == 0) {
//                return false;
//            }
//            else if (position == 1) {
//                return false;
//            }
//            else {
//                return true;
//            }
        }

        @Override
        public int getCount() {
            return 3 + reviewCount + 1;
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

                String a = String.valueOf(this.result.getPositiveCountForResult());
                TextView goodRating = (TextView) view.findViewById(R.id.result_detail_0_good_number);
                goodRating.setText(String.valueOf(this.result.getPositiveCountForResult()));

                String b = String.valueOf(this.result.getNeutralCountForResult());
                TextView mediocreRating = (TextView) view.findViewById(R.id.result_detail_0_mediocre_number);
                mediocreRating.setText(String.valueOf(this.result.getNeutralCountForResult()));

                String c = String.valueOf(this.result.getNegativeCountForResult());
                TextView badRating = (TextView) view.findViewById(R.id.result_detail_0_bad_number);
                badRating.setText(String.valueOf(this.result.getNegativeCountForResult()));

            } else if (type == 1) {
                view = inflater.inflate(R.layout.result_detail_1, null);
                Calendar rightNow = Calendar.getInstance();
                int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
                String status = "";
                String openingHours = "";
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

                String a = this.result.getPhoneNumberForResult();
                TextView phoneText = (TextView) view.findViewById(R.id.result_detail_1_phone);
                phoneText.setText(this.result.getPhoneNumberForResult());

                String b = this.result.getAddressForResult();
                TextView addressText = (TextView) view.findViewById(R.id.result_detail_1_address);
                addressText.setText(this.result.getAddressForResult());

                String c = this.result.getDescriptionForResult();
                TextView descriptionText = (TextView) view.findViewById(R.id.result_detail_1_description);
                descriptionText.setText(this.result.getDescriptionForResult());

            } else if (type == 2) {
                view = inflater.inflate(R.layout.result_detail_2, null);
                TextView viewAll = (TextView) view.findViewById(R.id.result_detail_2_view_all);
                viewAll.setOnClickListener(new openReview());
            } else if (type == 3) {
                view = inflater.inflate(R.layout.review_display, null);
                TextView comment = (TextView) view.findViewById(R.id.review_display_comment);
                comment.setText(this.clinicReviewModelList.get(reviewPointer).getReview());
                if (reviewPointer < this.reviewCount) {
                    reviewPointer++;
                } else {
                    reviewPointer = 0;
                }
                TextView userName = (TextView) view.findViewById(R.id.review_display_username);
                userName.setText("Guest");
                TextView dateText = (TextView) view.findViewById(R.id.review_display_date);
                userName.setText("2021-1-1 09:00");
                ImageView image = (ImageView) view.findViewById(R.id.review_display_image);
//            Need a way to know if a review is: Good, Mediocre, Bad
//            image.setImageResource();
            } else {
                view = inflater.inflate(R.layout.result_detail_3, null);
                TextView viewAll = (TextView) view.findViewById(R.id.result_detail_3_view_all);
                viewAll.setText("View " + this.clinicReviewModelList.size() + " reviews");
                Button writeReview = (Button) view.findViewById(R.id.result_detail_3_write_review);
                viewAll.setOnClickListener(new openReview());
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