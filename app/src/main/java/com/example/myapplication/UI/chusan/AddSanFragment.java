package com.example.myapplication.UI.chusan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddSanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSanFragment extends Fragment {

    public AddSanFragment() {
        // Required empty public constructor
    }

    public static AddSanFragment newInstance(String param1, String param2) {
        AddSanFragment fragment = new AddSanFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_san, container, false);
    }
}