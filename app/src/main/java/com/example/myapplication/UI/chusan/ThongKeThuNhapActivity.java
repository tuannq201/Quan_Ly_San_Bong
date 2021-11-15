package com.example.myapplication.UI.chusan;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ThongKeThuNhapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke_thu_nhap);
        BarChart barChart = findViewById(R.id.barChart);

        ArrayList<BarEntry> list = new ArrayList<>();
        list.add(new BarEntry(2015,100));
        list.add(new BarEntry(2016,200));
        list.add(new BarEntry(2017,300));
        list.add(new BarEntry(2018,400));
        list.add(new BarEntry(2019,500));
        list.add(new BarEntry(2020,600));
        list.add(new BarEntry(2021,700));
        BarDataSet barDataSet = new BarDataSet(list,"Test");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Test");
        barChart.animateY(2000);
    }
}