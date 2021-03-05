package com.victoryam.wepaws;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.victoryam.wepaws.Domain.Category;

public class SearchFragment extends Fragment {
    int CategoryId;
    Button searchBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_by_category, container, false);

        switch (getArguments().getInt("SearchFragmentArg")) {
            case R.id.home_menu_vet_btn:
                CategoryId = 1;
                initComponents(view, getResources().getStringArray(R.array.search_vet_component_names));
                break;
            case R.id.home_menu_store_btn:
                CategoryId = 2;
                initComponents(view, getResources().getStringArray(R.array.search_store_component_names));
                break;
            case R.id.home_menu_dining_btn:
                CategoryId = 3;
                initComponents(view, getResources().getStringArray(R.array.search_dining_component_names));
                break;
            case R.id.home_menu_park_btn:
                CategoryId = 4;
                initComponents(view, getResources().getStringArray(R.array.search_park_component_names));
                break;
        }

        searchBtn = (Button) view.findViewById(R.id.search_by_category_button);
        searchBtn.setOnClickListener(new onSearchButtonClicked());

        return view;
    }

    private class onSearchButtonClicked implements  View.OnClickListener {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", CategoryId);

            Navigation.findNavController(v).navigate(R.id.action_SearchFragment_to_ResultFragment, bundle);
        }
    }

    private void initComponents(View view, String[] componentNames) {
        ListView listView = (ListView)view.findViewById(R.id.search_by_category_listview);

        // Change this later ***
        String[] spinnerItems = new String[]{"Item 1", "Item 2", "Item 3"};

        ComponentAdapter componentAdapter = new ComponentAdapter(this.getContext(), componentNames, spinnerItems);
        listView.setAdapter(componentAdapter);
    }

    class ComponentAdapter extends ArrayAdapter<String> {
        Context context;
        String[] componentItems;
        String[] componentSpinnerItems;

        ComponentAdapter(Context c, String[] componentItems, String[] componentSpinnerItems) {
            super(c, R.layout.search_component, R.id.search_component_name, componentItems);
            this.context = c;
            this.componentItems = componentItems;
            this.componentSpinnerItems = componentSpinnerItems;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.search_component, null);
            }

            TextView textView = (TextView) view.findViewById(R.id.search_component_name);
            Spinner spinner = (Spinner) view.findViewById(R.id.search_component_spinner);

            textView.setText(this.componentItems[position]);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.context, R.layout.preference_spinner_item, this.componentSpinnerItems);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new componentSpinnerOnItemSelected());
            return view;
//            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View component = layoutInflater.inflate(R.layout.search_component, parent, false);
//            TextView componentName = component.findViewById(R.id.search_component_name);
//            componentName.setText(componentItems[position]);
//
//            Spinner componentSpinner = component.findViewById(R.id.search_component_spinner);
//            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this.context, android.R.layout.simple_spinner_item, componentSpinnerItems);
//            componentSpinner.setAdapter(spinnerAdapter);
//            componentSpinner.setOnItemSelectedListener(new ComponentAdapter.componentSpinnerOnItemSelected());
//
//            Spinner componentSpinner = (Spinner) component.findViewById(R.id.search_component_spinner);
//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
//                    getContext(),
//                    getResources().getStringArray(this.componentSpinnerItems),
//                    android.R.layout.simple_spinner_item);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
//
//            return component;
        }

        private class componentSpinnerOnItemSelected implements AdapterView.OnItemSelectedListener {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
//                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        }
    }

}
