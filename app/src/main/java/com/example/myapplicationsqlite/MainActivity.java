package com.example.myapplicationsqlite;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.util.Patterns;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.webkit.URLUtil;
import android.webkit.WebBackForwardList;
import android.webkit.WebHistoryItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private WebView webView;
//    private Intent intent;
//    private  String text = null;

    FloatingActionButton fab, fab1, fab2;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){

            Log.d("AliAhmed", "A In1");
            setTheme(R.style.darktheme);
        }
        else{
            setTheme(R.style.AppTheme);
            Log.d("AliAhmed", "A In2");
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Home");

        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);

        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                animateFeb();
//            }
//        });
//        fab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                animateFeb();
//                Toast.makeText(MainActivity.this, "Camera fab clicked. Replace with your action", Toast.LENGTH_LONG).show();
//            }
//        });
//        fab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                animateFeb();
//                Toast.makeText(MainActivity.this, "Email fab clicked. Replace with your action", Toast.LENGTH_LONG).show();
//            }
//        });
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
//        String text = intent.getStringExtra("browserLink");
//        Log.d("SearchCheck","pass-input"+text);
        if(b !=null){
            Log.d("SearchCheck","Not-null"+b.getString("inputLink"));
            webView = (WebView) findViewById(R.id.webview);
            //make load in our app not browser app
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(b.getString("inputLink"));
        }
        else{
            webView = (WebView) findViewById(R.id.webview);
            //make load in our app not browser app
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("http://www.google.com");
        }
//        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
//
//            Log.d("AliAhmed", "B: In1");
//            setTheme(R.style.darktheme);
//        }
//        else{
//            setTheme(R.style.AppTheme);
//            Log.d("AliAhmed", "B: In2");
//        }


        setTheme(R.style.darktheme);


    }

    @Override
    public void onBackPressed(){
        if (webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                animateFeb();
//                WebBackForwardList currentList = webView.copyBackForwardList();
//                int currentSize = currentList.getSize();
//                for(int i = 0; i < currentSize; ++i)
//                {
//                    WebHistoryItem item = currentList.getItemAtIndex(i);
//                    String url = item.getUrl();
//                    Log.d("HistoryLink", "The URL at index: " + Integer.toString(i) + " is " + url );
//                }
                break;
            case R.id.fab1:
                animateFeb();
                Toast.makeText(MainActivity.this, "Camera fab clicked. Replace with your action", Toast.LENGTH_LONG).show();
                Intent launchIntent1 = getPackageManager().getLaunchIntentForPackage("com.google.android.youtube");
                if (launchIntent1 !=null){
                    startActivity(launchIntent1);
                }
                break;
            case R.id.fab2:
                animateFeb();
                Toast.makeText(MainActivity.this, "Email fab clicked. Replace with your action", Toast.LENGTH_LONG).show();
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.google.android.gm");
                if (launchIntent !=null){
                    startActivity(launchIntent);
                }
                break;
        }
    }

    private void animateFeb(){
        if (isOpen){
            fab.startAnimation(rotateForward);
            fab1.startAnimation(fabClose);
            fab2.startAnimation(fabClose);
            fab1.setClickable(false);
            fab2.setClickable(false);
            isOpen=false;
        }
        else{
            fab.startAnimation(rotateBackward);
            fab1.startAnimation(fabOpen);
            fab2.startAnimation(fabOpen);
            fab1.setClickable(true);
            fab2.setClickable(true);
            isOpen=true;
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
////        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            Toast.makeText(this,"Settings menu is clicked",Toast.LENGTH_LONG).show();
//            startActivity(new Intent(this,SettingsActivity.class));
//            return true;
//        }
//        else if (id == R.id.action_history){
//            Toast.makeText(this,"History menu is clicked",Toast.LENGTH_LONG).show();
//            startActivity(new Intent(this,HistoryActivity.class));
//            return true;
//        }
//        else if (id == R.id.app_bar_search){
//            Toast.makeText(this,"Search menu is clicked",Toast.LENGTH_LONG).show();
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }

    public void printBackForwardList() {
        WebBackForwardList currentList = webView.copyBackForwardList();
        int currentSize = currentList.getSize();
        for(int i = 0; i < currentSize; ++i)
        {
            WebHistoryItem item = currentList.getItemAtIndex(i);
            String url = item.getUrl();
            Log.d("HistoryLink", "The URL at index: " + Integer.toString(i) + " is " + url );
        }
//        Intent intent=new Intent();
//        setResult(2,intent);
    }



}
