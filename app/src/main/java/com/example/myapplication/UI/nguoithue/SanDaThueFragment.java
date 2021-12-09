package com.example.myapplication.UI.nguoithue;

import static android.content.Context.MODE_PRIVATE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.adapter.nguoi_thue_adapter.SDTAdapter;
import com.example.myapplication.adapter.nguoi_thue_adapter.SanDaThueAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;
import com.example.myapplication.itf.ITFClickPhieuThue;
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
    SDTAdapter sdtAdapter;
    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    //int posNow = 0;
    Dialog dialog;
    CumSanDAO cumSanDAO;
    SanDAO sanDAO;

    SimpleDateFormat formatNgay = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatGio = new SimpleDateFormat("HH:mm");
    TextView tv_tenSan_dg, tv_dg, btn_dg;
    EditText ed_danh_gia_text;
    RatingBar rb_dg;
    String phone = "";



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
        phone = pref.getString("PHONE","");
        phieuThueDAO = new PhieuThueDAO(getContext());
        cumSanDAO = new CumSanDAO(getContext());
        sanDAO = new SanDAO(getContext());


//        phieuThueList = phieuThueDAO.getPhieuByUser(phone);
//        List<PhieuThue> list = new ArrayList<>();
//        for (int i=0;i<phieuThueList.size();i++){
//            PhieuThue pt = new PhieuThue();
//            pt = phieuThueList.get(i);
//            pt.position = Cover.dateToPos(pt.ngayThue, pt.caThue, 0);
//            list.add(pt);
//        }
//
//
//        //sắp xếp phiếu thuê
//        Collections.sort(list, new Comparator<PhieuThue>() {
//            @Override
//            public int compare(PhieuThue t0, PhieuThue t1) {
//                return Integer.compare(t0.position, t1.position);
//            }
//        });

