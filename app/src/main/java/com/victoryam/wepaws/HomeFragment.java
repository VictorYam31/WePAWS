package com.victoryam.wepaws;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment {

    RecyclerView homeRecyclerView;
    HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home, container, false);

        homeRecyclerView = (RecyclerView) view.findViewById(R.id.homeRecyclerView);
        homeRecyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        homeAdapter = new HomeAdapter(this.getContext(), this.getHomeItems());
        homeRecyclerView.setAdapter(homeAdapter);

        SearchView searchView = (SearchView) view.findViewById(R.id.home_menu_search);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = (TextView) searchView.findViewById(id);
        textView.setTypeface(ResourcesCompat.getFont(getContext(), R.font.maian));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                Bundle bundle = new Bundle();
                int categoryId = 0;
                HashMap<Integer, List<String>> searchingCriteria = new HashMap<>();
                searchingCriteria.put(0, new ArrayList() {{
                    add(query);
                }});

                bundle.putInt("CategoryId", categoryId);
                bundle.putSerializable("SearchingCriteria", searchingCriteria);

                Navigation.findNavController(view).navigate(R.id.action_HomeFragment_to_ResultFragment, bundle);
                return false;
            }

        });

        return view;
    }

    private class onButtonClicked implements View.OnClickListener {
        int position;

        private onButtonClicked(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (this.position == 3) {
                Navigation.findNavController(v).navigate(R.id.action_HomeFragment_to_SearchDialog);
            }
            else {
                Bundle bundle = new Bundle();
                bundle.putInt("SearchFragmentArg", position);
                Navigation.findNavController(v).navigate(R.id.action_HomeFragment_to_SearchFragment, bundle);
            }
        }
    }

    public class HomeAdapter extends RecyclerView.Adapter<HomeHolder> {
        Context c;
        ArrayList<HomeModel> models;

        public HomeAdapter(Context c, ArrayList<HomeModel> models) {
            this.c = c;
            this.models = models;
        }

        @NonNull
        @Override
        public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, null);
            return new HomeHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
            holder.homeItemName.setText(models.get(position).getItemName());
            holder.homeItemImageView.setImageResource(models.get(position).getImage());
            holder.homeRelativeLayout.setOnClickListener(new onButtonClicked(position));
        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }

    private ArrayList<HomeModel> getHomeItems() {
        final String[] itemNames = getResources().getStringArray(R.array.home_menu_items);
        final TypedArray itemImages = getResources().obtainTypedArray(R.array.home_menu_item_images);
        ArrayList<HomeModel> models = new ArrayList<>();
        HomeModel p = new HomeModel();
        for (int i = 0; i < itemNames.length; i++) {
            if (i != 0) {
                p = new HomeModel();
            }
            p.setItemName(itemNames[i]);
            p.setImage(itemImages.getResourceId(i, -1));
            models.add(p);
        }
        return models;
    }
}
