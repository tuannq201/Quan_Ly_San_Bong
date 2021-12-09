package com.example.myapplication.UI.admin;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.myapplication.ADadapter.ChuSanAdapter;
import com.example.myapplication.R;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.User;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class ChuSanFragment extends Fragment {


    ArrayList<User> list;
    ChuSanAdapter adapter;
    SearchView searchView;
    ImageView imgAdd;
    ListView lv;
    UserDAO dao;
    User item;
    Dialog dialog;
    Button  btn_camera, btn_album, btn_save;
    TextInputEditText ed_phone_number, ed_name, ed_password, ed_re_password;
    ImageView iv_camera_result;
    public static final int REQUEST_CODE_CAMERA = 0;
    public static final int REQUEST_CODE_FOLDER = 1;
    public static final String PHAN_QUYEN = "CS";

    public ChuSanFragment() {
    }

    public static ChuSanFragment newInstance() {
        ChuSanFragment fragment = new ChuSanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chu_san, container, false);
        imgAdd = view.findViewById(R.id.imgThemCS);
        lv = view.findViewById(R.id.lvChuSan);
        dao = new UserDAO(getActivity());
        capNhatLV();


        //tìm user
        searchView = view.findViewById(R.id.svChuSan);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String s) {
               s = searchView.getQuery().toString();
               seachUser(s);
               return false;
           }

           @Override
           public boolean onQueryTextChange(String s) {
                   s = searchView.getQuery().toString();
                   seachUser(s);
               return false;
           }
       });
        //thêm user
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(0);
            }
        });
        //tìm trong list
//        String[] userList;
//        for (int i = 0; i < userList.length; i++) {
//            User animalNames = new User(userList[i]);
//            // Binds all strings into an array
//            list.add(animalNames);
//        }
//        adapter = new ListViewAdapter(this, arraylist);
//        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml

        //xóa user
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                xoa();
                return true;
            }
        });
        //cập nhât user
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                item = list.get(i);
                openDialog(1);
            }
        });
        return view;
    }

    public void seachUser(String TK){
        List<User> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            String tk = user.taiKhoan;
            if (tk.contains(TK)){
                list1.add(user);
            }
        }
        adapter = new ChuSanAdapter(getActivity(),this, (ArrayList<User>) list1);
        lv.setAdapter(adapter);
    }

    //mở dialog
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
            ed_phone_number.setText(item.taiKhoan);
            ed_phone_number.setEnabled(false);
            ed_name.setText(item.hoTen);
            ed_password.setText(item.matKhau);
            ed_re_password.setText(item.matKhau);
            btn_save.setText("Cập Nhật");
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
                item = new User();
                item.hoTen = name;
                item.phanQuyen = PHAN_QUYEN;
                item.taiKhoan = phone_number;
                item.matKhau = password;
                item.hinhAnh = imageViewToByteArray(iv_camera_result);
                if (validate()>0){
                    if (type == 0){
                        if (dao.insert(item) > 0){
                            Toast.makeText(getContext(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                            capNhatLV();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "Tạo tài khoản không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if (dao.update(item) > 0){
                            Toast.makeText(getContext(), "Cập nhật tài khoản thành công", Toast.LENGTH_SHORT).show();
                            capNhatLV();
                            dialog.dismiss();
                        }else {
                            Toast.makeText(getContext(), "Cập nhật khoản không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        dialog.show();
    }
    //cập nhật LV
    void capNhatLV(){
        list = (ArrayList<User>) dao.getPhanQuyen("CS");
        adapter = new ChuSanAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    //show dialog xóa
    public void xoa() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa Chủ Sân");
        builder.setMessage("Bạn có muốn xóa Không ?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dao.delete(item.taiKhoan);
                capNhatLV();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton(
                "Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }
        );
        AlertDialog alertDialog = builder.create();
        builder.show();
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
}