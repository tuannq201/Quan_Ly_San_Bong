package com.example.myapplication.UI.chusan;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChuSanActivity extends AppCompatActivity {

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_san);

        fab = findViewById(R.id.fab);
        loadFragment(new ListSanFragment());
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.nav_bottom_chu_san);
        navigationView.setBackground(null);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment;
                switch (item.getItemId()){
                    case R.id.nav_chon_san_cs:
                        fragment = new ListSanFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_ca_nhan:
                        fragment = new ThongKeFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
        fab.setOnClickListener(v -> {
            loadFragment(new AddSanFragment());
        });

    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

}