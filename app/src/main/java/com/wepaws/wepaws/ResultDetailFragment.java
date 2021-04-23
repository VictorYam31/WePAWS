package com.wepaws.wepaws;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.wepaws.wepaws.Utils.IResult;
import com.wepaws.wepaws.Utils.IReview;
import com.wepaws.wepaws.Utils.Utility;
import com.wepaws.wepaws.WebService.WebServiceManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ResultDetailFragment extends Fragment {
    private Utility utility;
    private int language;

    private IResult iResult;
    private int categoryId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        utility = new Utility();
        language = utility.getLocale(this.getContext());

        Bundle bundle = this.getArguments();

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

        String title;
        switch (categoryId) {
            case 1:
                title = getResources().getString(R.string.home_menu_clinic);
                break;
            default:
                title = getResources().getString(R.string.result_detail);
                break;
        }
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);

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
                    reviewList =
                            new ArrayList<IReview>(webServiceManager.get_shop_review(String.valueOf(this.iResult.getIDForResult())));
                    break;
                case 3:
                    reviewList =
                            new ArrayList<IReview>(webServiceManager.get_hotel_review(String.valueOf(this.iResult.getIDForResult())));
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
        private int reviewPointer;
        private int reviewCount;

        private Context mContext;
        private IResult result;
        private List<IReview> reviewList;

        public ResultDetailAdapter(Context c, IResult result, List<IReview> reviewList) {
            this.reviewPointer = 0;
            this.reviewCount = 0;
            this.mContext = c;
            this.result = result;
            this.reviewList = reviewList;
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
            int type = this.getItemViewType(position);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (type == 0) {
                view = inflater.inflate(R.layout.result_detail_0, null);

                TextView resultName = (TextView) view.findViewById(R.id.result_detail_0_name);
                String tempName = "";
                if (language == 0) { //EN
                    tempName = this.result.getNameForResult();
                } else { //CN
                    tempName = this.result.getNameCNForResult();
                }
                resultName.setText(tempName);


                TextView goodRating = (TextView) view.findViewById(R.id.result_detail_0_good_number);
                goodRating.setText(String.valueOf(this.result.getPositiveCountForResult()));

                TextView mediocreRating = (TextView) view.findViewById(R.id.result_detail_0_mediocre_number);
                mediocreRating.setText(String.valueOf(this.result.getNeutralCountForResult()));

                TextView badRating = (TextView) view.findViewById(R.id.result_detail_0_bad_number);
                badRating.setText(String.valueOf(this.result.getNegativeCountForResult()));

                ImageView callImage = (ImageView) view.findViewById(R.id.result_detail_0_phone_call);
                callImage.setOnClickListener(new makePhoneCall(mContext, this.result.getPhoneNumberForResult()));

                ImageView shareImage = (ImageView) view.findViewById(R.id.result_detail_0_bookmark);
                shareImage.setOnClickListener(new shareInfo(mContext, this.result.getNameForResult(),
                        this.result.getAddressForResult(), this.result.getPhoneNumberForResult()));
            } else if (type == 1) {
                view = inflater.inflate(R.layout.result_detail_1, null);

                Calendar rightNow = Calendar.getInstance();
                int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);

                String status = "";
                boolean isOverNight = this.result.getIsOvernightForResult();
                TextView openingHoursStatus = (TextView) view.findViewById(R.id.result_detail_1_opening_hours_status);


                if (isOverNight) {
                    status = "Open  24 Hours";
                    openingHoursStatus.setTextColor(getResources().getColor(R.color.good));
                } else {
                    if (currentHour > 21 || currentHour < 9) {
                        status = "Closed  (Open at 9:00 - 21:00)";
                        openingHoursStatus.setTextColor(getResources().getColor(R.color.light_red));
                    } else {
                        status = "Open  9:00 - 21:00";
                        openingHoursStatus.setTextColor(getResources().getColor(R.color.good));
                    }
                }

                openingHoursStatus.setText(status);

                TextView phoneText = (TextView) view.findViewById(R.id.result_detail_1_phone);
                phoneText.setText(this.result.getPhoneNumberForResult());

                TextView addressText = (TextView) view.findViewById(R.id.result_detail_1_address);
                String tempAddress = "";
                if (language == 0) { //EN
                    tempAddress = this.result.getAddressForResult();
                } else { //CN
                    tempAddress = this.result.getAddressCNForResult();
                }
                addressText.setText(tempAddress);

                TextView descriptionText = (TextView) view.findViewById(R.id.result_detail_1_description);
                descriptionText.setText(this.result.getDescriptionForResult());
            } else if (type == 2) {
                view = inflater.inflate(R.layout.result_detail_2, null);

                TextView viewAll = (TextView) view.findViewById(R.id.result_detail_2_view_all);
                viewAll.setOnClickListener(new openReview(categoryId, reviewList));
            } else if (type == 3) {
                view = inflater.inflate(R.layout.review_display, null);

                TextView comment = (TextView) view.findViewById(R.id.review_display_comment);
                String review = this.reviewList.get(reviewPointer).getReviewForReview();
                if (review.length() > 25) {
                    review = review.substring(0, 25) + "...";
                }
                comment.setText(review);

                TextView userName = (TextView) view.findViewById(R.id.review_display_username);
                userName.setText(this.reviewList.get(reviewPointer).getLoginIDForReview());

                TextView dateText = (TextView) view.findViewById(R.id.review_display_date);
                dateText.setText(this.reviewList.get(reviewPointer).gerCreateDateForReview());

                ImageView image = (ImageView) view.findViewById(R.id.review_display_image);
                switch (this.reviewList.get(reviewPointer).getRateForReview()) {
                    case 1:
                        image.setImageResource(R.drawable.outline_sentiment_very_satisfied_24);
                        image.setColorFilter(getContext().getResources().getColor(R.color.good));
                        break;
                    case 0:
                        image.setImageResource(R.drawable.outline_sentiment_satisfied_24);
                        image.setColorFilter(getContext().getResources().getColor(R.color.mediocre));
                        break;
                    case -1:
                        image.setImageResource(R.drawable.outline_sentiment_dissatisfied_24);
                        image.setColorFilter(getContext().getResources().getColor(R.color.bad));
                        break;
                }

                if (reviewPointer < this.reviewCount - 1) {
                    reviewPointer++;
                } else {
                    reviewPointer = 0;
                }
            } else {
                view = inflater.inflate(R.layout.result_detail_3, null);

                TextView viewAll = (TextView) view.findViewById(R.id.result_detail_3_view_all);
                viewAll.setText(getResources().getString(R.string.result_detail_view) + " " + this.reviewList.size() + " " + getResources().getString(R.string.result_detail_reviews));

                Button writeReview = (Button) view.findViewById(R.id.result_detail_3_write_review);

                viewAll.setOnClickListener(new openReview(categoryId, reviewList));
                writeReview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Bundle bundle = new Bundle();
                        bundle.putString("Name", result.getNameForResult());
                        bundle.putInt("ID", result.getIDForResult());
                        bundle.putInt("CategoryId", categoryId);
                        Navigation.findNavController(view).navigate(R.id.action_ResultDetailFragment_to_writeReviewFragment, bundle);
                    }
                });
            }

            return view;
        }
    }

    private class openReview implements View.OnClickListener {
        private int categoryId;
        private ArrayList<IReview> reviewList;

        private openReview(int categoryId, List<IReview> reviewList) {
            this.categoryId = categoryId;
            this.reviewList = new ArrayList<IReview>(reviewList);
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", categoryId);
            bundle.putInt("ID", iResult.getIDForResult());
            bundle.putString("Name", iResult.getNameForResult());
            bundle.putParcelableArrayList("ReviewList", reviewList);
            Navigation.findNavController(view).navigate(R.id.action_ResultDetailFragment_to_ReviewSummaryFragment, bundle);
        }
    }

    private class makePhoneCall implements View.OnClickListener {
        private String phoneNumber;
        private Context mContext;

        private makePhoneCall(Context mContext, String phoneNumber) {
            this.mContext = mContext;
            this.phoneNumber = phoneNumber.replaceAll("\\s+", "");
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
    }

    private class shareInfo implements View.OnClickListener {
        private String name;
        private String address;
        private String phoneNumber;
        private Context mContext;

        private shareInfo(Context mContext, String name, String address, String phoneNumber) {
            this.mContext = mContext;

            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
        }

        @Override
        public void onClick(View view) {
            //String message = "Share from WePAWS: https://www.google.com/search?q=" + name ;
            StringBuilder message = new StringBuilder();
            message.append("Share from WePAWS: \r\n")
                    .append(name)
                    .append("\r\nAddress: ")
                    .append(address)
                    .append("\r\nPhone: ")
                    .append(phoneNumber);

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message.toString());

            startActivity(Intent.createChooser(share, "WePAWS Share Info"));
        }
    }
}