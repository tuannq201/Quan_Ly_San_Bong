package com.example.myapplication.UI.nguoithue;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.entity.User;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    UserDAO userDAO;
    User user;

    CircleImageView circleImageView;
    ImageView iv_user_avata;
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
        SharedPreferences pref = getContext().getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String phone = pref.getString("PHONE","");
        String pass = pref.getString("PASSWORD","");
        user = userDAO.getUser(phone);
        Toast.makeText(getContext(), ""+user.taiKhoan, Toast.LENGTH_SHORT).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user, container, false);
        iv_user_avata = v.findViewById(R.id.iv_user_avata);

        circleImageView = v.findViewById(R.id.profile_image);

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
    }
}