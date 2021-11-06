package com.example.myapplication.UI.nguoithue;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.nguoi_thue_adapter.SanDaThueAdapter;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.PhieuThue;

import java.util.ArrayList;
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
            Toast.makeText(getContext(), ""+phieuThueList.size(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getContext(), "err", Toast.LENGTH_SHORT).show();
        }
        adapter = new SanDaThueAdapter(getContext(), (ArrayList<PhieuThue>) phieuThueList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_san_da_thue, container, false);
        lv = v.findViewById(R.id.lv_san_da_thue);
        lv.setAdapter(adapter);
        return v;
    }
}