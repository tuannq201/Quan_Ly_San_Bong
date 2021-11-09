package com.example.myapplication.UI.chusan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.entity.San;
import com.example.myapplication.entity.User;

public class CaSanOfChuSanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ca_san_of_chu_san);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        San san = (San) bundle.get("object_san");
        TextView text = findViewById(R.id.text);
        text.setText(""+ san.maSan);

    }
}