package com.victoryam.wepaws;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        Button vetButton = (Button) view.findViewById(R.id.home_menu_vet_btn);
        vetButton.setOnClickListener(new onButtonClicked());

        Button storeButton = (Button) view.findViewById(R.id.home_menu_store_btn);
        storeButton.setOnClickListener(new onButtonClicked());

        Button diningButton = (Button) view.findViewById(R.id.home_menu_dining_btn);
        diningButton.setOnClickListener(new onButtonClicked());

        Button parkButton = (Button) view.findViewById(R.id.home_menu_park_btn);
        parkButton.setOnClickListener(new onButtonClicked());

        return view;
    }

    private class onButtonClicked implements  View.OnClickListener {
        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            bundle.putInt("SearchFragmentArg", v.getId());
            Navigation.findNavController(v).navigate(R.id.action_HomeFragment_to_SearchFragment, bundle);
        }
    }

}
