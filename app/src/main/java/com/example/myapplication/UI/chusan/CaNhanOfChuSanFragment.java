package com.example.myapplication.UI.chusan;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
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
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.dao.CumSanDAO;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.User;
import com.google.android.material.textfield.TextInputEditText;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaNhanOfChuSanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaNhanOfChuSanFragment extends Fragment {

    UserDAO userDAO;
    User user;
    TextView tvTaiKhoan, tvThemCumSan, tvDoiThongTin, tvLogOut;
    Button btn_camera, btn_album, btn_save, btn_cancelUser, btCancel, btSave;
    ImageView iv_camera_result;
    TextInputEditText ed_name, ed_password,ed_passwordOld ,ed_re_password;
    EditText edTenCumSan, edDiaChi;
    Dialog dialog;
    CircleImageView circleImageView;
    ImageView iv_user_avata;
    String phone, pass;
    CumSanDAO cumSanDAO;
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_FOLDER = 1;
    public static final String PHAN_QUYEN = "CS";
    public CaNhanOfChuSanFragment() {
        // Required empty public constructor
    }


    public static CaNhanOfChuSanFragment newInstance(String param1, String param2) {
        CaNhanOfChuSanFragment fragment = new CaNhanOfChuSanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userDAO = new UserDAO(getContext());
        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        phone = pref.getString("PHONE","");
        pass = pref.getString("PASSWORD","");
        user = userDAO.getUser(phone);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca_nhan_of_chu_san, container, false);
        iv_user_avata = v.findViewById(R.id.profile_image);
        circleImageView = v.findViewById(R.id.profile_image);
        tvTaiKhoan = v.findViewById(R.id.text_TaiKhoan);
        tvDoiThongTin = v.findViewById(R.id.text_doi_thong_tin);
        tvLogOut = v.findViewById(R.id.dang_xuat);
        tvLogOut.setOnClickListener(v1 -> {
            Intent intent = new Intent(this.getContext(), LoginActivity.class);
            SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            edit.putBoolean("REMEMBER",false);
            edit.commit();
            startActivity(intent);
        });
        tvThemCumSan = v.findViewById(R.id.text_them_cum_san);
        setAvatar();
        tvThemCumSan.setOnClickListener(v1 -> {
            onClickGoToCaSan();
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ChipNavigationBar chip = getActivity().findViewById(R.id.chip_navi_chu_san);
        chip.setVisibility(View.VISIBLE);
    }

    private void onClickGoToCaSan() {
        CumSanFragment fragment = new CumSanFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void setAvatar(){
        if (user.hinhAnh != null){
            byte[] hinh = user.hinhAnh;
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
            iv_user_avata.setImageBitmap(bitmap);
            circleImageView.setImageBitmap(bitmap);
        }
        tvTaiKhoan.setText(""+user.taiKhoan);
        tvDoiThongTin.setOnClickListener(v -> {
            openDialogThongTin();
        });
    }

    private void openDialogThongTin() {
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_edit_thong_tin);
        Window window = dialog.getWindow();
        window.getAttributes().windowAnimations = R.style.DialogAnimation;
        btn_camera = dialog.findViewById(R.id.btn_camera);
        btn_album = dialog.findViewById(R.id.btn_albuml);
        iv_camera_result = dialog.findViewById(R.id.iv_image_edit);
        btn_save = dialog.findViewById(R.id.btn_save);
        btn_cancelUser = dialog.findViewById(R.id.btn_cancel);
        ed_name = dialog.findViewById(R.id.ed_name);
        ed_passwordOld = dialog.findViewById(R.id.ed_phone_number);
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

        ed_name.setText(user.hoTen);
        ed_passwordOld.setText(user.matKhau);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()>0){
                    String name = ed_name.getText().toString().trim();
                    String password = ed_password.getText().toString().trim();
                    user.hoTen = name;
                    user.phanQuyen = PHAN_QUYEN;
                    user.taiKhoan = phone;
                    user.matKhau = password;
                    user.hinhAnh = imageViewToByteArray(iv_camera_result);
                    if (userDAO.update(user) > 0){
                        Toast.makeText(getContext(), "Sửa tài khoản thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getContext(), "Sửa tài khoản không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_cancelUser.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();
    }
    public int validate(){
        int check = 1 ;
        if (ed_passwordOld.getText().length() == 0 || ed_name.getText().length() == 0 || ed_password.getText().length() == 0 || ed_re_password.getText().length() == 0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin !",Toast.LENGTH_LONG).show();
            check = -1;
        }
        String pass = ed_password.getText().toString();
        String rePass = ed_re_password.getText().toString();
        if (!pass.equals(rePass)) {
            Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
            check = -1;
        }
        if (pass.length() < 6 || rePass.length() < 6){
            Toast.makeText(getContext(), "Mật khẩu phải từ 6-30 ký tự", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
    //lấy đường dẫn của Img
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    //chuyển bitmap thành byte
    public byte[] imageViewToByteArray(ImageView iv){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArray);
        return byteArray.toByteArray();
    }

    //open camera or thư viện
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Uri uri = getImageUri(getActivity(), bitmap);
            CropImage.activity(uri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setCropShape(CropImageView.CropShape.OVAL)
                    .setActivityTitle("Cắt xén")
                    .setCropMenuCropButtonTitle("Lưu")
                    .setFixAspectRatio(true)
                    .start(getContext(),this);
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
                        .start(getContext(),this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Log.e("//=========", "onActivityResult: "+resultUri);
                iv_camera_result.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }


    }
}