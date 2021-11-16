package com.example.myapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.Log;
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

    public static String formater(int y, int m, int d){
        String strDate = "";
        m = m+1;
        String strY = String.valueOf(y);
        String strM = (m < 10)?"0"+m:String.valueOf(m);
        String strD = (d < 10)?"0"+d:String.valueOf(d);
        strDate = strD+"-"+strM+"-"+strY;
        return strDate;
    }

    public static int dateToPos(String ngay, String ca, int type){
        //input: 20-11-2021, ca 4
        //output: 202111204
        String y = ngay.substring(6, ngay.length());
        String m = ngay.substring(3, 5);
        String d = ngay.substring(0, 2);
        Log.i("=====", y+" "+m+" "+d);
        if (type == 0){
            //pos = ngay + ca
            if (ca.length() == 1){
                ca = "0"+ca;
            }
            String strPos = y+m+d+ca;
            return Integer.parseInt(strPos);
        }
        String strPos = y+m+d;
        return Integer.parseInt(strPos);
    }


    public static long NgayCaGioToPos(String ngay,String ca, String gio){
        String posDate = String.valueOf(dateToPos(ngay, ca, 1));
        String posGio = "";
        if (gio.length() == 5){
            posGio = gio.replaceAll(":", "");
            String rs = posDate + posGio;
            return Long.parseLong(rs);
        }
        if (ca == "1"){
            posGio = "0700";
        }
        if (ca == "2"){
            posGio = "0815";
        }
        if (ca == "3"){
            posGio = "0930";
        }
        if (ca == "4"){
            posGio = "1045";
        }
        if (ca == "5"){
            posGio = "1200";
        }
        if (ca == "6"){
            posGio = "1315";
        }
        if (ca == "7"){
            posGio = "1430";
        }
        if (ca == "8"){
            posGio = "1545";
        }
        if (ca == "9"){
            posGio = "1700";
        }
        if (ca == "10"){
            posGio = "1815";
        }
        if (ca == "11"){
            posGio = "1930";
        }
        if (ca == "12"){
            posGio = "2045";
        }
        String rs = posDate+posGio;
        //return Integer.parseInt(rs);
        return Long.parseLong(rs);
    }

    public static int caToPos(String ca){
        String posGio = "";
        if (ca.equals("1")){
            posGio = "600";
        }
        if (ca.equals("2")){
            posGio = "715";
        }
        if (ca.equals("3")){
            posGio = "830";
        }
        if (ca.equals("4")){
            posGio = "945";
        }
        if (ca.equals("5")){
            posGio = "1100";
        }
        if (ca.equals("6")){
            posGio = "1215";
        }
        if (ca.equals("7")){
            posGio = "1330";
        }
        if (ca.equals("8")){
            posGio = "1445";
        }
        if (ca.equals("9")){
            posGio = "1600";
        }
        if (ca.equals("10")){
            posGio = "1715";
        }
        if (ca.equals("11")){
            posGio = "1830";
        }
        if (ca.equals("12")){
            posGio = "1945";
        }

        return Integer.parseInt(posGio);
    }

    public static int hourToPos(String gio){
        gio =  gio.replaceAll(":","");
        return Integer.parseInt(gio);
    }

}
