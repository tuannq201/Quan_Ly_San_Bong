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


public class NguoiThueActivity extends AppCompatActivity implements SanFragment.ITFsendData {

    PhieuThueDAO phieuThueDAO;

    //MeowBottomNavigation meowBottomNavigation;
    public static com.etebarian.meowbottomnavigation.MeowBottomNavigation meowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_thue);loadFragment(new SanFragment());

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String phone = pref.getString("PHONE","");
        phieuThueDAO = new PhieuThueDAO(NguoiThueActivity.this);

//
//        meowBottomNavigation =(com.etebarian.meowbottomnavigation.MeowBottomNavigation) findViewById(R.id.meo_btn_nguoi_thue);
//        meowBottomNavigation.add(new com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model(1, R.drawable.ic_home));
//        meowBottomNavigation.add(new com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model(2, R.drawable.ic_notification));
//        meowBottomNavigation.add(new com.etebarian.meowbottomnavigation.MeowBottomNavigation.Model(3, R.drawable.ic_man_user));
//
//        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
//            @Override
//            public void onShowItem(MeowBottomNavigation.Model item) {
//                Fragment fragment = null;
//                switch (item.getId()){
//                    case 1:
//                        fragment = new SanFragment();
//                        break;
//                    case 2:
//                        fragment = new SanDaThueFragment();
//                        break;
//                    case 3:
//                        fragment = new UserFragment();
//                        break;
//
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
//                        fragment = new SanFragment();
//                        break;
//                    case 2:
//                        fragment = new SanDaThueFragment();
//                        break;
//                    case 3:
//                        fragment = new UserFragment();
//                        break;
//
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
//                        fragment = new SanFragment();
//                        break;
//                    case 2:
//                        fragment = new SanDaThueFragment();
//                        break;
//                    case 3:
//                        fragment = new UserFragment();
//                        break;
//
//                }
//                loadFragment(fragment);
//            }
//        });
//        meowBottomNavigation.show(1, true);
//        if (phieuThueDAO.getPhieuByUser(phone).size()>0){
//            meowBottomNavigation.setCount(2, String.valueOf(phieuThueDAO.getPhieuByUser(phone).size()));
//        }else {
//            meowBottomNavigation.clearCount(2);
//        }

        ChipNavigationBar chipNavigationBar = findViewById(R.id.chip_navi_nguoi_thue);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.home_nt:
                        chipNavigationBar.showBadge(R.id.notifi_nt, phieuThueDAO.getPhieuByUser(phone).size());
                        fragment = new SanFragment();
                        break;
                    case R.id.notifi_nt:
                        chipNavigationBar.showBadge(R.id.notifi_nt);
                        fragment = new SanDaThueFragment();
                        break;
                    case R.id.user_nt:
                        chipNavigationBar.showBadge(R.id.notifi_nt, phieuThueDAO.getPhieuByUser(phone).size());
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