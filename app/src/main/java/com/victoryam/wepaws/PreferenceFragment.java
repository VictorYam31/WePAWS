package com.victoryam.wepaws;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
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

import java.util.Locale;

public class PreferenceFragment extends Fragment {
    Spinner languageSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preference, container, false);

        languageSpinner = view.findViewById(R.id.preference_language_spinner);
        initSpinnerFooter();

        return view;
    }

    private void initSpinnerFooter() {
        String[] items = new String[]{
                getResources().getString(R.string.preference_language_en), getResources().getString(R.string.preference_language_zh)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.preference_spinner_item, items);
        languageSpinner.setAdapter(adapter);
        languageSpinner.setOnItemSelectedListener(new languageSpinnerOnItemSelected());
    }

    private class languageSpinnerOnItemSelected implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.v("item", (String) parent.getItemAtPosition(position));
//                ((TextView) parent.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
        }

        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    private void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(getActivity(), MainActivity.class);
        startActivity(refresh);
    }

}
