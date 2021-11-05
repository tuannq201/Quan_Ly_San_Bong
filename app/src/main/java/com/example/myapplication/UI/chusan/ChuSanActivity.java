package com.example.myapplication.UI.chusan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.myapplication.R;
import com.example.myapplication.UI.BottomNavigationBehavior;
import com.example.myapplication.UI.nguoithue.SanDaThueFragment;
import com.example.myapplication.UI.nguoithue.SanFragment;
import com.example.myapplication.UI.nguoithue.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ChuSanActivity extends AppCompatActivity {

//    private AHBottomNavigation ahBottomNavigation;
////    private Fragment fragment;
//    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_san);

//        ahBottomNavigation = findViewById(R.id.bottom_navigation);
//        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_baseline_home_24, R.color.color_tab_1);
//        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_baseline_add_24, R.color.color_tab_2);
//        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_baseline_account_circle_24, R.color.color_tab_3);
//// Add items
//        ahBottomNavigation.addItem(item1);
//        ahBottomNavigation.addItem(item2);
//        ahBottomNavigation.addItem(item3);
//
//        ahBottomNavigation.setColored(true);
//        toolbar = getSupportActionBar();
//        toolbar.setTitle("Sân bóng");
        loadFragment(new ListSanFragment());
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.nav_bottom_chu_san);
        navigationView.setBackground(null);
//        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigationView.getLayoutParams();
//        layoutParams.setBehavior(new BottomNavigationBehavior());
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment;
                switch (item.getItemId()){
                    case R.id.nav_chon_san_cs:
//                        toolbar.setTitle("Sân bóng");
                        fragment = new ListSanFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_thong_ke:
//                        toolbar.setTitle("Cá nhân");
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
        transaction.replace(R.id.frame_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}