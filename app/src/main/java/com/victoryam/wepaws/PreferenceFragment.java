package com.victoryam.wepaws;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PreferenceFragment extends Fragment {
    Spinner language_spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preference, container, false);

        language_spinner = view.findViewById(R.id.preference_language_spinner);
        initspinnerfooter();

        return view;
    }

    private void initspinnerfooter() {
        String[] items = new String[]{
                getResources().getString(R.string.preference_language_en), getResources().getString(R.string.preference_language_zh)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.preference_spinner_item, items);
        language_spinner.setAdapter(adapter);
        language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
//                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

}
