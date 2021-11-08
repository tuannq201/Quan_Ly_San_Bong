package com.example.myapplication.UI.chusan;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class CaSanFRM extends FragmentActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, new CaSanFragment()).commit();
        }
    }
}
