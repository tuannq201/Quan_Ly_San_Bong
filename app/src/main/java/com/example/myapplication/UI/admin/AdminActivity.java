package com.example.myapplication.UI.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapter.AdminAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class AdminActivity extends AppCompatActivity {

    // Màn hình của Admin.

    ViewPager2 vpAdmin;
    TabLayout tlAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        vpAdmin = findViewById(R.id.vpAdmin);
        tlAdmin = findViewById(R.id.tlAdmin);

        AdminAdapter adapter = new AdminAdapter(AdminActivity.this);
        vpAdmin.setAdapter(adapter);

        new TabLayoutMediator(tlAdmin, vpAdmin, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position==0){
//                    tab.setIcon(R.drawable....);
                    tab.setText("Chủ Sân");
                }else{
//                    tab.setIcon(R.drawable....);
                    tab.setText("Người Thuê");
                }
            }
        }).attach();


    }


}