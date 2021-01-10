package com.shreyas.tabbednews.model;

import com.google.gson.annotations.SerializedName;

public class NewsModel {

    @SerializedName("urlToImage")
    private String imageUrl;

    private String title;
    private String description;

    @SerializedName("publishedAt")
    private String timeStamp;

    @SerializedName("url")
    private String newsUrl;

    public NewsModel(String imageUrl, String title, String description, String timeStamp, String newsUrl) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.timeStamp = timeStamp;
        this.newsUrl = newsUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
}
