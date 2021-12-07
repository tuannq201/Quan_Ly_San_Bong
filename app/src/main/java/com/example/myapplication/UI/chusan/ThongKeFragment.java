package com.example.myapplication.UI.chusan;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.example.myapplication.R;
import com.example.myapplication.dao.PhieuThueDAO;
import com.example.myapplication.util.Cover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ThongKeFragment extends Fragment {

    AnyChartView anyChartView;
    TextView tvThuNhap;


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
        anyChartView = view.findViewById(R.id.chartThongKe);
        tvThuNhap = view.findViewById(R.id.tvThuNhap);


        Cartesian cartesian = AnyChart.column();
        PhieuThueDAO dao = new PhieuThueDAO(getContext());
        Date now;
        Date newDate;
        int i;
        ArrayList<String> list = new ArrayList<>();
        List<DataEntry> data = new ArrayList<>();
        int sum = 0;
        for (i = 1; i <= 7; i++) {
            now = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.DAY_OF_YEAR, +i);
            newDate = calendar.getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
            simpleDateFormat.format(now);
            //Log.e("//======", "onCreateView: "+simpleDateFormat.format(newDate));
            list.add(simpleDateFormat.format(newDate));
            int thu = dao.thuNhap(simpleDateFormat.format(newDate));
            data.add(new ValueDataEntry(simpleDateFormat.format(newDate), thu));
            sum = sum + thu;
        }
        tvThuNhap.setText("Thu Nhập Một Tuần Tới :"+ Cover.IntegerToVnd(sum)+" VNĐ");

        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(5d)
                .offsetY(5d)
                .fontColor("#FFFFFF")
                .format("{%Value}{groupsSeparator: } VNĐ");
        column.color("#39A459");

        cartesian.animation(true);
        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: } VNĐ");
        cartesian.yAxis(0).labels().fontColor("#000000");
        cartesian.yAxis(0).labels().fontStyle();

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("Ngày Trong Tuần");
        anyChartView.setChart(cartesian);


        return view;

    }
}