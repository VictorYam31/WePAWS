package com.victoryam.wepaws;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
<<<<<<< HEAD
import android.widget.AdapterView;
=======
>>>>>>> b49f8dee9d1c6551c514f026dd3ecc8d8642f229
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.victoryam.wepaws.Domain.Category;
import com.victoryam.wepaws.Domain.PetSpecies;
import com.victoryam.wepaws.Domain.VetMaster;
import com.victoryam.wepaws.Utils.Utility;

<<<<<<< HEAD
=======
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

>>>>>>> b49f8dee9d1c6551c514f026dd3ecc8d8642f229
public class ResultFragment extends Fragment {
    Utility utility;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result, container, false);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            String test = bundle.getString("test");
        }

        utility = new Utility();

//        dummy
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
//

        //initResults(view, results);

        return view;
    }

    private void initResults(View view, Result[] results) {
        ListView listView = (ListView)view.findViewById(R.id.result_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
                Result result = (Result) adapterView.getItemAtPosition(position);
                Navigation.findNavController(view).navigate(R.id.action_ResultFragment_to_ResultDetailFragment);
            }
        });

        ResultAdapter resultAdapter = new ResultAdapter(this.getContext(), results);
        listView.setAdapter(resultAdapter);
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

    private class initResults extends AsyncTask<String, Integer, String> {
        View view;
        String param;
        String data;

        initResults(View view, String param) {
            this.view = view;
            this.param = param;
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection conn = null;
            InputStream inStream;

            try {
                //Create a URL Connection object and set its parameters
                URL url = new URL(params[0]);
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
            ResultAdapter resultAdapter = new ResultAdapter(this.getContext(), results);
            listView.setAdapter(resultAdapter);
            super.onPostExecute(a);
        }
    }


//    class ResultAdapter extends ArrayAdapter<String> {
//        Context context;
//        Result[] results;
//
//        ResultAdapter(Context c, Result[] results) {
//            super(c, R.layout.result_display, R.id.search_component_name, componentItems);
//            this.context = c;
//            this.results = results;
//        }
//
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//            View view = convertView;
//            if (view == null) {
//                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                view = inflater.inflate(R.layout.search_component, null);
//            }
//
//            TextView textView = (TextView) view.findViewById(R.id.search_component_name);
//            Spinner spinner = (Spinner) view.findViewById(R.id.search_component_spinner);
//
//            textView.setText(this.componentItems[position]);
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.context, R.layout.preference_spinner_item, this.componentSpinnerItems);
//            spinner.setAdapter(adapter);
//            return view;
//        }
//    }
}