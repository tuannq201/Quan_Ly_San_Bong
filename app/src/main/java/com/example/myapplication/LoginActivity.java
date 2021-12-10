package com.example.myapplication;

import static com.example.myapplication.util.Cover.getImageUri;
import static com.example.myapplication.util.Cover.imageViewToByteArray;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.UI.admin.AdminActivity;
import com.example.myapplication.UI.chusan.ChuSanActivity;
import com.example.myapplication.UI.nguoithue.NguoiThueActivity;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.TrangThai;
import com.example.myapplication.entity.User;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class LoginActivity extends AppCompatActivity {

    Dialog dialog;
    Button btn_login, btn_camera, btn_album, btn_save, btn_register;
    ImageView iv_camera_result, imgShowPass;
    CheckBox chk_remember;
    TextInputEditText  ed_name, ed_password,ed_phone_number ,ed_re_password;
    EditText ed_phone_login, ed_password_login;
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_FOLDER = 1;
    public static final String PHAN_QUYEN = "NT";
    UserDAO userDAO;
    int count = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imgShowPass = findViewById(R.id.show_pass);
        imgShowPass.setOnClickListener(v -> {
            if (count %2 == 0){
                ed_password_login.setTransformationMethod(null);
                count++;
            }else {
                ed_password_login.setTransformationMethod(new PasswordTransformationMethod());
                count++;
            }
        });
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
        //demo();

    }


    public void demo(){
        PhieuThueDAO phieuThueDAO = new PhieuThueDAO(LoginActivity.this);
        TrangThai trangThai = phieuThueDAO.checkTrangThai(1, "4","06-12-2021" );
        Log.i("nnnnn", ""+trangThai.toString());
    }


    public void Login(){
        String phone_number = ed_phone_login.getText().toString();
        String password = ed_password_login.getText().toString();
        if (userDAO.checkLogin(phone_number, password)){
            remember();
            //Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            if (userDAO.getUser(phone_number).phanQuyen.equals("AD")){
                startActivity(new Intent(LoginActivity.this, AdminActivity.class));
            }
            if (userDAO.getUser(phone_number).phanQuyen.equals("CS")){
                startActivity(new Intent(LoginActivity.this, ChuSanActivity.class));
            }
            if (userDAO.getUser(phone_number).phanQuyen.equals("NT")){
                startActivity(new Intent(LoginActivity.this, NguoiThueActivity.class));
            }
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
            Login();
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
        iv_camera_result = dialog.findViewById(R.id.iv_image_edit);
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
                if (validate()>0){
                    String name = ed_name.getText().toString().trim();
                    String phone_number = ed_phone_number.getText().toString().trim();
                    String password = ed_password.getText().toString().trim();
                    User user = new User();
                    user.hoTen = name;
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



        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Uri uri = getImageUri(LoginActivity.this, bitmap);
            CropImage.activity(uri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setCropShape(CropImageView.CropShape.OVAL)
                    .setActivityTitle("Cắt xén")
                    .setCropMenuCropButtonTitle("Lưu")
                    .setFixAspectRatio(true)
                    .start(this);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                CropImage.activity(uri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropShape(CropImageView.CropShape.OVAL)
                        .setActivityTitle("Cắt xén")
                        .setCropMenuCropButtonTitle("Lưu")
                        .setFixAspectRatio(true)
                        .start(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                iv_camera_result.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }


    }
    public int validate(){
        int check = 1 ;
        if (ed_phone_number.getText().length() == 0 || ed_name.getText().length() == 0 || ed_password.getText().length() == 0 || ed_re_password.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"Bạn phải nhập đầy đủ thông tin !",Toast.LENGTH_LONG).show();
            check = -1;
        }else{
            String sdt = ed_phone_number.getText().toString();
            String regexSDT = "^(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})$";
            if (sdt.matches(regexSDT) == false){
                Toast.makeText(getApplicationContext(),"Số điện thoại không hợp lệ",Toast.LENGTH_LONG).show();
                check = -1;
            }
        }
        String pass = ed_password.getText().toString();
        String rePass = ed_re_password.getText().toString();
        if (!pass.equals(rePass)) {
            Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
            check = -1;
        }
        return check;
    }
}