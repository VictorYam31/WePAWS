package com.victoryam.wepaws;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.victoryam.wepaws.Domain.Animal;
import com.victoryam.wepaws.Domain.Category;
import com.victoryam.wepaws.Domain.Vet;

import org.w3c.dom.Text;

public class ResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.result, container, false);

//        dummy
        Vet vet1 = new Vet();
        vet1.setVetName("Tom");
        vet1.setVetAdd(getResources().getString(R.string.hk_district_Kwun_Tong));
        Animal animal1 = new Animal();
        animal1.setAnimalName(getResources().getString(R.string.animal_type_amphibian));
        Category cat1 = new Category();
        cat1.setDesc("Category description for Tom");
        Result result1 = new Result(vet1, animal1, cat1, 4.5f);

        Vet vet2 = new Vet();
        vet2.setVetName("John");
        vet2.setVetAdd(getResources().getString(R.string.hk_district_Kowloon));
        Animal animal2 = new Animal();
        animal2.setAnimalName(getResources().getString(R.string.animal_type_fish));
        Category cat2 = new Category();
        cat2.setDesc("Category description for John");
        Result result2 = new Result(vet2, animal2, cat2, 2f);

        Vet vet3 = new Vet();
        vet3.setVetName("Ken");
        vet3.setVetAdd(getResources().getString(R.string.hk_district_Hong_Kong_Island));
        Animal animal3 = new Animal();
        animal3.setAnimalName(getResources().getString(R.string.animal_type_insect));
        Category cat3 = new Category();
        cat3.setDesc("Category description for Ken");
        Result result3 = new Result(vet3, animal3, cat3, 3.5f);

        Result[] results = {result1, result2, result3};
//

        initResults(view, results);

        return view;
    }

    private void initResults(View view, Result[] results) {
        ListView listView = (ListView)view.findViewById(R.id.result_listview);

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
            return position;
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
            resultAddress.setText(this.results[position].getVet().getVetAdd());
            resultAnimal.setText(this.results[position].getAnimal().getAnimalName());
            resultCategory.setText(this.results[position].getCategory().getDesc());
            resultRating.setRating(this.results[position].getRating());

            return view;
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