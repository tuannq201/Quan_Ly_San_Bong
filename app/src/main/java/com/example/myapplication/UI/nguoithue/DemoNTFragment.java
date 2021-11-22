package com.example.myapplication.UI.nguoithue;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
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
    int rongMH = 0;
    int maCumSan = 7;
    int rongItemGridView = 0;
    int rongGridView = 0;
    int caoGridView = 0;
    public DemoNTFragment() {
        // Required empty public constructor
    }


    public static DemoNTFragment newInstance(String param1, String param2) {
        DemoNTFragment fragment = new DemoNTFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
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

        Display display = getContext().getDisplay();
        Point size = new Point();
        display.getSize(size);
        rongMH = size.x;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_demo_n_t, container, false);
        gridView = v.findViewById(R.id.nt_gridview);
        gridView.setNumColumns(soSan);
        LinearLayout layout = v.findViewById(R.id.linearLayout_nt);


            gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                    new ViewTreeObserver.OnGlobalLayoutListener(){

                        @Override
                        public void onGlobalLayout() {
                            caoGridView = gridView.getHeight();
                            rongGridView = gridView.getWidth();
                            //Log.i("ooooo", ""+"width: "+mWidth+"   height: "+mHeight);
                            abc(caoGridView, rongGridView, v);
                            ViewGroup.LayoutParams layoutParams = gridView.getLayoutParams();
                            layoutParams.width = (rongMH)/6 * soSan;
                            gridView.setLayoutParams(layoutParams);
                            ViewGroup.LayoutParams layoutParams1 = layout.getLayoutParams();
                            layoutParams1.width = (rongMH)/6;
                            layout.setLayoutParams(layoutParams1);
                        }
                    });



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

    public void abc(int grHeigh, int grWidth, View v){
        TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12;
        t1 = v.findViewById(R.id.tv_nt1);
        t2 = v.findViewById(R.id.tv_nt2);
        t3 = v.findViewById(R.id.tv_nt3);
        t4 = v.findViewById(R.id.tv_nt4);
        t5 = v.findViewById(R.id.tv_nt5);
        t6 = v.findViewById(R.id.tv_nt6);
        t7 = v.findViewById(R.id.tv_nt7);
        t8 = v.findViewById(R.id.tv_nt8);
        t9 = v.findViewById(R.id.tv_nt9);
        t10 = v.findViewById(R.id.tv_nt10);
        t11 = v.findViewById(R.id.tv_nt11);
        t12 = v.findViewById(R.id.tv_nt12);
        List<TextView> list = new ArrayList<>();
        list.add(t1);list.add(t2);list.add(t3);list.add(t4);list.add(t5);list.add(t6);list.add(t7);list.add(t8);list.add(t9);list.add(t10);list.add(t11);list.add(t12);
        for (int i = 0;i<list.size();i++){
            ViewGroup.LayoutParams params = list.get(i).getLayoutParams();
            params.height = grHeigh / 12;
            list.get(i).setLayoutParams(params);
            list.get(i).setText(""+(i+1));
            //Toast.makeText(getContext(), ""+grHeigh/12, Toast.LENGTH_SHORT).show();
        }
    }


}