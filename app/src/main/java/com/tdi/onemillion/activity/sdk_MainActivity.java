package com.tdi.onemillion.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

//import com.simform.refresh.SSPullToRefreshLayout;
import com.tdi.onemillion.R;
import com.tdi.onemillion.adapter.sdk_FeedAdapter;
import com.tdi.onemillion.rest.SDKRestService;
import com.tdi.onemillion.rest.model.sdk_articlesmodel;
import com.tdi.onemillion.rest.model.sdk_listdatamodel;
import com.tdi.onemillion.util.sdk_CustomExoPlayerView;
//import com.tdi.everland.util.sdk_WaveAnimation;

import java.util.ArrayList;

import static com.tdi.onemillion.sdk_Constant.LOG_TAG;
import static com.tdi.onemillion.sdk_Constant.PLATFORM_TYPE_1;
import static com.tdi.onemillion.sdk_Constant.PLATFORM_TYPE_3;


public class sdk_MainActivity extends AppCompatActivity implements SDKRestService.ListListener, sdk_FeedAdapter.ItemClickListener {


    private SDKRestService SDKRestService;
    //    private SSPullToRefreshLayout ssPullRefresh;
    private SwipeRefreshLayout swiperefreshlayout;

    private sdk_FeedAdapter adapter;

    private StaggeredGridLayoutManager layoutManager;

    private int page = 1;
    private int limit = 20;

    private ImageView MImage;

    private sdk_CustomExoPlayerView exoPlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.sdk_activity_main);

        article_mediaslist = new ArrayList<>();
        adapter = new sdk_FeedAdapter(this, article_mediaslist);

        SDKRestService = new SDKRestService(this);

        SDKRestService.listListener = this;

//        ssPullRefresh = findViewById(R.id.refreshLayout);
        swiperefreshlayout = findViewById(R.id.refreshLayout);

        MImage = findViewById(R.id.titleImage);
        setRefresh();

        getList(this);

        setAdapter();

        /*  밀리언 이미지 클릭시 맨 상단으로 이동 */
        MImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerview.scrollToPosition(0);
            }
        });


    }

    private void getList(Context context) {


        article_mediaslist = new ArrayList<>();


        SDKRestService.getList(0, page, limit, null, null);//리스트가져오기


    }


    private void setRefresh() {  //당겨서 새로고침 레이아웃
//
//


        swiperefreshlayout.setOnRefreshListener(() -> {
            page = 1;
            getList(this);
        });
//
//        ssPullRefresh.setRefreshViewParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
////        ssPullRefresh.setPadding(0, 0, 0, 0);
////
//        ssPullRefresh.setRefreshView(new sdk_WaveAnimation(this));
//        ssPullRefresh.setGifAnimation(R.drawable.sdk_loading);
//        ssPullRefresh.setLottieAnimation("lottie_isometric-plane.json");
//        ssPullRefresh.setRepeatMode(SSPullToRefreshLayout.RepeatMode.REPEAT);
//        // set number of times the animation repeats
//        ssPullRefresh.setRepeatCount(SSPullToRefreshLayout.RepeatCount.INFINITE);
//        //set style of RefreshLayout : NORMAL, FLOAT, PINNED
//        ssPullRefresh.setRefreshStyle(SSPullToRefreshLayout.RefreshStyle.NORMAL);
//
//        ssPullRefresh.setRefreshInitialOffset(0f);
//
    }

    RecyclerView recyclerview;

    private void setAdapter() {

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerview = findViewById(R.id.recyclerview);

        recyclerview.addItemDecoration(new SpaceDecoration(
                Math.round(convertDpToPixel(this, 5f))
        ));
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);

        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

                int LlastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPositions(null)[0];
                int RlastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPositions(null)[1];
                int itemTotalCount = article_mediaslist.size() - 1;

                if (LlastVisibleItemPosition == itemTotalCount || RlastVisibleItemPosition == itemTotalCount) {
//    Toast.makeText(getContext(), "Last Position", Toast.LENGTH_SHORT).show();

                    if (page < (int) Math.ceil((float) sdk_listdatamodel.getTotalCount() / limit)) {
                        page++;   //페이지 번호 증가

                        Log.e(LOG_TAG, "page : " + page);
                        SDKRestService.getList(0, page, limit, null, null);//리스트가져오기

                    }
                }


                super.onScrolled(recyclerView, dx, dy);
            }
        });

        adapter.onClickListener = this;

    }

    ArrayList<sdk_articlesmodel> article_mediaslist;
    sdk_listdatamodel sdk_listdatamodel;

    @Override
    public void ListListener(sdk_listdatamodel data) {


        if (data != null) {
            sdk_listdatamodel = data;
            for (int i = 0; i < data.getArticles().size(); i++) {
                if (data.getArticles().get(i).getPlatform() != null) {
                    if (data.getArticles().get(i).getPlatform().equals(PLATFORM_TYPE_1) || data.getArticles().get(i).getPlatform().equals(PLATFORM_TYPE_3)) {  //TODO:현재 유튜브랑 인스타그램만 보이게
                        article_mediaslist.add(data.getArticles().get(i));
                    }
                }
            }

            Log.e(LOG_TAG, "data.getArticles()" + data.getArticles().size());
            Log.e(LOG_TAG, "data.article_mediaslist()" + article_mediaslist.size());

            adapter.setarticle_mediaList(article_mediaslist);
            adapter.notifyDataSetChanged();
        }
        Handler handler = new Handler();
        handler.postDelayed(runnable, 1500);


    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            swiperefreshlayout.setRefreshing(false);
        }
    };

    @Override
    public void ErrorCode(int statusCode, String name, String message, int page, String code) {
        this.page = page - 1;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(int position) {
        if (article_mediaslist.get(position).getPlatform().equals(PLATFORM_TYPE_3)) {
            Intent intent = new Intent(this, sdk_Detail_youtubeActivity.class);
            intent.putExtra("article_id", article_mediaslist.get(position).getId());
            startActivity(intent);
        } else {

            Intent intent = new Intent(this, sdk_Detail_imagevideoActivity.class);
            intent.putExtra("article_id", article_mediaslist.get(position).getId());
            startActivity(intent);

        }
        Log.e(LOG_TAG, "postion : " + position);
    }


    class SpaceDecoration extends RecyclerView.ItemDecoration {
        private int halfSpace = 0;

        public SpaceDecoration(int size) {

            halfSpace = size / 2;
        }


        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            if (parent.getPaddingLeft() != halfSpace) {
                parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace);
                parent.setClipToPadding(false);
            }
            outRect.top = halfSpace;
            outRect.right = halfSpace;
            outRect.left = halfSpace;
            outRect.bottom = halfSpace;
        }

    }

    public float convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }


}

