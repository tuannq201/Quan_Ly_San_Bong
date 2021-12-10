package com.example.myapplication.UI.nguoithue;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import static com.example.myapplication.util.Cover.getImageUri;
import static com.example.myapplication.util.Cover.imageViewToByteArray;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.User;
import com.example.myapplication.util.Cover;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    UserDAO userDAO;

    PhieuThueDAO phieuThueDAO;
    User user;
    EditText ed_1, ed_2;
    EditText ed_3;
    TextView tv_san, tv_tienThue;
    Button btnCapNhatNT, btnDangXuat;
    Intent intent;
    public static final String PHAN_QUYEN = "NT";
    Dialog dialog;
    Button  btn_camera, btn_album, btn_save;
    TextInputEditText ed_phone_number, ed_name, ed_password, ed_re_password;
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_FOLDER = 1;

    //CircleImageView circleImageView;
    ImageView iv_user_avata; //fragment
    ImageView iv_camera_result; //dialog
    String phone;
    public UserFragment() {
        // Required empty public constructor
    }

    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDAO = new UserDAO(getContext());
        phieuThueDAO = new PhieuThueDAO(getContext());
        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        phone = pref.getString("PHONE","");
        String pass = pref.getString("PASSWORD","");
        user = userDAO.getUser(phone);

        //Toast.makeText(getContext(), ""+user.taiKhoan, Toast.LENGTH_SHORT).show();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        iv_user_avata = v.findViewById(R.id.profile_image);
        ed_1 = v.findViewById(R.id.ed_name_user);
        ed_2 = v.findViewById(R.id.ed_phone_user);
        ed_3 = v.findViewById(R.id.ed_pass_user);
        btnCapNhatNT = v.findViewById(R.id.btnCapNhatNT);
        btnDangXuat = v.findViewById(R.id.btnDangXuat);
        tv_san = v.findViewById(R.id.tv_tk_soSan_use);
        tv_tienThue = v.findViewById(R.id.tv_tk_soTien_use);

        btnDangXuat.setOnClickListener(view -> {
            intent = new Intent(this.getContext(), LoginActivity.class);
            SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            edit.putBoolean("REMEMBER",false);
            edit.commit();
            startActivity(intent);
        });
        btnCapNhatNT.setOnClickListener(view -> {
            openDialog(1);
        });
        setAvatar();

        return v;
    }
    public void openDialog(final int type){
        dialog = new Dialog(getContext());
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

        if (type != 0){
            ed_phone_number.setText(user.taiKhoan);
            ed_phone_number.setEnabled(false);
            ed_name.setText(user.hoTen);
            ed_password.setText(user.matKhau);
            ed_re_password.setText(user.matKhau);
            btn_save.setText("Cập Nhật");
            try {
                iv_camera_result.setImageBitmap(Cover.ByteToBitmap(user.hinhAnh));
            }catch (Exception e){

            }
        }

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
                String name = ed_name.getText().toString().trim();
                String phone_number = ed_phone_number.getText().toString().trim();
                String password = ed_password.getText().toString().trim();
                user = new User();
                user.hoTen = name;
                user.phanQuyen = PHAN_QUYEN;
                user.taiKhoan = phone_number;
                user.matKhau = password;
                user.hinhAnh = imageViewToByteArray(iv_camera_result);
                if (validate()>0){
                    if (type == 0){

                    }else {
                        if (userDAO.update(user) > 0){
                            ed_1.setText(""+user.hoTen);
                            ed_2.setText(user.taiKhoan);
                            ed_3.setText(user.matKhau);
                            Toast.makeText(getContext(), "Cập nhật tài khoản thành công", Toast.LENGTH_SHORT).show();
                            setAvatar();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Uri uri = getImageUri(getContext(), bitmap);
            CropImage.activity(uri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setCropShape(CropImageView.CropShape.OVAL)
                    .setActivityTitle("Cắt xén")
                    .setCropMenuCropButtonTitle("Lưu")
                    .setFixAspectRatio(true)
                    .start(getContext(), this);
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
                        .start(getContext(), this);
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
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin !",Toast.LENGTH_LONG).show();
            check = -1;
        }else{
            String sdt = ed_phone_number.getText().toString();
            String regexSDT = "^(03|05|07|08|09|01[2|6|8|9])+([0-9]{8})$";
            if (sdt.matches(regexSDT) == false){
                Toast.makeText(getContext(),"Số điện thoại không hợp lệ",Toast.LENGTH_LONG).show();
                check = -1;
            }
        }
        String pass = ed_password.getText().toString();
        String rePass = ed_re_password.getText().toString();
        if (!pass.equals(rePass)) {
            Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
            check = -1;
        }
        return check;
    }
    public void setAvatar(){
        if (user.hinhAnh != null){
            byte[] hinh = user.hinhAnh;
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
            iv_user_avata.setImageBitmap(bitmap);
            //iv_camera_result.setImageBitmap(bitmap);
        }
        ed_1.setText(""+user.hoTen);
        ed_2.setText(user.taiKhoan);
        ed_3.setText(user.matKhau);
        tv_san.setText("Sân đã thuê: "+phieuThueDAO.getPhieuByUser(phone).size()+" sân");
        tv_tienThue.setText("Tổng tiền: "+ Cover.IntegerToVnd(phieuThueDAO.AllTienThueNT(phone))+"vnđ");
    }

}