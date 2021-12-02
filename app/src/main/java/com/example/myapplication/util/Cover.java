package com.example.myapplication.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
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
import java.util.List;
import java.util.Locale;

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
    public static int KhuyenMaiTheoPhanTram(int phanTram,int gia){
        if (phanTram == 10){
            return gia - gia*10/100;
        }
        if (phanTram == 20){
            return gia - gia*20/100;
        }
        if (phanTram == 30){
            return gia - gia*30/100;
        }
        if (phanTram == 40){
            return gia - gia*40/100;
        }
        if (phanTram == 50){
            return gia - gia*50/100;
        }
        if (phanTram == 60){
            return gia - gia*60/100;
        }
        if (phanTram == 70){
            return gia - gia*70/100;
        }if (phanTram == 80){
            return gia - gia*80/100;
        }
        if (phanTram == 90){
            return gia - gia*90/100;
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
        String posDate = String.valueOf(dateToPos(ngay, ca, 1));//= ngày
        String posGio = "";
        if (gio.length() == 5){
            posGio = gio.replaceAll(":", "");
            String rs = posDate + posGio;
            return Long.parseLong(rs);
        }
        ca = ca.trim();
        if (ca.equals("1")){
            posGio = "0700";
        }
        if (ca.equals("2")){
            posGio = "0815";
        }
        if (ca.equals("3")){
            posGio = "0930";
        }
        if (ca.equals("4")){
            posGio = "1045";
        }
        if (ca.equals("5")){
            posGio = "1200";
        }
        if (ca.equals("6")){
            posGio = "1315";
        }
        if (ca.equals("7")){
            posGio = "1430";
        }
        if (ca.equals("8")){
            posGio = "1545";
        }
        if (ca.equals("9")){
            posGio = "1700";
        }
        if (ca.equals("10")){
            posGio = "1815";
        }
        if (ca.equals("11")){
            posGio = "1930";
        }
        if (ca.equals("12")){
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




    public static String getCompleteAddressString(Context context, double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

}
