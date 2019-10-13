package com.example.myapplicationsqlite;

import Model.HistoryModel;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.URLUtil;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

public class BaseActivity extends AppCompatActivity {
    private WebView webView;
    private String searchText;
    private SearchView searchView;
    private Intent intent;
    private String pass;
    private String nameTitle;
    private String checkDuplicate="";
    String url;

//    private ArrayAdapter<HistoryModel> adapter;
//    private List<HistoryModel> readUser;
//    private ListView listView;

    private static final String EXTRA_TEXT ="com.example.myapplicationsqlite.EXTRA_TEXT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

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
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(BaseActivity.this,MainActivity.class);
                Bundle b = new Bundle();
                Log.d("SearchCheck","Submit");
//                startActivity(new Intent(BaseActivity.this,MainActivity.class));
                if (onQueryTextChange(searchText)) {
                    Log.d("CheckWeb", searchText);
                    if (Patterns.WEB_URL.matcher(searchText).matches() && URLUtil.isHttpUrl(searchText)) {
                        Log.d("CheckWebCondition", "http " + searchText);
                        webView = (WebView) findViewById(R.id.webview);
                        //make load in our app not browser app
                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl(searchText);
//                        WebSettings webSettings = webView.getSettings();
//                        webSettings.setJavaScriptEnabled(true);
                        pass = searchText;
                        Log.d("SearchCheck", "input1" + pass);
//                        startActivity(new Intent(BaseActivity.this,MainActivity.class));
//                        insertData();
                    } else {
                        Log.d("CheckWebCondition", searchText);
                        webView = (WebView) findViewById(R.id.webview);
                        //make load in our app not browser app
                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl("http://www.google.com/search?q=" + searchText);
//                        WebSettings webSettings = webView.getSettings();
//                        webSettings.setJavaScriptEnabled(true);
//                    searchView.clearFocus();

                        pass = "http://www.google.com/search?q=" + searchText;
                        Log.d("SearchCheck", "input2" + pass);

//                        insertData();

//                        startActivity(new Intent(BaseActivity.this,MainActivity.class));
                    }
//                    Log.d("SearchCheck","v1"+pass);
                    b.putString("inputLink", pass);
                    Log.d("CheckWebCondition", pass + "---");
                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
//                    nameTitle = webView.getTitle();
//                    Log.d("getTitle",nameTitle);
                    if (!checkDuplicate.equals(pass)){
                        insertData();
                        checkDuplicate=pass;
                    }
//                    intent.putExtra("browserLink",pass);
//                    startActivity(new Intent(BaseActivity.this,MainActivity.class));

                }
//                webView.setWebViewClient(new WebViewClient() {
//                    @Override
//                    public void onPageFinished(WebView view, String url) {
//                        Log.d("getTitle",view.getTitle());
//                    }
//
//                });


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchText = searchView.getQuery().toString();
                Log.d("SearchCheck",searchText);
                if(searchText !=null){
                    return true;
                }
                return false;
            }
        });
        return true;
//        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
////        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this,"Settings menu is clicked",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }
        else if (id == R.id.action_history){
            Toast.makeText(this,"History menu is clicked",Toast.LENGTH_LONG).show();
            startActivity(new Intent(this,HistoryActivity.class));
            return true;
        }
        else if (id == R.id.app_bar_search){
            Toast.makeText(this,"Search menu is clicked",Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.app_bookmark_icon){
            startActivity(new Intent(this,BookmarkActivity.class));
            //updateData(); use function of update history to update info and dispaly in Bookmarks page
            Toast.makeText(this,"Bookmarks menu is clicked",Toast.LENGTH_LONG).show();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    private void insertData(){
        String name = pass;
//        String title = nameTitle;
//        String email = et_email.getText().toString();
        if(!name.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.insertUser(name);
            finish();
        }
    }


//    private void updateData(String id){
//        String name = et_name.getText().toString();
//        String pass = et_pass.getText().toString();
//        String email = et_email.getText().toString();
//        if(!name.isEmpty()&&!pass.isEmpty()&&!email.isEmpty()){
//            DatabaseHelper helper = new DatabaseHelper(this);
//            helper.updateUser(id,name,pass,email);
//            finish();
//        }
//    }
}
