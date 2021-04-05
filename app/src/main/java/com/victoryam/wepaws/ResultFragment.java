package com.victoryam.wepaws;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.victoryam.wepaws.Domain.Clinic;
import com.victoryam.wepaws.Domain.District;
import com.victoryam.wepaws.Utils.IResult;
import com.victoryam.wepaws.Utils.Utility;
import com.victoryam.wepaws.WebService.Model.ClinicMasterModel;
import com.victoryam.wepaws.WebService.Model.WildSearchModel;
import com.victoryam.wepaws.WebService.Task.WildSearchTask;
import com.victoryam.wepaws.WebService.WebServiceManager;

import org.json.JSONException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ResultFragment extends Fragment {
    Utility utility;
    int language;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result, container, false);
        Bundle bundle = this.getArguments();

        utility = new Utility();  //Initialize Utility
        language = utility.getLocale(this.getContext());

        int categoryId = -1;
        String[] componentNames = {};
        HashMap<Integer, List<String>> searchingCriteria = new HashMap<>();

        if (bundle != null) {
            categoryId = bundle.getInt("CategoryId");
            searchingCriteria = (HashMap<Integer, List<String>>) bundle.getSerializable("SearchingCriteria");
        }

        switch (categoryId) {
            case 0:
                componentNames = getResources().getStringArray(R.array.wild_search_names);
                break;
            case 1:
                componentNames = getResources().getStringArray(R.array.search_clinic_component_names);
                break;
            case 2:
                componentNames = getResources().getStringArray(R.array.search_store_component_names);
                break;
            case 3:
                componentNames = getResources().getStringArray(R.array.search_hotel_component_names);
                break;
//            case 4:
//                componentNames = getResources().getStringArray(R.array.search_park_component_names);
//                break;
        }

        new initResultsTask(view, categoryId, componentNames, searchingCriteria).execute("");
        return view;
    }

    private class initResultsTask extends AsyncTask<String, Integer, String> {
        View view;
        int categoryId;
        String[] componentNames;
        HashMap<Integer, List<String>> searchingCriteria;
        HashMap<String, String> searchingCriteriaForWebService;
        List<IResult> iResultList;
        ListView listView;

        initResultsTask(View view, int categoryId, String[] componentNames, HashMap<Integer, List<String>> searchingCriteria) {
            this.view = view;
            this.categoryId = categoryId;
            this.componentNames = componentNames;
            this.searchingCriteria = searchingCriteria;
            this.searchingCriteriaForWebService = new HashMap<String, String>();
        }

        @Override
        protected void onPreExecute() {
            listView = (ListView) view.findViewById(R.id.result_listview);
            searchingCriteriaForWebService = new HashMap<String, String>();

            for (int i = 0; i < componentNames.length; i++) {
                String componentName = componentNames[i];

                if (searchingCriteria.containsKey(i)) {
                    List<String> searchingCriteriaList = searchingCriteria.get(i);
                    String flattenCriteria = "";
                    for (int n = 0; n < searchingCriteriaList.size(); n++) {
                        if (n != searchingCriteriaList.size() - 1) {
                            if (componentName.equals("District")) {
                                flattenCriteria += new District().GetNumberbyDistrict(searchingCriteriaList.get(n)) + ", ";
                            } else {
                                flattenCriteria += searchingCriteriaList.get(n) + ", ";
                            }
                        } else {
                            if (componentName.equals("District")) {
                                flattenCriteria += new District().GetNumberbyDistrict(searchingCriteriaList.get(n));
                            } else {
                                flattenCriteria += searchingCriteriaList.get(n);
                            }
                        }
                    }

                    searchingCriteriaForWebService.put(componentNames[i], flattenCriteria);
                } else {
                    searchingCriteriaForWebService.put(componentNames[i], "");
                }
            }
        }

        @Override
        protected String doInBackground(String... params) {
            WebServiceManager webServiceManager = new WebServiceManager();
            iResultList = new ArrayList<>();

            try {
                switch (categoryId) {
                    case 0:
                        String name = searchingCriteriaForWebService.get("Name");
                        iResultList = new ArrayList<IResult>(webServiceManager.wild_search(name));
                        break;
                    case 1:
                        String clinic_name = searchingCriteriaForWebService.get("Name");
                        String clinic_district_id = searchingCriteriaForWebService.get("District");
                        String overnight = searchingCriteriaForWebService.get("Overnight");

                        iResultList = new ArrayList<>(webServiceManager.get_clinic_master(clinic_name, clinic_district_id, overnight));
                        break;
                    case 2:
                        String shop_name = searchingCriteriaForWebService.get("Name");
                        String shop_district_id = searchingCriteriaForWebService.get("District");
                        iResultList = new ArrayList<>(webServiceManager.get_shop_master(shop_name, shop_district_id));
                        break;
                    case 3:
                        break;
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String a) {
            ResultAdapter resultAdapter = new ResultAdapter(view.getContext(), iResultList);
            listView.setAdapter(resultAdapter);
            listView.setOnItemClickListener(new openDetailResultFragment(categoryId, iResultList));
        }
    }

    public class ResultAdapter extends BaseAdapter {
        private Context mContext;
        private List<IResult> iResultList;
        private final TypedArray itemImages = getResources().obtainTypedArray(R.array.home_menu_item_images);

        public ResultAdapter(Context c, List<IResult> iResultList) {
            this.mContext = c;
            this.iResultList = iResultList;
        }

        @Override
        public int getCount() {
            return iResultList.size();
        }

        @Override
        public Object getItem(int position) {
            return iResultList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.result_display, null);
            }

            TextView resultName = (TextView) view.findViewById(R.id.result_display_name);
            String tempName = "";
            if (language == 0) { //EN
                tempName = this.iResultList.get(position).getNameForResult();
            } else { //CN
                tempName = this.iResultList.get(position).getNameCNForResult();
            }
            resultName.setText(tempName);

            TextView resultAddress = (TextView) view.findViewById(R.id.result_display_address);
            String tempAddress = "";
            if (language == 0) { //EN
                tempAddress = this.iResultList.get(position).getAddressForResult();
            } else { //CN
                tempAddress = this.iResultList.get(position).getAddressCNForResult();
            }
            resultAddress.setText(tempAddress);


            TextView resultCategory = (TextView) view.findViewById(R.id.result_category);
            String categoryText = this.iResultList.get(position).getDescriptionForResult();
            if (categoryText.length() > 50) {
                categoryText = categoryText.substring(0, 50) + "...";
            } else if(categoryText.equals("")){
                resultCategory.setVisibility(View.GONE);
            }
            resultCategory.setText(categoryText);

            TextView goodNumberText = (TextView) view.findViewById(R.id.result_display_good_number);
            goodNumberText.setText(String.valueOf(this.iResultList.get(position).getPositiveCountForResult()));

            TextView badNumberText = (TextView) view.findViewById(R.id.result_display_bad_number);
            badNumberText.setText(String.valueOf(this.iResultList.get(position).getNegativeCountForResult()));

            ImageView image = (ImageView) view.findViewById(R.id.result_display_icon);

            @StyleableRes
            int itemCategory = this.iResultList.get(position).getCategoryForResult();
            image.setImageResource(itemImages.getResourceId(itemCategory, -1));

            return view;
        }
    }

    private class openDetailResultFragment implements AdapterView.OnItemClickListener {
        int categoryId;
        List<IResult> iResultList;

        public openDetailResultFragment(int categoryId, List<IResult> iResultList) {
            this.categoryId = categoryId;
            this.iResultList = iResultList;
        }

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
            IResult iResult = iResultList.get(index);

            Bundle bundle = new Bundle();
            bundle.putParcelable("IResult", iResult);
            bundle.putInt("CategoryId", categoryId);

            Navigation.findNavController(view).navigate(R.id.action_ResultFragment_to_ResultDetailFragment, bundle);
        }
    }
}