package com.example.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HistoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");
    }
}
