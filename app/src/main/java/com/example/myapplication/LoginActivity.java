package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.User;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class LoginActivity extends AppCompatActivity {

    Dialog dialog;
    Button btn_login, btn_camera, btn_album, btn_save, btn_register;
    ImageView iv_camera_result;
    CheckBox chk_remember;
    TextInputEditText ed_phone_number, ed_name, ed_password, ed_re_password, ed_phone_login, ed_password_login;
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_FOLDER = 1;
    public static final String PHAN_QUYEN = "NT";
    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn_login = findViewById(R.id.btn_login);
        ed_password_login = findViewById(R.id.ed_password_login);
        ed_phone_login = findViewById(R.id.ed_phone_number_login);
        chk_remember = findViewById(R.id.chk_remember);
        btn_register = findViewById(R.id.btn_register_user);

        userDAO = new UserDAO(LoginActivity.this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        checkRemember();


    }

    public void Login(){
        String phone_number = ed_phone_login.getText().toString().trim();
        String password = ed_password_login.getText().toString().trim();
        if (userDAO.checkLogin(phone_number, password)){
            remember();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Số điện thoại hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }
    }

    public void remember(){
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("PHONE",ed_phone_login.getText().toString().trim());
        edit.putString("PASSWORD",ed_password_login.getText().toString().trim());
        edit.putBoolean("REMEMBER",chk_remember.isChecked());
        edit.commit();
    }

    private void checkRemember(){
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String phone = pref.getString("PHONE","");
        String pass = pref.getString("PASSWORD","");
        boolean remember = pref.getBoolean("REMEMBER", false);
        if (remember == true){
            ed_phone_login.setText(phone);
            ed_password_login.setText(pass);
            chk_remember.setChecked(remember);
        }else{
            SharedPreferences.Editor edit = pref.edit();
            edit.clear();
            edit.commit();
        }

    }

    public void openDialog(){
        dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.dialog_register_user);
        Window window = dialog.getWindow();
        window.getAttributes().windowAnimations = R.style.DialogAnimation;
        btn_camera = dialog.findViewById(R.id.btn_camera);
        btn_album = dialog.findViewById(R.id.btn_albuml);
        iv_camera_result = dialog.findViewById(R.id.iv_image_register);
        btn_save = dialog.findViewById(R.id.btn_save);
        ed_name = dialog.findViewById(R.id.ed_name);
        ed_phone_number = dialog.findViewById(R.id.ed_phone_number);
        ed_password = dialog.findViewById(R.id.ed_password);
        ed_re_password = dialog.findViewById(R.id.ed_re_password);

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        btn_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    String name = ed_name.getText().toString().trim();
                    String phone_number = ed_phone_number.getText().toString().trim();
                    String password = ed_password.getText().toString().trim();
                    User user = new User();
                    user.ten = name;
                    user.phanQuyen = PHAN_QUYEN;
                    user.taiKhoan = phone_number;
                    user.matKhau = password;
                    user.hinhAnh = imageViewToByteArray(iv_camera_result);
                    if (userDAO.insert(user) > 0){
                        Toast.makeText(getApplicationContext(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getApplicationContext(), "Tạo tài khoản không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        dialog.show();
    }

    public byte[] imageViewToByteArray(ImageView iv){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArray);
        return byteArray.toByteArray();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            iv_camera_result.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                iv_camera_result.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean validate(){
        return true;
    }
}