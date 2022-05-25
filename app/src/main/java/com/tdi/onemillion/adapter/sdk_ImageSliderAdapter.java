package com.tdi.onemillion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdi.onemillion.R;
import com.tdi.onemillion.rest.model.sdk_articlesmodel;
import com.tdi.onemillion.util.sdk_CustomExoPlayerView;
import com.tdi.onemillion.util.sdk_ImageVideoView;

public class sdk_ImageSliderAdapter extends RecyclerView.Adapter<sdk_ImageSliderAdapter.MyViewHolder> {
    private Context context;
    private sdk_articlesmodel data;

    private sdk_CustomExoPlayerView imageVideoView;

    public sdk_ImageSliderAdapter(Context context, sdk_articlesmodel data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sdk_item_image, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.mImagevideoViewSdk.setUrl(data.getarticles().get(position));

        imageVideoView = holder.mImagevideoViewSdk.getCustomExoPlayerView();
    }

    public void releasePlayer() {
        if (imageVideoView != null) {
            imageVideoView.releasePlayer();
        }
    }


    @Override
    public int getItemCount() {
        return data.getarticles().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private sdk_ImageVideoView mImagevideoViewSdk;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImagevideoViewSdk = itemView.findViewById(R.id.imageVideoView);
        }


    }
}