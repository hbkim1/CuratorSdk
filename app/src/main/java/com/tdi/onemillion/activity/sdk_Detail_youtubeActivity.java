package com.tdi.onemillion.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.tdi.onemillion.R;
import com.tdi.onemillion.rest.SDKRestService;
import com.tdi.onemillion.rest.model.sdk_articlesmodel;
import com.tdi.onemillion.util.sdk_FullscreenableChromeClient;

import java.net.URISyntaxException;

import static com.tdi.onemillion.sdk_Constant.LOG_TAG;


public class sdk_Detail_youtubeActivity extends AppCompatActivity implements SDKRestService.DetailListListener {


    private SDKRestService SDKRestService;
    private Long article_id;
    private String videoid = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdk_activity_detail_youtube);

        if (getIntent() != null && getIntent().getExtras() != null) {

            article_id = getIntent().getExtras().getLong("article_id");
            Log.e(LOG_TAG, "teswt : " + getIntent().getExtras().getLong("article_id"));
        } else {
            finish();
        }

        init();

    }

    private void init() {


        SDKRestService = new SDKRestService(this);


        SDKRestService.getDetailList(article_id);
        SDKRestService.detaillistListener = this;

        ConstraintLayout toolbar_back = findViewById(R.id.toolbar_back);
        toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    @Override
    public void DetailListListener(sdk_articlesmodel data) {
        videoid = data.getUrl().split("v=")[1];

        Log.e(LOG_TAG, "videoid!!! " + videoid);
        setupWebView(videoid);


        TextView tv_content = findViewById(R.id.tv_content);
        tv_content.setText(data.getContents());


        TextView tv_content_title = findViewById(R.id.tv_content_title);
        tv_content_title.setText(data.getTitle());

        Button button = findViewById(R.id.go_web);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sdk_Detail_youtubeActivity.this, sdk_WebviewActivity.class);
                intent.putExtra("URL",data. getUrl());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {
        overridePendingTransition(0, 0);
        super.onResume();
        if (web_view != null) {
            web_view.onResume();
        }

    }

    private WebView web_view;

    @Override
    protected void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
        if (web_view != null) {
            web_view.onPause();
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void setupWebView(String videoid) {

        web_view = findViewById(R.id.web_view);
        ProgressBar pro_bar = findViewById(R.id.pro_bar);
        LinearLayout pro_layout = findViewById(R.id.pro_layout);


        WebSettings settings = web_view.getSettings();

        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);

        web_view.setBackgroundColor(Color.argb(1, 0, 0, 0));

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        web_view.setWebChromeClient(new sdk_FullscreenableChromeClient(this) {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                pro_bar.setProgress(newProgress);
            }
        });

        web_view.setWebViewClient(new WebViewClient() {

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

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

                pro_bar.setProgress(0);
                pro_layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pro_bar.setProgress(100);
                        pro_layout.setVisibility(View.GONE);
                    }
                }, 500);

            }
        });

        Log.e(LOG_TAG, "https://www.youtube.com/embed/" + videoid);
        web_view.loadUrl("https://www.youtube.com/embed/" + videoid);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (web_view.canGoBack()) {
            web_view.goBack();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void DetailErrorCode(int statusCode, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

