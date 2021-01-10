package com.shreyas.tabbednews.model;

import java.util.List;

public class NewsList {

    private String status;
    private List<NewsModel> articles;

    public NewsList(String status, List<NewsModel> articles) {
        this.status = status;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NewsModel> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsModel> articles) {
        this.articles = articles;
    }
}
