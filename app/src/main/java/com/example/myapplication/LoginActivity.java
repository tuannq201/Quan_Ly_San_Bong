package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.database.DbHelper;

public class LoginActivity extends AppCompatActivity {

    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userDAO = new UserDAO(LoginActivity.this);
    }
}