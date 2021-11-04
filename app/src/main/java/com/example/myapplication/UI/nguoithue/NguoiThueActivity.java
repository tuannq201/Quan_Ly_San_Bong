package com.example.myapplication.UI.nguoithue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.UI.SanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NguoiThueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_thue);loadFragment(new SanFragment());
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bnv_nguoi_thue);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.nav_chon_san:
                        fragment = new SanFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_da_thue:
                        fragment = new SanDaThueFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.cst_nguoi_thue, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}