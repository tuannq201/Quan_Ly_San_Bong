package com.example.myapplication.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

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
        if (ca.contains("1")){
            return "6:00-7:00";
        }
        if (ca.contains("2")){
            return "7:30-8:30";
        }
        if (ca.contains("3")){
            return "9:00-10:00";
        }
        if (ca.contains("4")){
            return "10:30-11:30";
        }
        if (ca.contains("5")){
            return "12:00-13:00";
        }
        if (ca.contains("6")){
            return "13:30-14:30";
        }
        if (ca.contains("7")){
            return "15:00-16:00";
        }
        if (ca.contains("8")){
            return "16:30-17:30";
        }
        if (ca.contains("9")){
            return "18:00-19:00";
        }
        if (ca.contains("10")){
            return "19:30-20:30";
        }
        if (ca.contains("11")){
            return "21:00-22:00";
        }
        if (ca.contains("12")){
            return "22:30-23:30";
        }
        return ca;
    }
}
