package com.example.myapplication.UI.nguoithue;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.UI.chusan.CaSanOfChuSanActivity;
import com.example.myapplication.adapter.ListSanAdapter;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SanCumSanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SanCumSanFragment extends Fragment {

    RecyclerView rcv;
    TextView tenCS;
    List<San> sanList;
    public int maCS;
    ListSanAdapter adapter;
    SanDAO dao;

    public SanCumSanFragment(int maCumSan) {
        this.maCS = maCumSan;
    }


    public static SanCumSanFragment newInstance(int maCumSan) {
        SanCumSanFragment fragment = new SanCumSanFragment(maCumSan);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new SanDAO(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_san_cum_san, container, false);
        rcv = v.findViewById(R.id.rcv_san_cumSan_nt);
        tenCS = v.findViewById(R.id.tv_cum_san_nt);
        updateLV(maCS);
        return v;
    }
    public void updateLV(int ma){
        sanList = (ArrayList<San>) dao.getSanByCumSan(String.valueOf(ma));
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListSanAdapter(getActivity(), sanList, new ITFOnItenClick() {
            @Override
            public void onItemClick(San san) {
                onClickGoToCaSan(san);
            }

            @Override
            public void onItemClick(PhieuThue phieuThue) {

            }
        });
        rcv.setAdapter(adapter);
    }

    private void onClickGoToCaSan(San san) {
        Intent intent = new Intent(this.getContext(), CaSanOfChuSanActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_san", san);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}