package com.shreyas.tabbednews.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.shreyas.tabbednews.R;

public class MyWebView extends AppCompatActivity {

    private WebView webView;
    private String webUrl;
    private TextView mTvLoading;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);
        mTvLoading = findViewById(R.id.tv_webLoading);

        webUrl = getIntent().getStringExtra("myWebUrl");
        Log.d("web_view_url", "onCreate: "+webUrl);

        webView = findViewById(R.id.wbView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mTvLoading.setVisibility(View.VISIBLE);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mTvLoading.setVisibility(View.GONE);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                mTvLoading.setVisibility(View.GONE);
            }
        } );
        webView.loadUrl(webUrl);

    }
}
