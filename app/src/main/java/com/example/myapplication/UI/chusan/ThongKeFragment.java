package com.example.myapplication.UI.chusan;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class ThongKeFragment extends Fragment {

    BarChart barChart;


    public ThongKeFragment() {
        // Required empty public constructor
    }

    public static ThongKeFragment newInstance() {
        ThongKeFragment fragment = new ThongKeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke, container, false);
//        btnTK = view.findViewById(R.id.btnThongKe);
//        btnTK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getActivity(),ThongKeThuNhapActivity.class));
//            }
//        });
         barChart = view.findViewById(R.id.barChart);

        ArrayList<BarEntry> list = new ArrayList<>();
        list.add(new BarEntry(1,300));
        list.add(new BarEntry(2,200));
        list.add(new BarEntry(3,600));
        list.add(new BarEntry(4,400));
        list.add(new BarEntry(5,450));
        list.add(new BarEntry(6,700));
        list.add(new BarEntry(7,400));

        String[] dayOfWeek = new String[] {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new ngayBieuDo(dayOfWeek));
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        BarDataSet barDataSet = new BarDataSet(list,"Thu nhập tuần trước");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("");
        barChart.animateY(2000);
        return view;
    }
    public class ngayBieuDo extends ValueFormatter implements IAxisValueFormatter{

        private String[] mValues;
        public ngayBieuDo(String[] values){
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return mValues[(int)value];
        }
    }
}