package com.example.myapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

public class Cover {

    public static byte[] imageViewToByteArray(ImageView iv){
        BitmapDrawable bitmapDrawable = (BitmapDrawable) iv.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArray);
        return byteArray.toByteArray();
    }

    public static Bitmap ByteToBitmap(byte[] bytes){
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bitmap;
    }
    public static String caToTime(String ca){
        if (ca.equals("1")){
            return "6:00-7:00";
        }
        if (ca.equals("2")){
            return "7:15-8:15";
        }
        if (ca.equals("3")){
            return "8:30-9:30";
        }
        if (ca.equals("4")){
            return "9:45-10:45";
        }
        if (ca.equals("5")){
            return "11:00-12:00";
        }
        if (ca.equals("6")){
            return "12:15-13:15";
        }
        if (ca.equals("7")){
            return "13:30-14:30";
        }
        if (ca.equals("8")){
            return "14:45-15:45";
        }
        if (ca.equals("9")){
            return "16:00-17:00";
        }
        if (ca.equals("10")){
            return "17:15-18:15";
        }
        if (ca.equals("11")){
            return "18:30-19:30";
        }
        if (ca.equals("12")){
            return "19:45-20:45";
        }
        return ca;
    }
    public static float KhuyenMai(String ca ,int gia){
        if (ca.equals("4")||ca.equals("5")||ca.equals("6")||ca.equals("7")){
            return (float) ((float) gia*0.3);
        }
        return gia;
    }
    public static int KhuyenMai1(String ca){
        if (ca.equals("4")||ca.equals("5")||ca.equals("6")||ca.equals("7")){
            return 30;
        }
        return 10;
    }

    public static String IntegerToVnd(int so){
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(so);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void loadFragment(Context context, Fragment fragment) {
        // load fragment
        FragmentManager manager = context.getSystemService(FragmentManager.class);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.cst_nguoi_thue, fragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }
}
