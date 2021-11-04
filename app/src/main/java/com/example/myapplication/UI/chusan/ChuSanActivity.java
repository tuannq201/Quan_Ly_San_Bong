package com.example.myapplication.UI.chusan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.example.myapplication.UI.SanFragment;
import com.example.myapplication.UI.nguoithue.SanDaThueFragment;
import com.example.myapplication.UI.nguoithue.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChuSanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_san);

        Fragment fragment = new ListSanFragment();
        loadFragment(fragment);
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bnv_chu_san);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.nav_chon_san_cs:
                        fragment = new ListSanFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_thong_ke:
                        fragment = new ThongKeFragment();
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
        transaction.replace(R.id.cst_chu_san, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}