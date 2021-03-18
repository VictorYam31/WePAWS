package com.victoryam.wepaws;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.victoryam.wepaws.Domain.Clinic;
import com.victoryam.wepaws.Domain.District;
import com.victoryam.wepaws.Utils.IResult;
import com.victoryam.wepaws.Utils.Utility;
import com.victoryam.wepaws.WebService.Model.ClinicMasterModel;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result, container, false);
        Bundle bundle = this.getArguments();

        utility = new Utility();  //Initialize Utility

        int categoryId = -1;
        String[] componentNames = {};
        HashMap<Integer, List<String>> searchingCriteria = new HashMap<>();

        if (bundle != null) {
            categoryId = bundle.getInt("CategoryId");
            searchingCriteria = (HashMap<Integer, List<String>>) bundle.getSerializable("SearchingCriteria");
        }

        switch (categoryId) {
            case 1:
                componentNames = getResources().getStringArray(R.array.search_clinic_component_names);
                break;
            case 2:
                componentNames = getResources().getStringArray(R.array.search_store_component_names);
                break;
            case 3:
                componentNames = getResources().getStringArray(R.array.search_dining_component_names);
                break;
            case 4:
                componentNames = getResources().getStringArray(R.array.search_park_component_names);
                break;
        }

        initResults(view, categoryId, componentNames, searchingCriteria);
        return view;
    }

    private void initResults(View view, int categoryId, String[] componentNames, HashMap<Integer, List<String>> searchingCriteria) {
        ListView listView = (ListView) view.findViewById(R.id.result_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
                Result result = (Result) adapterView.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("ResultDetailFragmentArg", result);
                Navigation.findNavController(view).navigate(R.id.action_ResultFragment_to_ResultDetailFragment, bundle);
            }
        });

        new initResultsTask(view, categoryId, componentNames, searchingCriteria).execute("");
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
                            if(componentName == "District"){
                                flattenCriteria += new District().GetNumberbyDistrict(searchingCriteriaList.get(n)) + ", ";
                            }else{
                                flattenCriteria += searchingCriteriaList.get(n) + ", ";
                            }
                        } else {
                            flattenCriteria += searchingCriteriaList.get(n);
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
                    case 1:
                        String clinic_name = searchingCriteriaForWebService.get("Name");
                        String district_id = searchingCriteriaForWebService.get("District");
                        String overnight = searchingCriteriaForWebService.get("Overnight");

                        iResultList = new ArrayList<>(webServiceManager.get_clinic_master(clinic_name, district_id, overnight));
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


            return "";
        }

        @Override
        protected void onPostExecute(String a) {
            ResultAdapter resultAdapter = new ResultAdapter(view.getContext(), iResultList);
            listView.setAdapter(resultAdapter);
        }
    }

    public class ResultAdapter extends BaseAdapter {

        private Context mContext;
        private List<IResult> iResultList;

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
            TextView resultAddress = (TextView) view.findViewById(R.id.result_display_address);
            TextView resultAnimal = (TextView) view.findViewById(R.id.result_animal);
            TextView resultCategory = (TextView) view.findViewById(R.id.result_category);
            RatingBar resultRating = (RatingBar) view.findViewById(R.id.result_rating);

            resultName.setText(this.iResultList.get(position).getNameForResult());
            resultAddress.setText(this.iResultList.get(position).getAddressForResult());
            resultAnimal.setText(this.iResultList.get(position).getSpeciesForResult());
            resultCategory.setText(this.iResultList.get(position).getDescriptionForResult());
            resultRating.setRating(Float.parseFloat(this.iResultList.get(position).getRatingForResult()));

            //Set Invisible txtView.setVisibility(View.INVISIBLE)

            return view;
        }
    }
}