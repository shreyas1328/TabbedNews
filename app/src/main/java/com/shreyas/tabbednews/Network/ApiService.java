package com.shreyas.tabbednews.Network;

import com.shreyas.tabbednews.model.NewsList;
import com.shreyas.tabbednews.model.NewsModel;
import com.shreyas.tabbednews.model.SourceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String NEWS_API_TOKEN = "apiKey=e902a24632c644c79887ea980ea3c38c";

    @GET("sources")
    Call<SourceModel> getNewsSource(@Query("apiKey") String key);

    @GET("everything")
    Call<NewsList> getAllNews(@Query("domains") String newsApi, @Query("apiKey") String key);
}
