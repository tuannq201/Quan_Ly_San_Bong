package com.example.myapplication.UI.nguoithue;

import android.app.Dialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.myapplication.R;
import com.example.myapplication.UI.chusan.ListSanFragment;
import com.example.myapplication.adapter.ListSanAdapter;
import com.example.myapplication.adapter.nguoi_thue_adapter.CumSanAdapter;
import com.example.myapplication.adapter.nguoi_thue_adapter.DanhGiaAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.CumSan;
import com.example.myapplication.entity.DanhGia;
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
    TextView tv_tenSan_sdg, tv_tongThue, tv_tong_dg, tv_sao_sdg;
    ImageView btn_dongShow;
    ListView lv_showdg;
    CumSanDAO cumSanDAO;
    List<CumSan> cumSanList;
    List<CumSan> listCS;
    RecyclerView rcv;
    CumSanAdapter adapter;
    SearchView sv;
    ITFsendData itFsendData;
    Dialog dialog;
    ImageView iv_chonDD;
    SanDAO sanDAO;
    PhieuThueDAO phieuThueDAO;
    ListView lv_dia_diem;


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
        phieuThueDAO = new PhieuThueDAO(getContext());
        sanDAO = new SanDAO(getContext());
        cumSanList = new ArrayList<>();
        listCS = cumSanDAO.getAll();
        for (int i = 0;i<listCS.size();i++){
            CumSan cumSan = listCS.get(i);
            cumSan.soDanhGia = phieuThueDAO.soDanhGiaCumSan(String.valueOf(cumSan.maCumSan));
            cumSan.soSao = phieuThueDAO.soSaoCumSan(String.valueOf(cumSan.maCumSan));
            if (sanDAO.getSanByCumSan(String.valueOf(cumSan.maCumSan)).size() > 0){
                cumSanList.add(listCS.get(i));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_san, container, false);
        rcv = v.findViewById(R.id.rcv_san_nguoi_thue);
        sv = v.findViewById(R.id.sv_cum_san);
        iv_chonDD = v.findViewById(R.id.iv_chondd);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        rcv.setLayoutManager(mLayoutManager);
        rcv.setItemAnimator(new DefaultItemAnimator());

        LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_recycle_view);
        rcv.setLayoutAnimation(animationController);
        setRecycleView(getContext(), cumSanList);

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
        iv_chonDD.setOnClickListener(view -> {
            openDialogCDD(getContext());
        });

        return v;
    }

    public int search(String s){
        List<CumSan> list = new ArrayList<>();
            if (cumSanList.size()> 0){
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
                setRecycleView(getContext(), list);
            }
            return list.size();
    }

    @Override
    public void onResume() {
        super.onResume();
        ChipNavigationBar chip = getActivity().findViewById(R.id.chip_navi_nguoi_thue);
        chip.setVisibility(View.VISIBLE);

        search(NguoiThueActivity.ddiaiemDC);

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

        if (NguoiThueActivity.count == 0){
            openDialogCDD(getContext());
            NguoiThueActivity.count++;
        }
    }

    public void setRecycleView(Context context, List<CumSan> list){
        //cumSanList.add(new CumSan());
        adapter = new CumSanAdapter(context, list, new ItemCumSanClick() {

            @Override
            public void onItemClick(CumSan cumSan, String typeKey) {

                //Toast.makeText(getContext(), ""+cumSan.soDanhGia+" sao: "+cumSan.soSao, Toast.LENGTH_SHORT).show();
                if (typeKey == "san"){
                    ChipNavigationBar chip = getActivity().findViewById(R.id.chip_navi_nguoi_thue);
                    chip.setVisibility(View.GONE);
                    itFsendData.sendData(cumSan.maCumSan);
                }
                if (typeKey == "show"){
                    try {
                        showDanhGia(cumSan);
                    }catch (Exception e){
                        Toast.makeText(getContext(), "Chưa có đánh giá!!!", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(getContext(), "show", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rcv.setAdapter(adapter);
    }

    public void showDanhGia(CumSan cumSan){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_show_danh_gia);

        tv_tenSan_sdg = dialog.findViewById(R.id.tv_tenSan_sdg);
        tv_tong_dg = dialog.findViewById(R.id.tv_soDanhGia_sdg);
        tv_tongThue = dialog.findViewById(R.id.tv_soLanThue_sdg);
        tv_sao_sdg = dialog.findViewById(R.id.tv_sao_sdg);
        lv_showdg = dialog.findViewById(R.id.lv_sdg);
        btn_dongShow = dialog.findViewById(R.id.iv_dong);

        tv_tenSan_sdg.setText(cumSan.tenCumSan);
        tv_tongThue.setText("Số lần thuê: "+phieuThueDAO.soLuotThueCumSan(String.valueOf(cumSan.maCumSan)));
        tv_tong_dg.setText("Số đánh giá: "+phieuThueDAO.soDanhGiaCumSan(String.valueOf(cumSan.maCumSan)));
        float dg = phieuThueDAO.soSaoCumSan(String.valueOf(cumSan.maCumSan)) / phieuThueDAO.soDanhGiaCumSan(String.valueOf(cumSan.maCumSan));
        tv_sao_sdg.setText("Đánh giá trung bình: "+dg);

        List<DanhGia> listDG = getDanhGia(String.valueOf(cumSan.maCumSan));
        DanhGiaAdapter adapter = new DanhGiaAdapter(getContext(), listDG);
        lv_showdg.setAdapter(adapter);

        btn_dongShow.setOnClickListener(view -> {
            dialog.dismiss();
        });
        dialog.show();
    }

    public List<DanhGia> getDanhGia(String maCS){
        UserDAO userDAO = new UserDAO(getContext());
        SanDAO sanDAO = new SanDAO(getContext());
        List<DanhGia> rsList = new ArrayList<>();
        List<PhieuThue> ptList = phieuThueDAO.getPhieuThueCumSan(maCS);
        if (ptList != null){
            for (PhieuThue pt: ptList){
                DanhGia dg = new DanhGia();
                if (pt.danhGia > 0){
                    dg.taiKhoanNT = pt.nguoiThue;
                    dg.ngayThue = pt.ngayThue;
                    if (pt.phanHoi != null){
                        dg.danhGia = pt.phanHoi;
                    }else {
                        dg.danhGia = ".....";
                    }
                    dg.giaThue = pt.tienSan;
                    dg.sao = pt.sao;
                    String tenNT = userDAO.getUser(pt.nguoiThue).hoTen;
                    dg.tenNT = tenNT;
                    String tenSan = sanDAO.getID(String.valueOf(pt.maSan)).tenSan;
                    dg.tenSan = tenSan;
                    rsList.add(dg);
                }

            }
        }
        return rsList;
    }

    public void openDialogCDD(Context context){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_chon_dia_diem);
        lv_dia_diem = dialog.findViewById(R.id.lv_dialog_chon_dd);

        ArrayList<String> list = new ArrayList<>();
        list.add("Tất cả");
        list.add("Quận Cẩm Lệ");
        list.add("Quận Hải Châu");
        list.add("Quận Liên Chiểu");
        list.add("Quận Ngũ Hành Sơn");
        list.add("Quận Sơn Trà");
        list.add("Quận Thanh Khê");
        list.add("Huyện Hòa Vang");
        list.add("Huyện Trường Sa");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);

        lv_dia_diem.setAdapter(adapter);

        lv_dia_diem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                String textSV = "";
                if (pos == 0){
                    dialog.dismiss();
                    search("");
                    NguoiThueActivity.ddiaiemDC = textSV;
                }else {
                    String dd = list.get(pos);
                    int a = dd.indexOf(" ");
                    textSV = dd.substring(a, dd.length()).trim();
                    if (search(textSV) == 0){
                        search(NguoiThueActivity.ddiaiemDC);
                        Toast.makeText(context, "không có sân nào tại "+dd+"\nvui lòng chọn địa điểm khác!!!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "tìm thấy "+search(textSV)+" kết quả", Toast.LENGTH_SHORT).show();
                        NguoiThueActivity.ddiaiemDC = textSV;
                        dialog.dismiss();
                    }
                }
            }
        });

        dialog.show();
    }

}