package com.example.myapplication.UI.chusan;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.myapplication.R;
import com.example.myapplication.UI.admin.ChuSanFragment;
import com.example.myapplication.UI.admin.NguoiThueFragment;
import com.example.myapplication.UI.nguoithue.SanDaThueFragment;
import com.example.myapplication.UI.nguoithue.SanFragment;
import com.example.myapplication.UI.nguoithue.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class ChuSanActivity extends AppCompatActivity {
    MeowBottomNavigation meowBottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_san);



//        loadFragment(new ChuSanFragment());
//        meowBottomNavigation =(com.etebarian.meowbottomnavigation.MeowBottomNavigation) findViewById(R.id.meo_btn_chusan);
//        meowBottomNavigation.add(new com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home_24));
//        meowBottomNavigation.add(new com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model(2, R.drawable.ic_baseline_insights_24));
//        meowBottomNavigation.add(new com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model(3, R.drawable.ic_baseline_account_circle_24));
//        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
//            @Override
//            public void onShowItem(MeowBottomNavigation.Model item) {
//                Fragment fragment = null;
//                switch (item.getId()){
//                    case 1:
//                        fragment = new ListSanFragment();
//                        break;
//                    case 2:
//                        fragment = new ThongKeFragment();
//                        break;
//                    case 3:
//                        fragment = new CaNhanOfChuSanFragment();
//                        break;
//                }
//                loadFragment(fragment);
//            }
//        });
//        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
//            @Override
//            public void onClickItem(MeowBottomNavigation.Model item) {
//                Fragment fragment = null;
//                switch (item.getId()){
//                    case 1:
//                        fragment = new ListSanFragment();
//                        break;
//                    case 2:
//                        fragment = new ThongKeFragment();
//                        break;
//                    case 3:
//                        fragment = new CaNhanOfChuSanFragment();
//                        break;
//                }
//                loadFragment(fragment);
//            }
//        });
//
//        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
//            @Override
//            public void onReselectItem(MeowBottomNavigation.Model item) {
//                Fragment fragment = null;
//                switch (item.getId()){
//                    case 1:
//                        fragment = new ListSanFragment();
//                        break;
//                    case 2:
//                        fragment = new ThongKeFragment();
//                        break;
//                    case 3:
//                        fragment = new CaNhanOfChuSanFragment();
//                        break;
//                }
//                loadFragment(fragment);
//            }
//        });
//        meowBottomNavigation.show(1, true);

        ChipNavigationBar chipNavigationBar = findViewById(R.id.chip_navi_chu_san);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.nav_trang_chu_cs:
                        fragment = new ListSanFragment();
                        break;
                    case R.id.nav_thong_ke_cs:
                        fragment = new ThongKeFragment();
                        break;
                    case R.id.nav_ca_nhan_cs:
                        fragment = new CaNhanOfChuSanFragment();
                        break;

                }
                loadFragment(fragment);
            }
        });
        chipNavigationBar.setItemSelected(R.id.nav_trang_chu_cs, true);
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

}