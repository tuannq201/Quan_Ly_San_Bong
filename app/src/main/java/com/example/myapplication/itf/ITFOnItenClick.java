package com.example.myapplication.itf;

import com.example.myapplication.entity.PhieuThue;
import com.example.myapplication.entity.San;

public interface ITFOnItenClick {
    void onItemClick(San san, int type);
    void onItemClick(PhieuThue phieuThue);

}
