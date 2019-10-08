# Android App Project
Developing Andriod application from Andriod Studio

## Details
Using WebView to browse Internet from app
```
<manifest ... >
    <uses-permission android:name="android.permission.INTERNET" />
    ...
</manifest>
```
```
WebView myWebView = (WebView) findViewById(R.id.webview);
myWebView.loadUrl("http://www.example.com");
```
https://developer.android.com/guide/webapps/webview.html
