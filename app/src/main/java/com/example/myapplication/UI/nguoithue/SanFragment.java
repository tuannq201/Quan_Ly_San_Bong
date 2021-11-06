package com.example.myapplication.UI.nguoithue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SanFragment extends Fragment {

    public SanFragment() {
        // Required empty public constructor
    }


    public static SanFragment newInstance(String param1, String param2) {
        SanFragment fragment = new SanFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_san, container, false);
        return v;
    }
}