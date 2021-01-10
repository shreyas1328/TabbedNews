package com.shreyas.tabbednews.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shreyas.tabbednews.R;
import com.shreyas.tabbednews.model.NewsModel;
import com.shreyas.tabbednews.ui.MyWebView;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsHolder> {

    private Context mContext;
    private List<NewsModel> mNewsItem;

    public NewsRecyclerAdapter(Context mContext, List<NewsModel> mNewsItem) {
        this.mContext = mContext;
        this.mNewsItem = mNewsItem;
    }

    public void setNewsArray(List<NewsModel> mNewsItem) {
        Log.d("mNewsItem_io", "setNewsArray: "+mNewsItem);
        this.mNewsItem = mNewsItem;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        Log.d("my_custom_binder", "onBindViewHolder: "+mNewsItem.get(position));
//        Picasso.with(mContext).load(mNewsItem.get(position).getImageUrl()).into(holder.mIVBanner);
        Glide.with(mContext).load(mNewsItem.get(position).getImageUrl()).error(R.mipmap.ic_launcher_round).into(holder.mIVBanner);
        holder.mTvTitle.setText(mNewsItem.get(position).getTitle());
        holder.mTvDescription.setText(mNewsItem.get(position).getDescription());
        holder.mTvTimeStamp.setText(mNewsItem.get(position).getTimeStamp());

        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyWebView.class);
                intent.putExtra("myWebUrl", mNewsItem.get(position).getNewsUrl());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNewsItem.size();
    }

    public class NewsHolder extends RecyclerView.ViewHolder {

        private ImageView mIVBanner;
        private TextView mTvTitle;
        private TextView mTvDescription;
        private TextView mTvTimeStamp;
        private CardView mCardView;

        public NewsHolder(@NonNull View view) {
            super(view);
            mCardView = view.findViewById(R.id.cv_news);
            mIVBanner = view.findViewById(R.id.mIvBanner);
            mTvTitle = view.findViewById(R.id.mTvTitle);
            mTvDescription = view.findViewById(R.id.mTvDescription);
            mTvTimeStamp = view.findViewById(R.id.mTvTimeStamp);
        }
    }
}
