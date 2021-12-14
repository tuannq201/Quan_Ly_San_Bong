package com.example.myapplication.UI.nguoithue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.UI.chusan.DanhGiaFragment;
import com.example.myapplication.adapter.ListSanAdapter;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.CumSan;
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
    List<San> sanList, listSanA;
    public int maCS;
    ListSanAdapter adapter;
    SanDAO sanDAO;
    PhieuThueDAO phieuThueDAO;

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
        sanDAO = new SanDAO(getContext());
        phieuThueDAO = new PhieuThueDAO(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_san_cum_san, container, false);
        rcv = v.findViewById(R.id.rcv_san_cumSan_nt);
        tenCS = v.findViewById(R.id.tv_cum_san_nt);
        sanList = new ArrayList<>();
        updateLV(maCS);
        return v;
    }
    public void updateLV(int ma){
        listSanA = (ArrayList<San>) sanDAO.getSanByCumSan(String.valueOf(ma));
        for (int i = 0;i<listSanA.size();i++){
            San san = listSanA.get(i);
            san.soDanhGia = phieuThueDAO.soDanhGiaSan(String.valueOf(san.maSan));
            san.soSao = phieuThueDAO.soSaoSan(String.valueOf(san.maSan));
            sanList.add(san);
        }
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());
        adapter = new ListSanAdapter(getActivity(), sanList, new ITFOnItenClick() {

            @Override
            public void onItemClick(San san, int type) {

                //Toast.makeText(getContext(), ""+san.soSao, Toast.LENGTH_SHORT).show();
                if (type == 6){
                    //show dánh giá
                    DanhGiaFragment fragment = new DanhGiaFragment(String.valueOf(san.maSan));
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frm_nguoi_thue, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }else {
                    onClickGoToCaSan(san);
                }
            }
            @Override
            public void onItemClick(PhieuThue phieuThue) {
            }

            @Override
            public void onItemClick(CumSan cumSan, int type) {
            }
        });
        rcv.setAdapter(adapter);
    }

    private void onClickGoToCaSan(San san) {

        CaSanFragment fragment = new CaSanFragment(san, "NT");
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.cst_nguoi_thue, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}