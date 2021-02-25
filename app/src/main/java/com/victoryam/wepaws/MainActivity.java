package com.victoryam.wepaws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainframe, new HomeFragment()).commit();

        BottomNavigationView navigationbar = findViewById(R.id.navigationbar);
        navigationbar.setOnNavigationItemSelectedListener(navListener);
    }

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