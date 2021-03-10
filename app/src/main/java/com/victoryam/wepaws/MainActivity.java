package com.victoryam.wepaws;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private Toolbar toolbar;
    private BottomNavigationView navigationBar;

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

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                String title;
                switch (destination.getId()) {
                    case R.id.SearchFragment:
                        switch (arguments.getInt("SearchFragmentArg")) {
                            case R.id.home_menu_clinic_btn:
                                title = getResources().getString(R.string.home_menu_search_vet);
                                break;
                            case R.id.home_menu_store_btn:
                                title = getResources().getString(R.string.home_menu_search_store);
                                break;
                            case R.id.home_menu_dining_btn:
                                title = getResources().getString(R.string.home_menu_search_dining);
                                break;
                            case R.id.home_menu_park_btn:
                                title = getResources().getString(R.string.home_menu_search_park);
                                break;
                            default:
                                title = (String) destination.getLabel();
                        }
                        break;
                    default:
                        title = (String) destination.getLabel();
                }

                if (destination.getId() == R.id.HomeFragment) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                }
                else {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
                getSupportActionBar().setTitle(title);
            }
        });

        navigationBar = findViewById(R.id.navigationbar);
        NavigationUI.setupWithNavController(navigationBar, navController);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public void onBackPressed() {
//        if (!navController.popBackStack()) {
//            finish();
//        }
//    }

}