package com.example.myapplication.UI.nguoithue;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.SearchView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.myapplication.R;
import com.example.myapplication.UI.chusan.ListSanFragment;
import com.example.myapplication.adapter.ListSanAdapter;
import com.example.myapplication.adapter.nguoi_thue_adapter.CumSanAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.CumSan;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;
import com.example.myapplication.itf.ItemCumSanClick;
import com.example.myapplication.util.Cover;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SanFragment extends Fragment {

    CumSanDAO cumSanDAO;
    List<CumSan> cumSanList;
    RecyclerView rcv;
    CumSanAdapter adapter;
    SearchView sv;
    ITFsendData itFsendData;

    public interface ITFsendData {
        void sendData(int maCumSan);
    }
    public SanFragment() {
        // Required empty public constructor
    }


    public static SanFragment newInstance(String param1, String param2) {
        SanFragment fragment = new SanFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cumSanDAO  = new CumSanDAO(getContext());
        cumSanList = cumSanDAO.getAll();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_san, container, false);
        rcv = v.findViewById(R.id.rcv_san_nguoi_thue);
        sv = v.findViewById(R.id.sv_cum_san);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());

        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_recycle_view);
        rcv.setLayoutAnimation(animationController);
        setRecycleView(cumSanList);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                search(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                search(s);
                return false;
            }
        });


        return v;
    }


    public void search(String s){
            if (cumSanList.size()> 0){
                List<CumSan> list = new ArrayList<>();
                for (int i = 0;i<cumSanList.size();i++){
                    if (s != null && cumSanList.get(i).tenCumSan != null && cumSanList.get(i).diaChi != null){
                        s = s.toLowerCase();
                        String ten = cumSanList.get(i).tenCumSan.toLowerCase();
                        String diaChi = cumSanList.get(i).diaChi.toLowerCase();
                        if (ten.contains(s) || diaChi.contains(s)){
                            list.add(cumSanList.get(i));
                        }
                    }
                }
                setRecycleView(list);
            }
    }

    @Override
    public void onResume() {
        super.onResume();
        ChipNavigationBar chip = getActivity().findViewById(R.id.chip_navi_nguoi_thue);
        chip.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        itFsendData = (ITFsendData) getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();
        itFsendData = (ITFsendData) getActivity();
    }

    public void setRecycleView(List<CumSan> list){
        cumSanList.add(new CumSan());
        adapter = new CumSanAdapter(getContext(), list, new ItemCumSanClick() {
            @Override
            public void onItemClick(CumSan cumSan) {
                ChipNavigationBar chip = getActivity().findViewById(R.id.chip_navi_nguoi_thue);
                chip.setVisibility(View.GONE);
                itFsendData.sendData(cumSan.maCumSan);
            }
        });
        rcv.setAdapter(adapter);
    }

}