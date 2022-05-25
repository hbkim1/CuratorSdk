package com.tdi.onemillion.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.tdi.onemillion.R;

import java.net.URISyntaxException;
import java.net.URL;

import okhttp3.internal.Util;


public class sdk_WebviewActivity extends AppCompatActivity {

    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdk_activity_web_view);
//        hideSystemUI();

        if (getIntent() != null) {
            url = getIntent().getStringExtra("URL");
        } else {
            Toast.makeText(this, "불러오기 실패 \n 잠시후 시도하세요.", Toast.LENGTH_LONG);
            finish();
        }
        init();


    }

    public void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void init() {


        setUpWebView();
        ConstraintLayout toolbarBack = findViewById(R.id.toolbar_back);
        toolbarBack.setOnClickListener(v -> finish());
    }

    private WebView webView;
    private ProgressBar proBar;
    private LinearLayout proLayout;


    private void setUpWebView() {

        webView = findViewById(R.id.web_view);
        proBar = findViewById(R.id.pro_bar);
        proLayout = findViewById(R.id.pro_layout);

        WebSettings set = webView.getSettings();

        set.setRenderPriority(WebSettings.RenderPriority.HIGH);
        set.setBuiltInZoomControls(true);
        set.setSupportZoom(true);
        set.setDisplayZoomControls(false);
        set.setJavaScriptEnabled(true);
        set.setDomStorageEnabled(true);
        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        set.setEnableSmoothTransition(true);


        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

                proBar.setProgress(newProgress);

            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                /* 앱으로 이동하기 버튼 클릭시 넘어 오는 url*/

                if (url != null && url.startsWith("intent://")) {

                    try {
                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                        Intent existPackage = getPackageManager().getLaunchIntentForPackage(intent.getPackage());

                        /*existPackage 가 null 이면 미 설치 상태*/
                        if (existPackage != null) {
                            startActivity(intent);
                        } else {
                            Intent marketIntent = new Intent(Intent.ACTION_VIEW);
                            marketIntent.setData(Uri.parse("market://details?id=" + intent.getPackage()));
                            startActivity(marketIntent);
                        }
                        return true;

                    } catch (URISyntaxException e) {

                        e.printStackTrace();
                        return false;

                    }

                }


                Log.d("URL_LOG",url);

                if (url != null && url.startsWith("market://")) {

                    try {
                        Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);

                        if (intent != null) {
                            startActivity(intent);
                            finish();
                        }
                        return true;

                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }


                }


                return false;
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                proBar.setProgress(0);
                proLayout.setVisibility(View.VISIBLE);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Handler mHandler = new Handler();

                mHandler.postDelayed(() -> {
                    proBar.setProgress(100);
                    proLayout.setVisibility(View.GONE);
                }, 500);


            }
        });
        webView.loadUrl(url);
        Log.d("URL_LOG","init");



    }


    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo("com.instagram.android", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {

            webView.goBack();

        } else {

            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        webView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
