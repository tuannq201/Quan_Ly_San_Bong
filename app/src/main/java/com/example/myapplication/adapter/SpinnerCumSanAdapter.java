package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.example.myapplication.entity.CumSan;

import java.util.ArrayList;

public class SpinnerCumSanAdapter extends ArrayAdapter<CumSan> {
    private Context context;
    private ArrayList<CumSan> listC;
    TextView tvMaCumSan, tvTenCumSan;

    public SpinnerCumSanAdapter(@NonNull Context context, ArrayList<CumSan> listC) {
        super(context, 0, listC);
        this.context = context;
        this.listC = listC;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cum_san_item_spinner, null);
        }
        CumSan cumSan = listC.get(position);
        if (cumSan != null){
            tvTenCumSan = view.findViewById(R.id.text_spinner_tenCumSan);

            tvTenCumSan.setText(cumSan.tenCumSan);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cum_san_item_spinner, null);
        }
        CumSan cumSan = listC.get(position);
        if (cumSan != null){
            tvTenCumSan = view.findViewById(R.id.text_spinner_tenCumSan);
            tvTenCumSan.setText(cumSan.tenCumSan);
        }
        return view;
    }
}
