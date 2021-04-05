package com.victoryam.wepaws;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SearchFragment extends Fragment {
    private HashMap<Integer, List<String>> searchingCriteria;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_by_category, container, false);
        int categoryId = -1;
        String[] componentsName = {};
        searchingCriteria = new HashMap<>();

        String title = getResources().getString(R.string.search_component_search);
        switch (getArguments().getInt("SearchFragmentArg")) {
            case 0:
                categoryId = 1;
                componentsName = getResources().getStringArray(R.array.search_clinic_component_names_display);
                title += (" " + getResources().getString(R.string.home_menu_clinic));
                break;
            case 1:
                categoryId = 2;
                componentsName = getResources().getStringArray(R.array.search_store_component_names_display);
                title += (" " + getResources().getString(R.string.home_menu_store));
                break;
            case 2:
                categoryId = 3;
                componentsName = getResources().getStringArray(R.array.search_dining_component_names_display);
                title += (" " + getResources().getString(R.string.home_menu_dining));
                break;
            case 3:
                categoryId = 4;
                componentsName = getResources().getStringArray(R.array.search_park_component_names_display);
                title += (" " + getResources().getString(R.string.home_menu_park));
                break;
        }
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);

        EditText searchByCategoryKeywords = (EditText) view.findViewById(R.id.search_by_category_keywords);
        if (categoryId != -1) {
            searchByCategoryKeywords.setVisibility(View.GONE);
        }

        initExpandableComponents(view, categoryId, componentsName);
        TextView searchBtn = (TextView) view.findViewById(R.id.search_by_category_button);
        searchBtn.setOnClickListener(new onSearchButtonClicked(categoryId, searchingCriteria));

        return view;
    }

    private class onSearchButtonClicked implements View.OnClickListener {
        private int categoryId;
        private HashMap<Integer, List<String>> searchingCriteria;

        private onSearchButtonClicked(int categoryId, HashMap<Integer, List<String>> searchingCriteria) {
            this.categoryId = categoryId;
            this.searchingCriteria = searchingCriteria;
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt("CategoryId", categoryId);
            bundle.putSerializable("SearchingCriteria", searchingCriteria);
            Navigation.findNavController(v).navigate(R.id.action_SearchFragment_to_ResultFragment, bundle);
        }
    }

    private void initExpandableComponents(View view, int categoryId, String[] firstLevel) {
        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.search_by_category_expandablelistview);
        expandableListView.setGroupIndicator(null);
        List<String[]> secondLevel = new ArrayList<>();
        List<LinkedHashMap<String, String[]>> thirdLevel = new ArrayList<>();
        for (String firstLevelComponent : firstLevel) {
            if (firstLevelComponent.equals(getResources().getString(R.string.search_component_district_display))) {
                secondLevel.add(getResources().getStringArray(R.array.hk_districts));
                LinkedHashMap<String, String[]> thirdLevelData = new LinkedHashMap<>();
                thirdLevelData.put(getResources().getString(R.string.hk_district_Hong_Kong_Island), getResources().getStringArray(R.array.hk_district_Hong_Kong_Island));
                thirdLevelData.put(getResources().getString(R.string.hk_district_Kowloon), getResources().getStringArray(R.array.hk_district_Kowloon));
                thirdLevelData.put(getResources().getString(R.string.hk_district_New_Territories), getResources().getStringArray(R.array.hk_district_New_Territories));
                thirdLevel.add(thirdLevelData);
            } else {
                secondLevel.add(null);
                thirdLevel.add(null);
            }
        }
        ExpandableComponentAdapter expandableComponentAdapter = new ExpandableComponentAdapter(this.getContext(), firstLevel, secondLevel, thirdLevel);
        expandableListView.setAdapter(expandableComponentAdapter);
    }

    public class ExpandableComponentAdapter extends BaseExpandableListAdapter {

        private Context mContext;
        private String[] firstLevel;    // [Name, District, Overnight]
        private List<String[]> secondLevel;     // [], [HK Island, Kowloon, NT], []
        private List<LinkedHashMap<String, String[]>> thirdLevel;       // null, Key: HK Island, Value: [Central, Sha Tin, ...], null
        private List<String> checkboxOptions;

        private String input;

        public ExpandableComponentAdapter(Context c, String[] firstLevel, List<String[]> secondLevel, List<LinkedHashMap<String, String[]>> thirdLevel) {
            this.mContext = c;
            this.firstLevel = firstLevel;
            this.secondLevel = secondLevel;
            this.thirdLevel = thirdLevel;
            checkboxOptions = new ArrayList();
        }

        @Override
        public int getGroupCount() {
            return firstLevel.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return 1;
        }

        @Override
        public Object getGroup(int i) {
            return secondLevel.get(i);
        }

        @Override
        public Object getChild(int i, int i1) {
            return thirdLevel.get(i).get(i1);
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // maybe separate into different layouts, maybe not
            if (firstLevel[i].equals(getResources().getString(R.string.search_component_district_display))) {
                view = inflater.inflate(R.layout.search_component_1, null);
                TextView componentName = (TextView) view.findViewById(R.id.search_component_1_name);
                Switch switchButton = (Switch) view.findViewById(R.id.search_component_1_switch);
                componentName.setText(firstLevel[i]);
                switchButton.setVisibility(View.INVISIBLE);
                switchButton.setFocusable(false);
            } else if (firstLevel[i].equals(getResources().getString(R.string.search_component_name_display))) {
                view = inflater.inflate(R.layout.search_component_2, null);
                EditText editText = (EditText) view.findViewById(R.id.search_component_2_edittext);
                if (input != null) {
                    editText.setText(input);
                }
                editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (!b) {
                            Log.v("edittext is", String.valueOf(editText.getText()));
                            input = String.valueOf(editText.getText());
                            editText.setText(input);
                            Log.v("input is", input);
                            searchingCriteria.put(i, new ArrayList() {{
                                add(input);
                            }});
                        }
                    }
                });
            } else {
                view = inflater.inflate(R.layout.search_component_1, null);
                TextView componentName = (TextView) view.findViewById(R.id.search_component_1_name);
                Switch componentSwitch = (Switch) view.findViewById(R.id.search_component_1_switch);
                componentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            searchingCriteria.put(i, new ArrayList() {{
                                add("Y");
                            }});
                        } else {
                            searchingCriteria.put(i, new ArrayList() {{
                                add("N");
                            }});
                        }
                    }
                });
                componentName.setText(firstLevel[i]);
            }
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            final SecondLevelExpandableListView secondLevelExpandableListView = new SecondLevelExpandableListView(mContext);
            String[] secondLevelComponents = secondLevel.get(i);
            List<String[]> thirdLevelData = new ArrayList<>();
            HashMap<String, String[]> secondToThirdLevelMap = thirdLevel.get(i);
            for (String key : secondToThirdLevelMap.keySet()) {
                thirdLevelData.add(secondToThirdLevelMap.get(key));
            }
            secondLevelExpandableListView.setAdapter(new SecondLevelComponentAdapter(mContext, i, checkboxOptions, secondLevelComponents, thirdLevelData));
            secondLevelExpandableListView.setGroupIndicator(null);
            secondLevelExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                int previousGroup = -1;

                @Override
                public void onGroupExpand(int groupPosition) {
                    if (groupPosition != previousGroup)
                        secondLevelExpandableListView.collapseGroup(previousGroup);
                    previousGroup = groupPosition;
                }
            });


            return secondLevelExpandableListView;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }

    public class SecondLevelExpandableListView extends ExpandableListView {

        public SecondLevelExpandableListView(Context c) {
            super(c);
        }

        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public class SecondLevelComponentAdapter extends BaseExpandableListAdapter {

        private Context mContext;
        int parentGroupPosition;
        String[] headers;
        List<String[]> data;
        List<String> checkboxOptions;

        public SecondLevelComponentAdapter(Context c, int parentGroupPosition, List<String> checkboxOptions, String[] headers, List<String[]> data) {
            this.mContext = c;
            this.headers = headers;
            this.data = data;
            this.parentGroupPosition = parentGroupPosition;
            this.checkboxOptions = checkboxOptions;
        }

        @Override
        public int getGroupCount() {
            return headers.length;
        }

        @Override
        public int getChildrenCount(int i) {
            return data.get(i).length;
        }

        @Override
        public Object getGroup(int i) {
            return headers[i];
        }

        @Override
        public Object getChild(int i, int i1) {
            return data.get(i)[i1];
        }

        @Override
        public long getGroupId(int i) {
            return i;
        }

        @Override
        public long getChildId(int i, int i1) {
            return i1;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.second_level, null);
            TextView secondLevelTextView = (TextView) view.findViewById(R.id.second_level_textview);
            secondLevelTextView.setText(getGroup(i).toString());
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.search_component_3, null);
            String[] childArray = data.get(i);

            TextView thirdLevelTextView = (TextView) view.findViewById(R.id.search_component_3_name);
            final CheckBox thirdLevelCheckBox = (CheckBox) view.findViewById(R.id.search_component_3_checkbox);
            thirdLevelCheckBox.setChecked(checkboxOptions.contains(childArray[i1]) ? true : false);
            thirdLevelCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (thirdLevelCheckBox.isChecked()) {
                        checkboxOptions.add(childArray[i1]);
                        if (searchingCriteria.containsKey(parentGroupPosition)) {
                            if (searchingCriteria.get(parentGroupPosition).contains(childArray[i1])) {
                                return;
                            } else {
                                searchingCriteria.get(parentGroupPosition).add(childArray[i1]);
                            }
                        } else {
                            searchingCriteria.put(parentGroupPosition, new ArrayList() {{
                                add(childArray[i1]);
                            }});
                        }
                    } else {
                        checkboxOptions.remove(childArray[i1]);
                        if (searchingCriteria.containsKey(parentGroupPosition)) {
                            searchingCriteria.get(parentGroupPosition).remove(childArray[i1]);
                        }
                    }
                }
            });
            thirdLevelTextView.setText(childArray[i1]);
            thirdLevelTextView.setTextSize(15);
            thirdLevelTextView.setPadding(100, 0, 0, 0);
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return true;
        }
    }
}
