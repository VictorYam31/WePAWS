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
import com.victoryam.wepaws.Utils.IResult;
import com.victoryam.wepaws.Utils.Utility;

import org.json.JSONException;

import java.io.DataOutputStream;
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

public class ResultFragment extends Fragment {


    Utility utility;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result, container, false);
        Bundle bundle = this.getArguments();

        utility = new Utility();

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
        HashMap<String, List<String>> searchingCriteriaForGson;

        String queryUrl;
        String data;
        String jsonQueryContent;

        initResultsTask(View view, int categoryId, String[] componentNames, HashMap<Integer, List<String>> searchingCriteria) {
            this.view = view;
            this.categoryId = categoryId;
            this.componentNames = componentNames;
            this.searchingCriteria = searchingCriteria;
        }

        @Override
        protected void onPreExecute() {
            searchingCriteriaForGson = new HashMap<String, List<String>>();
            switch (categoryId) {
                case 1:
                    queryUrl = "https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_listGet";
                    break;
                case 2:
                    queryUrl = "https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_listGet";
                    break;
                case 3:
                    queryUrl = "https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_listGet";
                    break;
                case 4:
                    queryUrl = "https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_listGet";
                    break;
            }

            Iterator iterator = searchingCriteria.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry pair = (Map.Entry) iterator.next();
                //jsonContent.put(componentNames[pair.getKey()], pair.getValue());
                searchingCriteriaForGson.put(componentNames[(int) pair.getKey()], (List<String>) pair.getValue());
                iterator.remove(); // avoids a ConcurrentModificationException
            }
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;
            InputStream inStream;

            try {
                //Create a URL Connection object and set its parameters
                URL url = new URL(queryUrl);
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(15000);
                conn.setReadTimeout(10000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json; utf-8");
                conn.setRequestProperty("Accept", "application/json");

                OutputStream os = conn.getOutputStream();
                DataOutputStream writer = new DataOutputStream(os);
                String jsonString = utility.SerializeObject(searchingCriteriaForGson);
//                String a = "clinic_name";
//                List<String> b = new LinkedList<>();
//                b.add("null");
//                String c = "district";
//                List<String> d = new LinkedList<>();
//                d.add("6");
//                String e = "overnight";
//                List<String> f = new LinkedList<>();
//                f.add("N");
//                HashMap<String, List<String>> g = new  HashMap<String, List<String>>();
//                g.put(a, b);
//                g.put(c,d);
//                g.put(e,f);
//
//                String jsonString = utility.SerializeObject(g);
                writer.writeBytes(jsonString);
                writer.flush();
                writer.close();
                os.close();

                conn.connect();

                inStream = conn.getInputStream();
                data = utility.readStream(inStream);
            } catch (Exception e) {
                Log.v("Exception", String.valueOf(e));
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String a) {
            ListView listView = (ListView) view.findViewById(R.id.result_listview);
            List<IResult> iResultList = new ArrayList<>();

            if(data == null){
                return;
            }

            switch (categoryId) {
                case 1:
                    iResultList = new ArrayList<>(utility.DeserializeClinic(data));
                    break;
                case 2:
                    iResultList = new ArrayList<>(utility.DeserializeStore(data));
                    break;
                case 3:
                    iResultList = new ArrayList<>(utility.DeserializeDining(data));
                    break;
                case 4:
                    iResultList = new ArrayList<>(utility.DeserializePark(data));
                    break;
            }

            // VetMaster vet1 = new VetMaster();
            // vet1.setVetName("Tom");
            // vet1.setVetAddress(getResources().getString(R.string.hk_district_Kwun_Tong));
            // PetSpecies animal1 = new PetSpecies();
            // animal1.setSpeciesName(getResources().getString(R.string.animal_type_amphibian));
            // Category cat1 = new Category();
            // cat1.setDesc("Category description for Tom");
            // Result result1 = new Result(vet1, animal1, cat1, 4.5f);

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

            return view;
        }
    }
}