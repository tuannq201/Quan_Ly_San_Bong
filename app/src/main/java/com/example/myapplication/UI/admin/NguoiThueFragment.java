package com.example.myapplication.UI.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class NguoiThueFragment extends Fragment {


    public NguoiThueFragment() {
        // Required empty public constructor
    }

    public static NguoiThueFragment newInstance() {
        NguoiThueFragment fragment = new NguoiThueFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nguoi_thue, container, false);
    }
}