//        Date now = new Date();
//        posNow = Cover.dateToPos(format.format(now), "1", 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_san_da_thue, container, false);

        lv = v.findViewById(R.id.lv_1111111);
        setListView();
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setListView(){
        phieuThueList = phieuThueDAO.getPhieuByUser(phone);
        List<PhieuThue> list = new ArrayList<>();
//        for (int i=0;i<phieuThueList.size();i++){
//            PhieuThue pt = new PhieuThue();
//            pt = phieuThueList.get(i);
//            pt.position = Cover.dateToPos(pt.ngayThue, pt.caThue, 0);
//            list.add(pt);
//        }

        //sắp xếp phiếu thuê
//        Collections.sort(list, new Comparator<PhieuThue>() {
//            @Override
//            public int compare(PhieuThue t0, PhieuThue t1) {
//                return Integer.compare(t0.position, t1.position);
//            }
//        });
        phieuThueList.sort(new Comparator<PhieuThue>() {
            @Override
            public int compare(PhieuThue pt0, PhieuThue pt1) {
                Date date0 = Cover.ThoiGianThueToDate(pt0.ngayThue, pt0.caThue);
                Date date1 = Cover.ThoiGianThueToDate(pt1.ngayThue, pt1.caThue);
                return date0.compareTo(date1);
            }
        });

        sdtAdapter = new SDTAdapter(getContext(), phieuThueList, new ITFClickPhieuThue() {
            @Override
            public void OnClick(PhieuThue phieuThue) {
                //Toast.makeText(getContext(), ""+phieuThue.maPT, Toast.LENGTH_SHORT).show();
                if (validateDel(phieuThue.caThue, phieuThue.ngayThue)){
                    delete(String.valueOf(phieuThue.maPT));
                }else {
                    Toast.makeText(getContext(), "không thể hủy sân!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        lv.setAdapter(sdtAdapter);

        Date now = new Date();
        for (int i=0;i<phieuThueList.size();i++){
            Date datei = Cover.ThoiGianThueToDate(phieuThueList.get(i).ngayThue, phieuThueList.get(i).caThue);
            if (datei.compareTo(now) >= 0){
                lv.setSelection(i);
                break;
            }
        }


//        for (int i=0;i<phieuThueList.size();i++){
//            String strPos = String.valueOf(list.get(i).position);
//            String strPosSub = strPos.substring(strPos.length()-1, strPos.length());
//            int pos = Integer.parseInt(strPos);
//            if (pos >= posNow){
//                lv.setSelection(i);
//                break;
//            }
//        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                PhieuThue phieuThue = phieuThueList.get(pos);
                dialog_danhGia(phieuThue);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void delete(String id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Hủy thuê");
        builder.setMessage("Bạn có muốn hủy Không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", (dialog, which) ->{
            if (phieuThueDAO.delete(id) > 0){
                Toast.makeText(getContext(), "Đã hủy sân", Toast.LENGTH_SHORT).show();
                setListView();
            }else {
                Toast.makeText(getContext(), "Hủy thất bại", Toast.LENGTH_SHORT).show();
            }
            dialog.cancel();
        } );
        builder.setNegativeButton("Không", (dialog, which) ->{
            dialog.cancel();
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }


    int rating = 0;
    boolean dDG = false;
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void dialog_danhGia(PhieuThue pt){

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_danh_gia);
        tv_tenSan_dg = dialog.findViewById(R.id.tv_ten_dialog_danhGia);
        tv_dg = dialog.findViewById(R.id.tv_rating_dialog_danhGia);
        btn_dg = dialog.findViewById(R.id.btn_gui_danhGia);
        rb_dg = dialog.findViewById(R.id.rb_dialog_danhGia);
        ed_danh_gia_text = dialog.findViewById(R.id.ed_phanHoi_text);

        //tv_tenSan_dg.setText(""+pt.toString());
        String cumSan = cumSanDAO.getCumSanBySan(String.valueOf(pt.maSan)).tenCumSan;
        String san = sanDAO.getID(String.valueOf(pt.maSan)).tenSan;
        tv_tenSan_dg.setText("Sân :"+cumSan+" - "+san);

        if (pt.danhGia == 1){
            Toast.makeText(getContext(), "Đã đánh giá", Toast.LENGTH_SHORT).show();
            rb_dg.setRating(pt.sao);
            if (pt.sao <= 1){
                tv_dg.setText("tệ!!!");
            }else
            if (pt.sao <= 2){
                tv_dg.setText("trung bình!!!");
            }else
            if (pt.sao <= 3){
                tv_dg.setText("khá!!!");
            }else
            if (pt.sao <= 4){
                tv_dg.setText("tốt!!!");
            }else
            {
                tv_dg.setText("rất tốt!!!");
            }
            //return;
        }
        if (pt.phanHoi != null){
            ed_danh_gia_text.setText(pt.phanHoi);
        }
         rb_dg.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
             @Override
             public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                 if (v <= 1){
                     tv_dg.setText("tệ!!!");
                 }else
                 if (v <= 2){
                     tv_dg.setText("trung bình!!!");
                 }else
                 if (v <= 3){
                     tv_dg.setText("khá!!!");
                 }else
                 if (v <= 4){
                     tv_dg.setText("tốt!!!");
                 }else
                 {
                     tv_dg.setText("rất tốt!!!");
                 }

                 rating = (int) v;
                 dDG = b;

             }
         });
         btn_dg.setOnClickListener(view -> {
             if (dDG == true){
                 PhieuThue phieuThue = new PhieuThue();
                 phieuThue.maPT = pt.maPT;
                 phieuThue.maSan = pt.maSan;
                 phieuThue.nguoiThue = pt.nguoiThue;
                 phieuThue.caThue = pt.caThue;
                 phieuThue.ngayThue = pt.ngayThue;
                 phieuThue.tienSan = pt.tienSan;
                 phieuThue.danhGia = 1;
                 phieuThue.sao = rating;
                 phieuThue.phanHoi = ed_danh_gia_text.getText().toString().trim();
                 if (phieuThueDAO.update(phieuThue) > 0){
                     Toast.makeText(getContext(), "Gửi đánh giá thành công!!!", Toast.LENGTH_SHORT).show();
                     setListView();
                     dialog.dismiss();
                 }else {
                     Toast.makeText(getContext(), "Gửi đánh giá thất bại!!!", Toast.LENGTH_SHORT).show();
                 }
             }else {
                 Toast.makeText(getContext(), "Bạn chưa đánh giá", Toast.LENGTH_SHORT).show();
             }
         });


        dialog.show();
    }

    public boolean validateDel(String ca, String ngay){
        Date now = new Date();
//        int caInt = Integer.parseInt(ca);
//        if (caInt > 8){
//            ca = String.valueOf(caInt-2);
//        }
//        long posNow = Cover.NgayCaGioToPos(formatNgay.format(now), ca, formatGio.format(now));
//        long posPT = Cover.NgayCaGioToPos(ngay, ca, "");
//        Log.i("-----", "now "+posNow+" pt "+posPT+" rs:"+(posNow-posPT));
//        if (posNow <= posPT){
//            return true;
//        }
//        return false;
        Date datePT = Cover.ThoiGianThueToDate(ngay, ca);
        Date datePT2 = Cover.addHoursToDate(datePT, -2);
        Log.i("commmm ", ""+datePT+"\n"+datePT2);
        if (datePT2.compareTo(now) >= 0){
            return true;
            //Toast.makeText(getContext(), "có thể xóa", Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(getContext(), ""+datePT2.compareTo(now), Toast.LENGTH_SHORT).show();
//        Log.i("commmm ", "PT2:"+datePT2+"    now:"+format.format(now)+" => "+datePT2.compareTo(now));
        return false;
    }
}