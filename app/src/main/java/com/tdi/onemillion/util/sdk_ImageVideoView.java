package com.tdi.onemillion.util;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tdi.onemillion.rest.model.sdk_article_mediasmodel;

import static com.tdi.onemillion.sdk_Constant.DEV_CDN_URL;

public class sdk_ImageVideoView extends LinearLayout {

    Context mContext;
    sdk_CustomExoPlayerView customExoPlayerView;
    ImageView imageView;

    public sdk_ImageVideoView(Context context) {
        super(context);
        initView(context);
    }

    public sdk_ImageVideoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public sdk_ImageVideoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        setBackgroundColor(Color.BLACK);
    }

    //url이 비디오 형식일 경우 exoplayerview를 실행
    //url이 이미지 형식일 경우 imageview load
    public void setUrl(sdk_article_mediasmodel data) {
        if (isVideo(data.getType())) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            customExoPlayerView = new sdk_CustomExoPlayerView(mContext);
            customExoPlayerView.setLayoutParams(params);
            addView(customExoPlayerView);
//            customExoPlayerView.initializePlayer("https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4");
            customExoPlayerView.initializePlayer(data.getUrl());
        } else {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(params);
            addView(imageView);
            if (data.getStorage_url() != null) {
                Glide.with(this).load(DEV_CDN_URL + data.getStorage_url()).override(data.getWidth(), data.getHeight()).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).into(imageView);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }

    }

    //확장자가 비디오인지 이미지인지 확인
    private Boolean isVideo(String type) {

        if (type.equals("video")) {
            return true;
        } else {
            return false;
        }
    }

    public sdk_CustomExoPlayerView getCustomExoPlayerView() {
        return customExoPlayerView;
    }


    //동영상 해제
    public void releasePlayer() {
        if (customExoPlayerView != null) {
            customExoPlayerView.releasePlayer();
        }
    }
}