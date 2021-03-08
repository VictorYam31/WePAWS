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

import org.w3c.dom.Text;

public class ResultDetailFragment extends Fragment {

    private Result result;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getArguments() != null) {
            this.result = this.getArguments().getParcelable("ResultDetailFragmentArg");
        }
        else {
            Log.v("empty arguments", "no results?");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result_detail, container, false);

        initResultDetails(view, this.result);

        return view;
    }

    private void initResultDetails(View view, Result result) {
        ListView listView = (ListView)view.findViewById(R.id.result_detail_listview);

        ResultDetailAdapter resultDetailAdapter = new ResultDetailAdapter(this.getContext(), result);
        listView.setAdapter(resultDetailAdapter);
    }

    public class ResultDetailAdapter extends BaseAdapter {

        private Context mContext;
        private Result result;

        public ResultDetailAdapter(Context c, Result result) {
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

            Log.v("getView", String.valueOf(type));

            if (type == 0) {
                view = inflater.inflate(R.layout.result_detail_0, null);
                TextView resultName = (TextView) view.findViewById(R.id.result_detail_0_name);
                TextView resultRating = (TextView) view.findViewById(R.id.result_detail_0_rating);
                resultName.setText(this.result.getVet().getVetName());
                resultRating.setText(Float.toString(this.result.getRating()));
            }
            else if (type == 1) {
                view = inflater.inflate(R.layout.result_detail_1, null);
                TextView resultInfo = (TextView) view.findViewById(R.id.result_detail_1_result_info);
                resultInfo.setText(this.result.getCategory().getDesc());
            }
            else {
                view = inflater.inflate(R.layout.result_detail_2, null);
            }

            return view;
        }
    }

}