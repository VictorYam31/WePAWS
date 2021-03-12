package com.victoryam.wepaws;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.victoryam.wepaws.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchFragment extends Fragment {
    private ComponentAdapter componentAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_by_category, container, false);
        int categoryId = -1;
        String[] componentsName ={};
        HashMap<Integer, List<String>> searchingCriteria = new HashMap<>();

        switch (getArguments().getInt("SearchFragmentArg")) {
            case R.id.home_menu_clinic_btn:
                categoryId = 1;
                componentsName = getResources().getStringArray(R.array.search_clinic_component_names);
                break;
            case R.id.home_menu_store_btn:
                categoryId = 2;
                componentsName = getResources().getStringArray(R.array.search_store_component_names);
                break;
            case R.id.home_menu_dining_btn:
                categoryId = 3;
                componentsName = getResources().getStringArray(R.array.search_dining_component_names);
                break;
            case R.id.home_menu_park_btn:
                categoryId = 4;
                componentsName = getResources().getStringArray(R.array.search_park_component_names);
                break;
        }

        initComponents(view, categoryId, componentsName);
        Button searchBtn = (Button) view.findViewById(R.id.search_by_category_button);
        searchBtn.setOnClickListener(new onSearchButtonClicked(categoryId, searchingCriteria));

        NavController navController = NavHostFragment.findNavController(this);
        MutableLiveData<List<String>> liveData = navController.getCurrentBackStackEntry().getSavedStateHandle().getLiveData("key");
        liveData.observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> l) {
                int position = Integer.parseInt(l.get(0));
                List<String> selectedItems = new ArrayList<String>(l);
                selectedItems.remove(0);
                searchingCriteria.put(position, selectedItems);
                componentAdapter.updateItem(position, selectedItems.toString());
            }
        });

        return view;
    }

    private class onSearchButtonClicked implements  View.OnClickListener {
        private int categoryId;
        private HashMap<Integer, List<String>> searchingCriteria;

        private onSearchButtonClicked(int categoryId, HashMap<Integer, List<String>> searchingCriteria){
            this.categoryId = categoryId;
            this.searchingCriteria = searchingCriteria;
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", categoryId);
            bundle.putSerializable("SearchingCriteria",searchingCriteria);
            Navigation.findNavController(v).navigate(R.id.action_SearchFragment_to_ResultFragment, bundle);
        }
    }

    private void initComponents(View view, int categoryId, String[] componentNames) {
        ListView listView = (ListView)view.findViewById(R.id.search_by_category_listview);
        componentAdapter = new ComponentAdapter(this.getContext(), categoryId, componentNames);
        listView.setAdapter(componentAdapter);
    }

    public class ComponentAdapter extends BaseAdapter {

        private Context mContext;
        private int categoryId;
        private String[] componentNames;
        private String[] selectedItems;

        public ComponentAdapter(Context c, int categoryId, String[] componentNames) {
            this.mContext = c;
            this.categoryId = categoryId;
            this.componentNames = componentNames;
            this.selectedItems = new String[componentNames.length];
        }

        @Override
        public int getItemViewType(int position) {
            // if (componentNames[position].equals(getResources().getString(R.string.search_component_opening_hours))) {
            //     return 1;
            // }
            // else {
            return 0;
            //}
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public int getCount() {
            return componentNames.length;
        }

        @Override
        public Object getItem(int position) {
            return selectedItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            int type = this.getItemViewType(position);
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.search_component_0, null);
            TextView componentName = (TextView) view.findViewById(R.id.search_component_0_name);
            Button componentBtn = (Button) view.findViewById(R.id.search_component_0_btn);
            TextView selectedItem = (TextView) view.findViewById(R.id.search_component_0_selection);
            componentName.setText(this.componentNames[position]);
            componentBtn.setOnClickListener(new openDialog(type, this.componentNames[position], position));
            if (selectedItems[position] != null) {
                selectedItem.setText(selectedItems[position]);
            }

            return view;
        }

        public void updateItem(int position, String s) {
            this.selectedItems[position] = s;
            notifyDataSetChanged();
        }
    }

    private class openDialog implements View.OnClickListener {

        private final int type;
        private final String componentName;
        private final int position;

        public openDialog(int type, String componentName, int position) {
            this.type = type;
            this.componentName = componentName;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putString("SearchDialogComponent", this.componentName);      // Used by dialog to figure out which string array to show
            bundle.putInt("ReturnPosition", this.position);     // Used to update component at a specific position when something is returned from the dialog
            switch (type) {
                case 0:
                    bundle.putInt("SearchDialogType", 0);   //  Normal -> Multiple Choices Dialog
                    break;
                // case 1:
                //     bundle.putInt("SearchDialogType", 1);   //  Opening Hours -> TimePicker Dialog
                //     break;
            }

            Navigation.findNavController(v).navigate(R.id.action_SearchFragment_to_SearchDialog, bundle);
        }
    }

}
