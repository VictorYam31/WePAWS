package com.victoryam.wepaws;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SearchFragment extends Fragment {

    private int viewId;

    public SearchFragment(int viewId) {
        this.viewId = viewId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;

        switch (this.viewId) {
            case R.id.home_menu_vet_btn:
                Log.v("test create", "creating search vet fragment");
                view = inflater.inflate(R.layout.search_vet, container, false);
                break;
            case R.id.home_menu_store_btn:
                view = inflater.inflate(R.layout.search_vet, container, false);
                break;
            case R.id.home_menu_dining_btn:
                view = inflater.inflate(R.layout.search_vet, container, false);
                break;
            case R.id.home_menu_park_btn:
                view = inflater.inflate(R.layout.search_vet, container, false);
                break;
        }

        return view;
    }
}
