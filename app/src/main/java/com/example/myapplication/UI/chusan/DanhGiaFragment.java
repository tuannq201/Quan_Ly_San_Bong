package com.example.myapplication.UI.chusan;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.nguoi_thue_adapter.DanhGiaAdapter;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.DanhGia;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.util.Cover;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DanhGiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DanhGiaFragment extends Fragment {

    public List<DanhGia> listDG;
    public String maSan;
    public List<PhieuThue> listPT;
    public PhieuThueDAO phieuThueDAO;
    public SanDAO sanDAO;
    public UserDAO userDAO;
    TextView tv_1, tv_2, tv_3, tv_4, tv_5;
    ListView lv;
    DanhGiaAdapter adapter;
    int tongSaoSan = 0;
    public DanhGiaFragment(String maSan) {
        // Required empty public constructor
        this.maSan = maSan;
    }


    public static DanhGiaFragment newInstance(String maSan) {
        DanhGiaFragment fragment = new DanhGiaFragment(maSan);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phieuThueDAO = new PhieuThueDAO(getContext());
        listPT = phieuThueDAO.getPhieuThueSan(maSan);
        sanDAO = new SanDAO(getContext());
        userDAO = new UserDAO(getContext());
        listDG = new ArrayList<>();
        if (listPT != null){

            for (PhieuThue pt: listPT){
                if (pt.danhGia == 1){
                    //đã đánh giá
                    tongSaoSan += pt.sao;
                    DanhGia danhGia = new DanhGia();
                    danhGia.giaThue = pt.tienSan;
                    try {
                        danhGia.tenSan = sanDAO.getID(maSan).tenSan;
                    }catch (Exception e){
                        danhGia.tenSan = maSan;
                    }
                    danhGia.sao = pt.sao;
                    danhGia.danhGia = pt.phanHoi;
                    danhGia.ngayThue = pt.ngayThue;
                    danhGia.taiKhoanNT = pt.nguoiThue;
                    try {
                        danhGia.tenNT = userDAO.getUser(pt.nguoiThue).hoTen;
                    }catch (Exception e){
                        danhGia.tenNT = pt.ngayThue.substring(0, 3)+"******";
                    }
                    listDG.add(danhGia);
                }
            }
        }

        listDG.sort(new Comparator<DanhGia>() {
            @Override
            public int compare(DanhGia dg0, DanhGia dg1) {
                Date d1 = Cover.StringToDate2(dg0.ngayThue);
                Date d2 = Cover.StringToDate2(dg1.ngayThue);
                return d2.compareTo(d1);
            }
        });
        adapter = new DanhGiaAdapter(getContext(), listDG);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_danh_gia, container, false);
        lv = v.findViewById(R.id.lv_show_dg);
        tv_1 = v.findViewById(R.id.tv_frag_dg_1);
        tv_2 = v.findViewById(R.id.tv_frag_dg_2);
        tv_3 = v.findViewById(R.id.tv_frag_dg_3);
        tv_4 = v.findViewById(R.id.tv_frag_dg_4);
        tv_5 = v.findViewById(R.id.tv_frag_odg);

        tv_1.setText("Phản hồi người thuê sân - "+sanDAO.getID(maSan).tenSan);
        tv_2.setText("Số lượt thuê sân: "+listPT.size());
        tv_3.setText("Số đánh giá: "+listDG.size());
        try {
            float sao = tongSaoSan / listDG.size();
            tv_4.setText("Đánh giá trung bình: "+sao+" sao");
        }catch (Exception e){
            tv_4.setText("Đánh giá trung bình: 0");
        }
        if (listDG.size() > 0){
            tv_5.setVisibility(View.GONE);
        }
        lv.setAdapter(adapter);
        return v;
    }
}