package com.example.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    private WebView webView;
    private String searchText;
    private SearchView searchView;
    private Intent intent;
    private String pass;

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
                startActivity(new Intent(BaseActivity.this,MainActivity.class));
                if (onQueryTextChange(searchText)){
                    Log.d("CheckWeb",searchText);
                    if(Patterns.WEB_URL.matcher(searchText).matches()&& URLUtil.isHttpUrl(searchText)){
                        Log.d("CheckWebCondition",searchText);
                        webView = (WebView) findViewById(R.id.webview);
                        //make load in our app not browser app
                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl(searchText);
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                        pass = searchText;
                        Log.d("SearchCheck","input1"+pass);
//                        startActivity(new Intent(BaseActivity.this,MainActivity.class));
                    }
                    else{
                        Log.d("CheckWebCondition",searchText);
                        webView = (WebView) findViewById(R.id.webview);
                        //make load in our app not browser app
                        webView.setWebViewClient(new WebViewClient());
                        webView.loadUrl("http://www.google.com/search?q="+searchText);
                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
//                    searchView.clearFocus();

                        pass = "http://www.google.com/search?q="+searchText;
                        Log.d("SearchCheck","input2"+pass);


//                        startActivity(new Intent(BaseActivity.this,MainActivity.class));
                    }
//                    Log.d("SearchCheck","v1"+pass);
                    b.putString("inputLink", pass);

                    intent.putExtras(b);
                    startActivity(intent);
                    finish();
//                    intent.putExtra("browserLink",pass);
//                    startActivity(new Intent(BaseActivity.this,MainActivity.class));
                }

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



        return super.onOptionsItemSelected(item);
    }
}
