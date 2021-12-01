package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.entity.San;

import java.util.ArrayList;

public class SpinnerSanAdapter extends ArrayAdapter<San> {

    private Context context;
    private ArrayList<San> listS;
    TextView tvTenSan;

    public SpinnerSanAdapter(@NonNull Context context, ArrayList<San> listS) {
        super(context,0,listS);
        this.context = context;
        this.listS = listS;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.san_item_spinner, null);
        }
        San san = listS.get(position);
        if (san != null){
            tvTenSan = view.findViewById(R.id.text_spinner_tenSan);
            tvTenSan.setText(san.tenSan);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.san_item_spinner, null);
        }
        San san = listS.get(position);
        if (san != null){
            tvTenSan = view.findViewById(R.id.text_spinner_tenSan);
            tvTenSan.setText(san.tenSan);
        }
        return view;
    }
}
