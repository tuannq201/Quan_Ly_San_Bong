package com.example.myapplication.UI.nguoithue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.nguoi_thue_adapter.DemoAdapter;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.util.Cover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemoNTFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemoNTFragment extends Fragment {

    GridView gridView;
    PhieuThueDAO phieuThueDAO;
    ArrayList<TrangThai> trangThais;
    DemoAdapter demoAdapter;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    int soSan = 0;//số sân của cụm sân
    List<San> sanList;
    SanDAO sanDAO;
    int maCumSan = 7;
    public DemoNTFragment() {
        // Required empty public constructor
    }


    public static DemoNTFragment newInstance(String param1, String param2) {
        DemoNTFragment fragment = new DemoNTFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phieuThueDAO = new PhieuThueDAO(getContext());
        trangThais = new ArrayList<>();
        sanDAO = new SanDAO(getContext());
        sanList = sanDAO.getSanByCumSan(String.valueOf(maCumSan));
        soSan = sanList.size();
        Date now = new Date();
        String strNow = format.format(now);
        for (int i = 1;i<= 12;i++){
            for (int y = 1;y<=soSan;y++){
                int maSan = sanList.get(y-1).maSan;
                String ca = String.valueOf(i);
                TrangThai trangThai = phieuThueDAO.checkTrangThai(maSan, ca, strNow);
                trangThais.add(trangThai);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_demo_n_t, container, false);
        gridView = v.findViewById(R.id.nt_gridview);
        gridView.setNumColumns(soSan);


        demoAdapter = new DemoAdapter(getContext(), trangThais);
        gridView.setAdapter(demoAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int ca = i/soSan + 1;
                int ngay = i%soSan;
                String san = sanList.get(ngay).tenSan;
                String tt = "trống";
                if(trangThais.get(i).taiKhoan.contains("0")){
                    tt = "đã thuê";
                }
                Toast.makeText(getContext(), tt+" ca:"+ca+" sân:"+san+" tiền sân:"+ Cover.IntegerToVnd(trangThais.get(i).tienSan)+"vnd", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }


}