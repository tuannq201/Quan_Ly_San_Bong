package com.example.myapplication.UI.admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.myapplication.R;


public class ChuSanFragment extends Fragment {

    SearchView searchView;
    ImageView imgAdd;
    ListView lv;


    public ChuSanFragment() {
    }

    public static ChuSanFragment newInstance() {
        ChuSanFragment fragment = new ChuSanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        View view = inflater.inflate(R.layout.fragment_chu_san, container, false);
        searchView = view.findViewById(R.id.svChuSan);
        imgAdd = view.findViewById(R.id.imgThemCS);
        lv = view.findViewById(R.id.lvChuSan);
        return view;
    }
}