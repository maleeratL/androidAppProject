package com.example.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import Model.HistoryModel;
public class HistoryActivity extends BaseActivity {
    private String searchText;
    private SearchView searchView;

    private ArrayAdapter<HistoryModel> adapter;
    private List<HistoryModel> readHistory;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");
//        Intent intent=new Intent(HistoryActivity.this,MainActivity.class);
//        startActivityForResult(intent, 2);// Activity is started with requestCode 2
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);
        searchView = (SearchView)searchItem.getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("HistoryCheck","submit");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("HistoryCheck","typeInput");
                return false;
            }
        });


        return true;
//        return super.onCreateOptionsMenu(menu);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getUser();
    }

    private void getUser() {
        readHistory = new ArrayList<HistoryModel>();
        DatabaseHelper helper = new DatabaseHelper(this);
        readHistory = helper.readUser();

        if (readHistory.size() != 0) {
            listView = (ListView) findViewById(R.id.history_list);
            adapter = new ArrayAdapter<HistoryModel>(this, android.R.layout.simple_list_item_1, android.R.id.text1, readHistory);
            listView.setAdapter(adapter);
            Log.i("Adapter OnCreate", readHistory.size() + "");
            registerForContextMenu(listView);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo  info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        Log.i("INFO",info.position+"");
        menu.setHeaderTitle("Edit");
        menu.add("update");
        menu.add("delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo  info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        Log.i("INFO",position+"");
        if(item.getTitle().equals("update")){
            String id = readHistory.get(position).getId();
            Log.i("UPDATE ID",id +"");
            Intent i = new Intent(HistoryActivity.this, BaseActivity.class);
            i.putExtra("position",id);
            startActivity(i);
        }else if (item.getTitle().equals("delete")){
            String id = readHistory.get(position).getId();
            Log.i("DELETE ID",id);
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.deleteUser(id);
            readHistory = helper.readUser();
            Log.i("DELETE", readHistory.size() + "");
            adapter.clear();
            adapter.addAll(readHistory);
            adapter.notifyDataSetChanged();
        }

        return true;

    }
}
