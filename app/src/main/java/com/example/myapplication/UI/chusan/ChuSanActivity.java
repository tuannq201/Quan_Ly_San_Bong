package com.example.myapplication.UI.chusan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.UI.BottomNavigationBehavior;
import com.example.myapplication.UI.nguoithue.SanDaThueFragment;
import com.example.myapplication.UI.nguoithue.SanFragment;
import com.example.myapplication.UI.nguoithue.UserFragment;
import com.example.myapplication.entity.User;
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
                        fragment = new CaSanFragment();
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