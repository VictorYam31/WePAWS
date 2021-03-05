package com.victoryam.wepaws;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

//    private class vetButtonClicked implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
////            Toast.makeText(getActivity(), "vetBtn", Toast.LENGTH_LONG).show();
////            Intent intent = new Intent(getApplicationContext(), .class);
////            startActivity(intent);
//            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.home_menu_search_vet);
////            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
////            replaceFragment(v, getResources().getString(R.string.home_menu_search_vet));
//            Bundle bundle = new Bundle();
//            bundle.putInt("SearchFragmentArg", v.getId());
////            Log.v("btn_id", String.valueOf(v.getId()));
//            Navigation.findNavController(v).navigate(R.id.action_HomeFragment_to_SearchFragment, bundle);
//        }
//    }
//
//    private class storeButtonClicked implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
////            Intent intent = new Intent(getApplicationContext(), .class);
////            startActivity(intent);
//            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.home_menu_search_store);
//            replaceFragment(v, getResources().getString(R.string.home_menu_search_store));
//        }
//    }
//
//    private class diningButtonClicked implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
////            Intent intent = new Intent(getApplicationContext(), .class);
////            startActivity(intent);
//            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.home_menu_search_dining);
//            replaceFragment(v, getResources().getString(R.string.home_menu_search_dining));
//        }
//    }
//
//    private class parkButtonClicked implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
////            Intent intent = new Intent(getApplicationContext(), .class);
////            startActivity(intent);
//            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.home_menu_search_park);
//            replaceFragment(v, getResources().getString(R.string.home_menu_search_park));
//        }
//    }
//
//    private void replaceFragment(View v, String fragmentName) {
//        int viewId = v.getId();
//        Fragment focus = new SearchFragment();
//        Bundle args = new Bundle();
//        args.putInt("viewId", viewId);
//        focus.setArguments(args);
////        ((AppCompatActivity)getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, focus).addToBackStack(fragmentName).commit();
//    }

}
