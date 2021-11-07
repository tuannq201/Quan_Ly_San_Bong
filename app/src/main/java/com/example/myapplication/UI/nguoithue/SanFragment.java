package com.example.myapplication.UI.nguoithue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ListSanAdapter;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SanFragment extends Fragment {

    SanDAO sanDAO;
    List<San> sanList;
    RecyclerView rcv;
    ListSanAdapter adapter;
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
        sanDAO = new SanDAO(getContext());
        sanList = sanDAO.getAll();
        Toast.makeText(getContext(), ""+sanList.size(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_san, container, false);
        rcv = v.findViewById(R.id.rcv_san_nguoi_thue);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListSanAdapter(getContext(), sanList, new ITFOnItenClick() {
            @Override
            public void onItemClick(San san) {
                Toast.makeText(getContext(), ""+san.tenSan, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(PhieuThue phieuThue) {

            }
        });
        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_recycle_view);
        rcv.setLayoutAnimation(animationController);
        rcv.setAdapter(adapter);


        return v;
    }
}