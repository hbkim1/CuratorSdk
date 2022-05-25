package com.tdi.onemillion.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.tdi.onemillion.R;
import com.tdi.onemillion.rest.model.sdk_articlesmodel;

import java.util.ArrayList;

import static com.tdi.onemillion.sdk_Constant.DEV_CDN_URL;
import static com.tdi.onemillion.sdk_Constant.LOG_TAG;
import static com.tdi.onemillion.sdk_Constant.PLATFORM_TYPE_1;
import static com.tdi.onemillion.sdk_Constant.PLATFORM_TYPE_2;
import static com.tdi.onemillion.sdk_Constant.PLATFORM_TYPE_3;
import static com.tdi.onemillion.sdk_Constant.PLATFORM_TYPE_4;
import static com.tdi.onemillion.sdk_Constant.PLATFORM_TYPE_5;


public class sdk_FeedAdapter extends RecyclerView.Adapter<sdk_FeedAdapter.ViewHolder> {
    private Context context;
    private ArrayList<sdk_articlesmodel> articleslist;

    public sdk_FeedAdapter(Context mContext, ArrayList<sdk_articlesmodel> articleslist) {
        this.context = mContext;
        this.articleslist = articleslist;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sdk_item_feed, parent, false);

        return new ViewHolder(view);
    }

    public ItemClickListener onClickListener = null;

    public interface ItemClickListener {
        void onClick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


//        if (holder instanceof ViewHolder) {

//        holder.binding.count.setText("" + position);


        Log.e(LOG_TAG, "list url : " + articleslist.get(position).getStorage_thumbnail_url());
        if (articleslist.get(position).getStorage_thumbnail_url() != null) {
//            holder.cardView.getLayoutParams().width = Math.round(convertDpToPixel(context, articleslist.get(position).getThumbnail_width()));
//            holder.cardView.getLayoutParams().height = Math.round(convertDpToPixel(context, articleslist.get(position).getThumbnail_height()));

            Glide.with(context).load(DEV_CDN_URL + articleslist.get(position).getStorage_thumbnail_url()).override(articleslist.get(position).getThumbnail_width(), articleslist.get(position).getThumbnail_height()).into(holder.feed_img);
            holder.itemView.setOnClickListener(view -> onClickListener.onClick(position));
        } else {

            holder.feed_img.getLayoutParams().height = Math.round(convertDpToPixel(context, 200));
            holder.feed_img.getLayoutParams().width = Math.round(convertDpToPixel(context, 480));

            holder.feed_img.setBackgroundColor(context.getResources().getColor(R.color.black));
        }


        if (articleslist.get(position).getPlatform() != null) {
            switch (articleslist.get(position).getPlatform()) {
                case PLATFORM_TYPE_1://인스타그램
                    holder.platform_img.setVisibility(View.VISIBLE);
                    holder.platform_img.setImageResource(R.drawable.sns_icon_instagram);
                    break;
                case PLATFORM_TYPE_2://페이스북
                    holder.platform_img.setVisibility(View.GONE);
                    break;
                case PLATFORM_TYPE_3://유튜브
                    holder.platform_img.setVisibility(View.VISIBLE);
                    holder.platform_img.setImageResource(R.drawable.sns_icon_youtube);
                    break;
                case PLATFORM_TYPE_4://네이버블로그
                    holder.platform_img.setVisibility(View.VISIBLE);
                    holder.platform_img.setImageResource(R.drawable.sns_icon_naver_blog);
                    break;
                case PLATFORM_TYPE_5://트위터
                    holder.platform_img.setVisibility(View.GONE);
                    break;
            }
        }


//        }
    }

    public void setarticle_mediaList(ArrayList<sdk_articlesmodel> articleslist) {
        this.articleslist = articleslist;

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return articleslist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView feed_img;
        ImageView platform_img;
        ImageView btn_nice;
        MaterialCardView cardView;


        public ViewHolder(View view) {
            super(view);
            feed_img = view.findViewById(R.id.feed_img);
            platform_img = view.findViewById(R.id.platform_img);
            btn_nice = view.findViewById(R.id.btn_nice);
            cardView = view.findViewById(R.id.cardview);

        }
    }

    public float convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
}