package com.example.myapplication.UI.chusan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.entity.PhieuThue;

import java.util.ArrayList;
import java.util.List;


public class CaSanFragment extends Fragment {
    PhieuThueDAO dao;
    PhieuThue item;
    List<PhieuThue> list;
    PhieuThueDAO adapter
    //ArrayAdapter adapter;
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
        ArrayList<String> list = new ArrayList<>();
//        for (int i = 1; i <13 ; i++) {
//            //list.add("Ca: "+i);
//            String tenCa = String.valueOf(i);
//        }

        //adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,list);
        adapter = new PhieuThueDAO()
        gridView.setAdapter(adapter);
        return view;
    }
    private void openDialog(){

    }
    public String caToTime(String ca){
        if (ca.contains("1")){
            return "6:00-7:00";
        }
        if (ca.contains("2")){
            return "7:30-8:30";
        }
        if (ca.contains("3")){
            return "9:00-10:00";
        }
        if (ca.contains("4")){
            return "10:30-11:30";
        }
        if (ca.contains("5")){
            return "12:00-13:00";
        }
        if (ca.contains("6")){
            return "13:30-14:30";
        }
        if (ca.contains("7")){
            return "15:00-16:00";
        }
        if (ca.contains("8")){
            return "16:30-17:30";
        }
        if (ca.contains("9")){
            return "18:00-19:00";
        }
        if (ca.contains("10")){
            return "19:30-20:30";
        }
        if (ca.contains("11")){
            return "21:00-22:00";
        }
        if (ca.contains("12")){
            return "22:30-23:30";
        }
        return ca;
    }
}