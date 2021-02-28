package com.victoryam.wepaws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

//    fragment finish()?

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, new HomeFragment()).commit();

        BottomNavigationView navigationbar = findViewById(R.id.navigationbar);
        navigationbar.setOnNavigationItemSelectedListener(navListener);
    }

//    old
//    public void homeSearch(View view) {
//        int viewId = view.getId();
//        Fragment focus = new SearchFragment();
//        Bundle args = new Bundle();
//        args.putInt("viewId", viewId);
//        focus.setArguments(args);
//
//        switch(viewId) {
//            case R.id.home_menu_vet_btn:
//                Log.v("button test", "pressed vet button");
//                getSupportActionBar().setTitle(R.string.home_menu_search_vet);
//                break;
//            case R.id.home_menu_store_btn:
//                getSupportActionBar().setTitle(R.string.home_menu_search_store);
//                break;
//            case R.id.home_menu_dining_btn:
//                getSupportActionBar().setTitle(R.string.home_menu_search_dining);
//                break;
//            case R.id.home_menu_park_btn:
//                getSupportActionBar().setTitle(R.string.home_menu_search_park);
//                break;
//        }
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, focus).commit();
//    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment focus = null;

                    switch(item.getItemId()) {
                        case R.id.nav_menu_home:
                            getSupportActionBar().setTitle(R.string.app_name);
                            focus = new HomeFragment();
                            break;
                        case R.id.nav_menu_search:
                            break;
                        case R.id.nav_menu_preference:
                            getSupportActionBar().setTitle(R.string.nav_menu_preference);
                            focus = new PreferenceFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, focus).commit();

                    return true;
                }
            };

}