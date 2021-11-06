package com.example.myapplication.UI.chusan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ListSanAdapter;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;

import java.util.ArrayList;
import java.util.List;


public class ListSanFragment extends Fragment {

    RecyclerView rcvSan;
    SanDAO dao;
    List<San> listSan;
    ListSanAdapter adapter;

    public ListSanFragment() {
    }

    public static ListSanFragment newInstance(String param1, String param2) {
        ListSanFragment fragment = new ListSanFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_san, container, false);
        rcvSan = view.findViewById(R.id.list_san_chu_san);
        dao = new SanDAO(getActivity());
        updateLV();
        return view;
    }
    public void updateLV(){
        listSan = (ArrayList<San>) dao.getAll();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcvSan.setLayoutManager(mLayoutManager);
        rcvSan.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListSanAdapter(getActivity(), listSan, new ITFOnItenClick() {
            @Override
            public void onItemClick(San san) {

            }

            @Override
            public void onItemClick(PhieuThue phieuThue) {

            }
        });
        rcvSan.setAdapter(adapter);
    }
}