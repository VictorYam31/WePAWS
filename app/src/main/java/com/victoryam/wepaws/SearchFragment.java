package com.victoryam.wepaws;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_by_category, container, false);
//        Log.v("SearchFragmentArg", String.valueOf(getArguments().getInt("SearchFragmentArg")));

        switch (getArguments().getInt("SearchFragmentArg")) {
            case R.id.home_menu_vet_btn:
//                Log.v("test create", "creating search vet fragment");
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.home_menu_search_vet));
                initComponents(view, getResources().getStringArray(R.array.search_vet_component_names));
                break;
            case R.id.home_menu_store_btn:
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.home_menu_search_store));
                initComponents(view, getResources().getStringArray(R.array.search_store_component_names));
                break;
            case R.id.home_menu_dining_btn:
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.home_menu_search_dining));
                initComponents(view, getResources().getStringArray(R.array.search_dining_component_names));
                break;
            case R.id.home_menu_park_btn:
                ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.home_menu_search_park));
                initComponents(view, getResources().getStringArray(R.array.search_park_component_names));
                break;
        }

        return view;
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
