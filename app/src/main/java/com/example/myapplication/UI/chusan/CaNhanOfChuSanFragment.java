package com.example.myapplication.UI.chusan;

import static android.content.Context.MODE_PRIVATE;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.User;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaNhanOfChuSanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaNhanOfChuSanFragment extends Fragment {

    UserDAO userDAO;
    User user;
    EditText ed_1, ed_2, ed_3, ed_4;

    CircleImageView circleImageView;
    ImageView iv_user_avata;
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
        String phone = pref.getString("PHONE","");
        String pass = pref.getString("PASSWORD","");
        user = userDAO.getUser(phone);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ca_nhan_of_chu_san, container, false);
        iv_user_avata = v.findViewById(R.id.profile_image);
        circleImageView = v.findViewById(R.id.profile_image);
        ed_1 = v.findViewById(R.id.ed_name_userf);
        ed_2 = v.findViewById(R.id.ed_phone_userf);
        ed_3 = v.findViewById(R.id.ed_pass_userf);
        ed_4 = v.findViewById(R.id.ed_cumsan);
        setAvatar();

        return v;
    }
    public void setAvatar(){
        if (user.hinhAnh != null){
            byte[] hinh = user.hinhAnh;
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
            iv_user_avata.setImageBitmap(bitmap);
            circleImageView.setImageBitmap(bitmap);
        }
        ed_1.setText(""+user.hoTen);
        ed_2.setText(user.taiKhoan);
        ed_3.setText(user.matKhau);
    }
}