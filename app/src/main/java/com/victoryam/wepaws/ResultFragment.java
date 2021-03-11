package com.victoryam.wepaws;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.victoryam.wepaws.Domain.Category;
import com.victoryam.wepaws.Domain.PetSpecies;
import com.victoryam.wepaws.Domain.VetMaster;
import com.victoryam.wepaws.Utils.Utility;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ResultFragment extends Fragment {
    int categoryId;
    HashMap<Integer, List<String>> searchingCriteria;

    Utility utility;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result, container, false);
        Bundle bundle = this.getArguments();

        if (bundle != null) {
            categoryId = bundle.getInt("CategoryId");
            searchingCriteria = (HashMap<Integer, List<String>>) bundle.getSerializable("SearchingCriteria");
        }

        String[] componentNames = new String[0];
        utility = new Utility();

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

        initResults(view, componentNames);
        return view;
    }

    private void initResults(View view, String[] componentNames) {
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

    public class ResultAdapter extends BaseAdapter {

        private Context mContext;
        private Result[] results;

        public ResultAdapter(Context c, Result[] results) {
            this.mContext = c;
            this.results = results;
        }

        @Override
        public int getCount() {
            return results.length;
        }

        @Override
        public Object getItem(int position) {
            return results[position];
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

            resultName.setText(this.results[position].getVet().getVetName());
            resultAddress.setText(this.results[position].getVet().getVetAddress());
            resultAnimal.setText(this.results[position].getAnimal().getSpeciesName());
            resultCategory.setText(this.results[position].getCategory().getDesc());
            resultRating.setRating(this.results[position].getRating());

            return view;
        }
    }

    private class initResultsTask extends AsyncTask<String, Integer, String> {
        View view;
        int category;
        String[] componentsName;
        HashMap<Integer, List<String>> searchingCriteria;

        String queryUrl;
        String data;
        String jsonQueryContent;

        initResultsTask(View view, int category, String[] componentsName, HashMap<Integer, List<String>> searchingCriteria) {
            this.view = view;
            this.category = category;
            this.componentsName = componentsName;
            this.searchingCriteria = searchingCriteria;
        }

        @Override
        protected void onPreExecute() {
            JSONObject jsonContent = new JSONObject();
            try {
                switch (category) {
                    case 1:
                        queryUrl = "https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_listGet";

                        jsonContent.put("clinic_name", searchingCriteria.get(0).toString()); //Name
                        //jsonContent.put("district", searchingCriteria.get(1).toString()); //District
                        //jsonContent.put("overnight", searchingCriteria.get(2).toString()); //Overnight
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            jsonQueryContent = jsonContent.toString();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;
            InputStream inStream;

            try {
                //Create a URL Connection object and set its parameters
                URL url = new URL(queryUrl);
                conn = (HttpURLConnection) url.openConnection();
                //Set connection time out of 5 seconds
                conn.setConnectTimeout(15000);
                //Set read connection time out of 2.5 seconds
                conn.setReadTimeout(10000);
                //Set HTTP request method
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("clinic_name=").append(URLEncoder.encode("Kowloon City Central Animal Hospital", "UTF-8"));
                //After the network response, retrieve the input stream
                OutputStream os = conn.getOutputStream();

                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(stringBuilder.toString());
                writer.flush();
                writer.close();
                os.close();

                conn.connect();

                inStream = conn.getInputStream();
                //Convert the input stream to String Bitmap
                data = utility.readStream(inStream);

//                OkHttpClient client = new OkHttpClient().newBuilder()
//                        .build();
//                MediaType mediaType = MediaType.parse("application/json");
//                String jsonContent = new JSONObject()
//                        .put("clinic_name",  "Kowloon City Central Animal Hospital")
//                        .put("district", 6)
//                        .put("overnight", "N")
//                        .toString();
//                RequestBody body = RequestBody.create(mediaType, jsonContent);
//                Request request = new Request.Builder()
//                        .url("https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_list")
//                        .method("POST", body)
//                        .addHeader("Content-Type", "application/json")
//                        .build();
//                Response response = client.newCall(request).execute();
//                String a = response.body().string();
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

            VetMaster vet1 = new VetMaster();
            vet1.setVetName("Tom");
            vet1.setVetAddress(getResources().getString(R.string.hk_district_Kwun_Tong));
            PetSpecies animal1 = new PetSpecies();
            animal1.setSpeciesName(getResources().getString(R.string.animal_type_amphibian));
            Category cat1 = new Category();
            cat1.setDesc("Category description for Tom");
            Result result1 = new Result(vet1, animal1, cat1, 4.5f);

            VetMaster vet2 = new VetMaster();
            vet2.setVetName("John");
            vet2.setVetAddress(getResources().getString(R.string.hk_district_Kowloon));
            PetSpecies animal2 = new PetSpecies();
            animal2.setSpeciesName(getResources().getString(R.string.animal_type_fish));
            Category cat2 = new Category();
            cat2.setDesc("Category description for John");
            Result result2 = new Result(vet2, animal2, cat2, 2f);

            VetMaster vet3 = new VetMaster();
            vet3.setVetName("Ken");
            vet3.setVetAddress(getResources().getString(R.string.hk_district_Hong_Kong_Island));
            PetSpecies animal3 = new PetSpecies();
            animal3.setSpeciesName(getResources().getString(R.string.animal_type_insect));
            Category cat3 = new Category();
            cat3.setDesc("Category description for Ken");
            Result result3 = new Result(vet3, animal3, cat3, 3.5f);
            Result[] results = {result1, result2, result3};


            ResultAdapter resultAdapter = new ResultAdapter(view.getContext(), results);
            listView.setAdapter(resultAdapter);
        }

        private String getQuery(List<BasicNameValuePair> params) throws UnsupportedEncodingException
        {
            StringBuilder result = new StringBuilder();
            boolean first = true;

            for (BasicNameValuePair pair : params)
            {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
            }

            return result.toString();
        }
    }
}