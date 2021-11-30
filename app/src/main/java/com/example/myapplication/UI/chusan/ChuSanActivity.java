package com.example.myapplication.UI.chusan;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_san);

        ChipNavigationBar chipNavigationBar = findViewById(R.id.chip_navi_chu_san);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.nav_trang_chu_cs:
                        fragment = new TongQuanFragment();
                        break;
                    case R.id.nav_san:
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