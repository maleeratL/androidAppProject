package com.example.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import Model.HistoryModel;
public class HistoryActivity extends BaseActivity {

    private ArrayAdapter<HistoryModel> adapter;
    private List<HistoryModel> readHistory;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");
    }
}
