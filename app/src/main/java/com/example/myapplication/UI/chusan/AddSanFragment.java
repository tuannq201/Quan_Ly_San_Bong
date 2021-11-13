package com.example.myapplication.UI.chusan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SpinnerCumSanAdapter;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.SanDAO;
import com.example.myapplication.entity.CumSan;
import com.example.myapplication.entity.San;
import com.example.myapplication.util.Cover;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSanFragment extends Fragment {

    EditText edTenSan, edLoaiSan, edGiaSan;
    Spinner spTenCumSan;
    Button btSave, btAlbum, btCamera;
    ImageView imgSan;
    SanDAO dao;
    List<CumSan> listCumSan;
    int maCumSan;
    SpinnerCumSanAdapter spinnerCumSanAdapter;
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_FOLDER = 1;
    public AddSanFragment() {
        // Required empty public constructor
    }

    public static AddSanFragment newInstance(String param1, String param2) {
        AddSanFragment fragment = new AddSanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_san, container, false);

//        FloatingActionButton fab = getActivity().findViewById(R.id.fab);
//        fab.setVisibility(View.INVISIBLE);
        spTenCumSan = view.findViewById(R.id.sp_tenCumSan);
        edTenSan = view.findViewById(R.id.ed_ten_san);
        edLoaiSan = view.findViewById(R.id.ed_loai_san);
        edGiaSan = view.findViewById(R.id.ed_gia_san);
        imgSan = view.findViewById(R.id.image_san);
        btCamera = view.findViewById(R.id.btn_camera);
        btAlbum = view.findViewById(R.id.btn_albuml);
        btSave = view.findViewById(R.id.btn_save);

        SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String phone = pref.getString("PHONE","");
        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        btAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        CumSanDAO cumSanDAO = new CumSanDAO(getContext());
        listCumSan = new ArrayList<>();
        listCumSan = cumSanDAO.getAll();
        spinnerCumSanAdapter = new SpinnerCumSanAdapter(getContext(), (ArrayList<CumSan>) listCumSan);
        spTenCumSan.setAdapter(spinnerCumSanAdapter);
        spTenCumSan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maCumSan = listCumSan.get(position).maCumSan;
                String tenCumSan = listCumSan.get(position).tenCumSan;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btSave.setOnClickListener(v -> {
            if (validate()){
                dao = new SanDAO(getContext());
                String tenSan = edTenSan.getText().toString().trim();
                String loaiSan = edLoaiSan.getText().toString().trim();
                int giaSan = Integer.parseInt(edGiaSan.getText().toString().trim());
                San san = new San();
                san.tenSan = tenSan;
                san.loaiSan = loaiSan;
                san.giaSan = giaSan;
                san.anhSan = Cover.imageViewToByteArray(imgSan);
                san.maCumSan = maCumSan;
                if (dao.insert(san) > 0){
                    Toast.makeText(getContext(), "Tạo sân thành công", Toast.LENGTH_SHORT).show();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container,new ListSanFragment());
                    transaction.commit();
                }else {
                    Toast.makeText(getContext(), "Tạo sân không thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgSan.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == Activity.RESULT_OK && data != null){
            Uri uri = data.getData();
            imgSan.setImageURI(uri);
        }
    }

    public boolean validate(){
        boolean check = true;
        if (edTenSan.getText().length() == 0 || edLoaiSan.getText().length() == 0|| edGiaSan.getText().length() == 0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = false;
        }
        return check;
    }
}