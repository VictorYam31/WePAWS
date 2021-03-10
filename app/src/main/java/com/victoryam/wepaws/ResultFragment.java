package com.victoryam.wepaws;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Parcelable;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

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

/*//        dummy
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
//*/

        //initResults(view, results);
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


        //ResultAdapter resultAdapter = new ResultAdapter(this.getContext(), componentNames);
        //listView.setAdapter(resultAdapter);
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
        String queryUrl;
        String data;
        String[] componentsName;
        HashMap<String, List<String>> searchingCriteria;

        initResultsTask(View view, int category, String[] componentsName, HashMap<String, List<String>> searchingCriteria) {
            this.view = view;
            this.category = category;
            this.componentsName = componentsName;
            this.searchingCriteria = searchingCriteria;
        }

        @Override
        protected void onPreExecute() {
            String url = "https://wepaws.azurewebsites.net/webservice1.asmx/";
            for (int i = 0; i < componentsName.length - 1; i++) {
                url += String.format("%s=%s", componentsName[i], searchingCriteria.get(i));
                if(i != componentsName.length -1){
                    url += "/";
                }
            }
            queryUrl = url;
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
                conn.setConnectTimeout(5000);
                //Set read connection time out of 2.5 seconds
                conn.setReadTimeout(2500);
                //Set HTTP request method
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                //Perform network request
                conn.connect();

                //After the network response, retrieve the input stream
                inStream = conn.getInputStream();
                //Convert the input stream to String Bitmap
                data = utility.readStream(inStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
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

            switch (category) {
/*                case 1:
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
                    break;*/
            }
/*            ResultAdapter resultAdapter = new ResultAdapter(this.getContext(), componentNames);
            listView.setAdapter(resultAdapter);*/

            // *** Uncomment these if needed ***
//            ResultAdapter resultAdapter = new ResultAdapter(this.getContext(), results);
//            listView.setAdapter(resultAdapter);
            super.onPostExecute(a);
        }
    }
}