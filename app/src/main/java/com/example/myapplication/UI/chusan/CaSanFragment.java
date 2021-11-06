package com.example.myapplication.UI.chusan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CaSanAdapter;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.PhieuThue;

import java.util.ArrayList;
import java.util.List;


public class CaSanFragment extends Fragment {
    PhieuThueDAO dao;
    List<PhieuThue> list;
    CaSanAdapter adapter;
    GridView gridView;
    public CaSanFragment() {

    }

    public static CaSanFragment newInstance(String param1, String param2) {
        CaSanFragment fragment = new CaSanFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ca_san, container, false);
        gridView = view.findViewById(R.id.grCaSan);
        dao = new PhieuThueDAO(getActivity());
        list = (ArrayList<PhieuThue>) dao.getAll();
        adapter = new CaSanAdapter(getActivity(),this,list);
        gridView.setAdapter(adapter);
        return view;
    }
}