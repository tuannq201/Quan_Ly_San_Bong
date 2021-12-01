package com.example.myapplication.UI.nguoithue;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.myapplication.R;
import com.example.myapplication.UI.chusan.ListSanFragment;
import com.example.myapplication.dao.PhieuThueDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.text.SimpleDateFormat;
import java.util.Date;


public class NguoiThueActivity extends AppCompatActivity implements SanFragment.ITFsendData {

    PhieuThueDAO phieuThueDAO;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    public static int count = 0;
    public static int count1 = 0;
    public static String ddiaiemDC = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_thue);loadFragment(new SanFragment());

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String phone = pref.getString("PHONE","");
        phieuThueDAO = new PhieuThueDAO(NguoiThueActivity.this);


        Date now = new Date();
        ChipNavigationBar chipNavigationBar = findViewById(R.id.chip_navi_nguoi_thue);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.home_nt:
                        chipNavigationBar.showBadge(R.id.notifi_nt, phieuThueDAO.getByDate(phone, format.format(now)));
                        fragment = new SanFragment();
                        break;
                    case R.id.notifi_nt:
                        chipNavigationBar.showBadge(R.id.notifi_nt);
                        fragment = new SanDaThueFragment();
                        break;
                    case R.id.user_nt:
                        chipNavigationBar.showBadge(R.id.notifi_nt, phieuThueDAO.getByDate(phone, format.format(now)));
                        fragment = new UserFragment();
                        break;

                }
                loadFragment(fragment);
            }
        });
        chipNavigationBar.setItemSelected(R.id.home_nt, true);


    }
    public void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_nguoi_thue, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void sendData(int maCumSan) {

        //fragment.updateLV(maCumSan);
        //fragment.getMaCumSan(maCumSan);
        //Toast.makeText(getApplicationContext(), "=============================="+maCumSan, Toast.LENGTH_SHORT).show();
        Bundle bundle = new Bundle();
        bundle.putString("ma", String.valueOf(maCumSan));
        //fragment.setArguments(bundle);

        SanCumSanFragment fragment = new SanCumSanFragment(maCumSan);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frm_nguoi_thue, fragment);
        transaction.addToBackStack(null);

        transaction.commit();


        //fragment.updateLV(maCumSan);
    }
}