package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.dao.UserDAO;

public class LoginActivity extends AppCompatActivity {

    UserDAO userDAO;
    Button btn_register, btn_camera, btn_albuml;
    Dialog dialog;
    ImageView iv_image;
    public static final int  REQUEST_CODE_CAMERA = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_register = findViewById(R.id.btn_register_user);
        iv_image = findViewById(R.id.iv_image_register);
        userDAO = new UserDAO(LoginActivity.this);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        String tsst = "sadsda";
    }

    public void openCamera(View v){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && requestCode == RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Toast.makeText(getApplicationContext(), "onActivityResl", Toast.LENGTH_SHORT).show();
            iv_image.setImageBitmap(bitmap);
        }
    }

    //dialog đăng ký tài khoản cho người thuê
    public void openDialog(){
        dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.dialog_register_user);
        dialog.show();
    }
}