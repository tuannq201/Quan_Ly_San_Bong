package com.example.myapplication.UI.nguoithue;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.nguoi_thue_adapter.SDTAdapter;
import com.example.myapplication.adapter.nguoi_thue_adapter.SanDaThueAdapter;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;
import com.example.myapplication.util.Cover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SanDaThueFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SanDaThueFragment extends Fragment {
    PhieuThueDAO phieuThueDAO;
    List<PhieuThue> phieuThueList;
    ListView lv;
    SanDaThueAdapter adapter;
    RecyclerView rcv;
    SDTAdapter sdtAdapter;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    int posNow = 0;



    public SanDaThueFragment() {
    }


    public static SanDaThueFragment newInstance(String param1, String param2) {
        SanDaThueFragment fragment = new SanDaThueFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String phone = pref.getString("PHONE","");
        phieuThueDAO = new PhieuThueDAO(getContext());

        try {
            phieuThueList = phieuThueDAO.getPhieuByUser(phone);

        }catch (Exception e){
        }

        List<PhieuThue> list = new ArrayList<>();
        for (int i=0;i<phieuThueList.size();i++){
            PhieuThue pt = new PhieuThue();
            pt = phieuThueList.get(i);
            pt.position = Cover.dateToPos(pt.ngayThue, pt.caThue, 0);
            list.add(pt);
        }


        //sắp xếp phiếu thuê
        Collections.sort(list, new Comparator<PhieuThue>() {
            @Override
            public int compare(PhieuThue t0, PhieuThue t1) {
                return Integer.compare(t0.position, t1.position);
            }
        });


//        adapter = new SanDaThueAdapter(getContext(), (ArrayList<PhieuThue>) list, new ITFOnItenClick() {
//
//
//            @Override
//            public void onItemClick(San san, int type) {
//
//            }
//
//            @Override
//            public void onItemClick(PhieuThue phieuThue) {
//
//            }
//        });

        Date now = new Date();
        posNow = Cover.dateToPos(format.format(now), "1", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_san_da_thue, container, false);
//        rcv = v.findViewById(R.id.rcv_san_da_thue);
//        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
//        rcv.setLayoutManager(mLayoutManager);
//        rcv.setItemAnimator(new DefaultItemAnimator());
//        rcv.setAdapter(adapter);

        lv = v.findViewById(R.id.lv_1111111);
        List<PhieuThue> list = new ArrayList<>();
        for (int i=0;i<phieuThueList.size();i++){
            PhieuThue pt = new PhieuThue();
            pt = phieuThueList.get(i);
            pt.position = Cover.dateToPos(pt.ngayThue, pt.caThue, 0);
            list.add(pt);
        }

        //sắp xếp phiếu thuê
        Collections.sort(list, new Comparator<PhieuThue>() {
            @Override
            public int compare(PhieuThue t0, PhieuThue t1) {
                return Integer.compare(t0.position, t1.position);
            }
        });

        sdtAdapter = new SDTAdapter(getContext(), list);
        lv.setAdapter(sdtAdapter);

        for (int i=0;i<list.size();i++){
            String strPos = String.valueOf(list.get(i).position);
            String strPosSub = strPos.substring(strPos.length()-1, strPos.length());
            int pos = Integer.parseInt(strPos);
            if (pos >= posNow){
                lv.setSelection(i);
                break;
            }
        }
        return v;
    }
}