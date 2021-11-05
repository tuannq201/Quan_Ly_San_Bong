package com.example.myapplication.ADadapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.UI.admin.ChuSanFragment;
import com.example.myapplication.UI.admin.NguoiThueFragment;

public class AdminAdapter extends FragmentStateAdapter {

    public AdminAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position == 0 ){
            fragment = ChuSanFragment.newInstance();
        }else {
            fragment = NguoiThueFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
