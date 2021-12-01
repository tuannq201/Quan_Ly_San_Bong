package com.example.myapplication.UI.chusan;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.UI.nguoithue.CaSanFragment;
import com.example.myapplication.adapter.RecyclerCumSanAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.entity.CumSan;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFOnItenClick;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class CumSanFragment extends Fragment {

    String phone;
    ImageView imgAdd;
    Button btSave, btCancel;
    TextView tvTitle;
    EditText edTenCumSan, edDiaChi;
    RecyclerView rcvCumSan;
    RecyclerCumSanAdapter adapter;
    List<CumSan> cumSanList;
    CumSanDAO dao;
    CumSan cumSan;
    public CumSanFragment() {
    }

    public static CumSanFragment newInstance(String param1, String param2) {
        CumSanFragment fragment = new CumSanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        phone = pref.getString("PHONE","");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cum_san, container, false);
        rcvCumSan = view.findViewById(R.id.recycler_cum_san);
        imgAdd = view.findViewById(R.id.img_add_cumsan);
        imgAdd.setOnClickListener(v -> {
            openDialogThemCumSan(0, cumSan);
        });
        cumSanList = new ArrayList<>();
        dao = new CumSanDAO(getActivity());
        update();
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ChipNavigationBar chip = getActivity().findViewById(R.id.chip_navi_chu_san);
        chip.setVisibility(View.GONE);
    }

    private void update() {
        cumSanList = dao.getCSByChuSan(phone);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rcvCumSan.setLayoutManager(layoutManager);
        adapter = new RecyclerCumSanAdapter(getActivity(), cumSanList, new ITFOnItenClick() {
            @Override
            public void onItemClick(San san, int type) {
            }

            @Override
            public void onItemClick(PhieuThue phieuThue) {
            }

            @Override
            public void onItemClick(CumSan cumSan, int type) {
                if (type == 0){
                    openDialogThemCumSan(1, cumSan);
                }
                if (type == 1){
                    delete(String.valueOf(cumSan.maCumSan));
                }
            }
        });
        adapter.notifyDataSetChanged();
        rcvCumSan.setAdapter(adapter);
    }
    private void openDialogThemCumSan(int type, CumSan cumSan1) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_add_cum_san);
        Window window = dialog.getWindow();
        window.getAttributes().windowAnimations = R.style.DialogAnimation;
        btCancel = dialog.findViewById(R.id.bt_cancel);
        btSave = dialog.findViewById(R.id.bt_save);
        edTenCumSan = dialog.findViewById(R.id.ed_ten_cum);
        edDiaChi = dialog.findViewById(R.id.ed_dia_chi);
        if (type != 0){
            tvTitle = dialog.findViewById(R.id.text_title);
            tvTitle.setText("Cập Nhập Cụm Sân");
            edTenCumSan.setText(cumSan1.tenCumSan);
            edDiaChi.setText(cumSan1.diaChi);
        }
        btCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
        btSave.setOnClickListener(v -> {
            if (edTenCumSan.length() == 0 || edDiaChi.length() == 0){
                Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }else {
                dao = new CumSanDAO(getContext());
                cumSan = new CumSan();
                cumSan.tenCumSan = edTenCumSan.getText().toString().trim();
                cumSan.diaChi = edDiaChi.getText().toString().trim();
                cumSan.chuSan = phone;
                if (type == 0){
                    if (dao.insert(cumSan) > 0){
                        Toast.makeText(getContext(), "Tạo cụm sân thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Tạo cụm sân không thành công", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    cumSan.maCumSan = cumSan1.maCumSan;
                    if (dao.update(cumSan) > 0){
                        Toast.makeText(getContext(), "Sửa cụm sân thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getContext(), "Sửa cụm sân không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                update();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void delete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa Cụm Sân");
        builder.setMessage("Bạn có muốn xóa Không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", (dialog, which) ->{
            dao.delete(id);
            update();
            dialog.cancel();
        } );
        builder.setNegativeButton("Không", (dialog, which) ->{
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
}