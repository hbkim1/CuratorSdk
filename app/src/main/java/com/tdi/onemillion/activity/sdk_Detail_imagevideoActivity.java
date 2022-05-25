package com.tdi.onemillion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.tdi.onemillion.R;
import com.tdi.onemillion.adapter.sdk_ImageSliderAdapter;
import com.tdi.onemillion.rest.SDKRestService;
import com.tdi.onemillion.rest.model.sdk_articlesmodel;
import com.tdi.onemillion.util.sdk_CustomExoPlayerView;

import static com.tdi.onemillion.sdk_Constant.LOG_TAG;


public class sdk_Detail_imagevideoActivity extends AppCompatActivity implements com.tdi.onemillion.rest.SDKRestService.DetailListListener {


    private SDKRestService SDKRestService;
    private Long article_id;
    private LinearLayout layoutIndicator;
    private ViewPager2 viewPager;
    private sdk_CustomExoPlayerView customExoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdk_activity_detail_imagevideo);


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

        TextView title_text = findViewById(R.id.title_text);
        title_text.setText(data.getPlatform());


        TextView tv_content = findViewById(R.id.tv_content);
        tv_content.setText(data.getContents());

        initAdapter(data);

        Button button = findViewById(R.id.go_web);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sdk_Detail_imagevideoActivity.this, sdk_WebviewActivity.class);
                intent.putExtra("URL",data.getUrl());
                startActivity(intent);

                /*Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);*/


            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (adapter != null) {
            adapter.releasePlayer();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.releasePlayer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapter != null) {
            adapter.releasePlayer();
        }
    }

    private sdk_ImageSliderAdapter adapter;

    private void initAdapter(sdk_articlesmodel data) {
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        adapter = new sdk_ImageSliderAdapter(this, data);
        viewPager.setAdapter(adapter);
        layoutIndicator = findViewById(R.id.layoutIndicators);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });
        setupIndicators(data.getarticles().size());


    }

    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.setMargins(16, 8, 16, 8);

        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(this);
            indicators[i].setImageDrawable(ContextCompat.getDrawable(this,
                    R.drawable.bg_indicator_inactive));
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
        setCurrentIndicator(0);
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        this,
                        R.drawable.bg_indicator_inactive
                ));
            }
        }
    }

    @Override
    public void DetailErrorCode(int statusCode, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

