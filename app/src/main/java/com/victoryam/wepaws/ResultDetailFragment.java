package com.victoryam.wepaws;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.victoryam.wepaws.Utils.IResult;
import com.victoryam.wepaws.Utils.Utility;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class ResultDetailFragment extends Fragment {

    private Result result;
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
        ListView listView = (ListView)view.findViewById(R.id.result_detail_listview);

        ResultDetailAdapter resultDetailAdapter = new ResultDetailAdapter(this.getContext(), result);
        listView.setAdapter(resultDetailAdapter);
    }

    public class ResultDetailAdapter extends BaseAdapter {

        private Context mContext;
        private IResult result;

        public ResultDetailAdapter(Context c, IResult result) {
            this.mContext = c;
            this.result = result;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return 0;
            }
            else if (position == 1) {
                return 1;
            }
            else {
                return 2;
            }
        }

        @Override
        public int getViewTypeCount() {
            return 3;
        }

        @Override
        public boolean isEnabled(int position) {
            if (position == 0) {
                return false;
            }
            else if (position == 1) {
                return false;
            }
            else {
                return true;
            }
        }

        @Override
        public int getCount() {
            return 3;
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
                TextView resultRating = (TextView) view.findViewById(R.id.result_detail_0_rating);
                resultName.setText(this.result.getNameForResult());
                resultRating.setText(this.result.getRatingForResult());
            }
            else if (type == 1) {
                view = inflater.inflate(R.layout.result_detail_1, null);
                TextView resultInfo = (TextView) view.findViewById(R.id.result_detail_1_result_info);
                resultInfo.setText(this.result.getDescriptionForResult());
            }
            else {
                view = inflater.inflate(R.layout.result_detail_2, null);
                view.setOnClickListener(new openReview());
            }

            return view;
        }
    }

    private class openReview implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("name", result.getVet().getVetName());
            Navigation.findNavController(view).navigate(R.id.action_ResultDetailFragment_to_ReviewSummaryFragment, bundle);
        }
    }

}