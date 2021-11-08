package com.example.myapplication.UI.chusan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.UI.BottomNavigationBehavior;
import com.example.myapplication.UI.nguoithue.SanDaThueFragment;
import com.example.myapplication.UI.nguoithue.SanFragment;
import com.example.myapplication.UI.nguoithue.UserFragment;
import com.example.myapplication.entity.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ChuSanActivity extends AppCompatActivity {

    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chu_san);

        fab = findViewById(R.id.fab);
        loadFragment(new ListSanFragment());
        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.nav_bottom_chu_san);
        navigationView.setBackground(null);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment;
                switch (item.getItemId()){
                    case R.id.nav_chon_san_cs:
                        fragment = new ListSanFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.nav_ca_nhan:
                        fragment = new CaSanFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
        fab.setOnClickListener(v -> {
//            loadFragment(new );
        });

    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
//    private void openDialog(){
//        dialog = new Dialog(LoginActivity.this);
//        dialog.setContentView(R.layout.dialog_register_user);
//        Window window = dialog.getWindow();
//        window.getAttributes().windowAnimations = R.style.DialogAnimation;
//        btn_camera = dialog.findViewById(R.id.btn_camera);
//        btn_album = dialog.findViewById(R.id.btn_albuml);
//        iv_camera_result = dialog.findViewById(R.id.iv_image_register);
//        btn_save = dialog.findViewById(R.id.btn_save);
//        ed_name = dialog.findViewById(R.id.ed_name);
//        ed_phone_number = dialog.findViewById(R.id.ed_phone_number);
//        ed_password = dialog.findViewById(R.id.ed_password);
//        ed_re_password = dialog.findViewById(R.id.ed_re_password);
//
//        btn_camera.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
//            }
//        });
//        btn_album.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, REQUEST_CODE_FOLDER);
//            }
//        });
//
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (validate()){
//                    String name = ed_name.getText().toString().trim();
//                    String phone_number = ed_phone_number.getText().toString().trim();
//                    String password = ed_password.getText().toString().trim();
//                    User user = new User();
//                    user.ten = name;
//                    user.phanQuyen = PHAN_QUYEN;
//                    user.taiKhoan = phone_number;
//                    user.matKhau = password;
//                    user.hinhAnh = imageViewToByteArray(iv_camera_result);
//                    if (userDAO.insert(user) > 0){
//                        Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
//                        dialog.dismiss();
//                    }else {
//                        Toast.makeText(getApplicationContext(), "Tạo tài khoản không thành công", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//
//
//
//        dialog.show();
//    }
}