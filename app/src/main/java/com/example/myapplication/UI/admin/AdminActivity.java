package com.example.myapplication.UI.admin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.myapplication.ADadapter.AdminAdapter;
import com.example.myapplication.R;



public class AdminActivity extends AppCompatActivity {

    MeowBottomNavigation meowBottomNavigation;

    // Màn hình của Admin.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        loadFragment(new ChuSanFragment());
        meowBottomNavigation =(com.etebarian.meowbottomnavigation.MeowBottomNavigation) findViewById(R.id.meo_bvn_admin);
        meowBottomNavigation.add(new com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model(1, R.drawable.chu_san));
        meowBottomNavigation.add(new com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model(2, R.drawable.nguoi_thue));
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new ChuSanFragment();
                        break;
                    case 2:
                        fragment = new NguoiThueFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new ChuSanFragment();
                        break;
                    case 2:
                        fragment = new NguoiThueFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });

        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                switch (item.getId()){
                    case 1:
                        fragment = new ChuSanFragment();
                        break;
                    case 2:
                        fragment = new NguoiThueFragment();
                        break;
                }
                loadFragment(fragment);
            }
        });
        meowBottomNavigation.show(1, true);
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_admin, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }


}