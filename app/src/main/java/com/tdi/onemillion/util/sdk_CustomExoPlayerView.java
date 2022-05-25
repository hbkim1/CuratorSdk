package com.tdi.onemillion.util;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static com.tdi.onemillion.sdk_Constant.LOG_TAG;

public class sdk_CustomExoPlayerView extends PlayerView {

    SimpleExoPlayer player;
    DataSource.Factory mediaDataSourceFactory;
    DefaultTrackSelector trackSelector;
    TrackGroupArray lastSeenTrackGroupArray;

    public sdk_CustomExoPlayerView(Context context) {
        super(context);
    }

    public sdk_CustomExoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public sdk_CustomExoPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void initializePlayer(String url) {
        if (trackSelector != null) {
            Log.i("DATA", "trankSelector : not null");
        } else {
            Log.i("DATA", "trankSelector : null");
        }

        final long defaultMaxInitialBitrate = Integer.MAX_VALUE;
        final DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter.Builder(getContext()).setInitialBitrateEstimate(defaultMaxInitialBitrate).build();
        AdaptiveTrackSelection.Factory videoTrackFactory = new AdaptiveTrackSelection.Factory(defaultBandwidthMeter);

        trackSelector = new DefaultTrackSelector(videoTrackFactory);
        mediaDataSourceFactory = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "mediaPlayerSample"));

        DefaultExtractorsFactory factory = new DefaultExtractorsFactory();
        DefaultDataSourceFactory dataSource = new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "DetailVideo"));

        MediaSource mediaSource;
        if (url.contains(".m3u8")) {
            mediaSource = buildMediaSourceHLS(Uri.parse(url));
        } else {
            mediaSource = new ExtractorMediaSource.Factory(dataSource).createMediaSource(Uri.parse(url));
        }
        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);


        player.prepare(mediaSource);
        player.setPlayWhenReady(true);

        setShutterBackgroundColor(Color.TRANSPARENT);
        setPlayer(player);
        requestFocus();

        lastSeenTrackGroupArray = null;
    }

    private MediaSource buildMediaSourceHLS(Uri uri) {

        HlsMediaSource source = null;
        try {
            String userAgent = System.getProperty("http.agent");
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory(userAgent);
            source = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        } catch (Exception e) {
            Log.e(LOG_TAG, "buildMediaSourceHLS error : " + e.toString());
        }

        return new ConcatenatingMediaSource(source);
    }

    //현재 동영상의 시간
    public int getCurrentPosition() {
        return (int) player.getCurrentPosition();
    }

    //동영상이 실행되고있는지 확인
    public boolean isPlaying() {
        return player.getPlayWhenReady();
    }

    //동영상 정지
    public void pause() {
        if (player!=null) {
            player.setPlayWhenReady(false);
        }
    }

    //동영상 재생
    public void start() {
        if (player!=null) {
            player.setPlayWhenReady(true);
        }
    }

    //동영상 시간 설정
    public void seekTo(int position) {
        if (player!=null) {
            player.seekTo(position);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i("DATADATA", "onResume");
        if (player!=null) {
            player.setPlayWhenReady(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("DATADATA", "onPause");
        if (player!=null) {
            player.setPlayWhenReady(false);
        }
    }

    //동영상 해제
    public void releasePlayer() {
        if (player!=null) {
            player.release();
        }
        trackSelector = null;
    }
}