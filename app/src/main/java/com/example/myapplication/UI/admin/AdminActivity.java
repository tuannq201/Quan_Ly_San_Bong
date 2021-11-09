package com.example.myapplication.UI.admin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.ADadapter.AdminAdapter;
import com.example.myapplication.R;
import com.example.myapplication.UI.nguoithue.SanDaThueFragment;
import com.example.myapplication.UI.nguoithue.SanFragment;
import com.example.myapplication.UI.nguoithue.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class AdminActivity extends AppCompatActivity {

    // Màn hình của Admin.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        loadFragment(new ChuSanFragment());
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bnv_admin);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.nav_chu_san_admin:
                        fragment = new ChuSanFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_nguoi_thue_admin:
                        fragment = new NguoiThueFragment();
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
        transaction.replace(R.id.cst_admin, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }


}