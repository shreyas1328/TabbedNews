package com.shreyas.tabbednews.Repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.shreyas.tabbednews.Network.ApiService;
import com.shreyas.tabbednews.Network.RetrofitInstance;
import com.shreyas.tabbednews.model.NewsList;
import com.shreyas.tabbednews.model.NewsModel;
import com.shreyas.tabbednews.model.SourceItemModel;
import com.shreyas.tabbednews.model.SourceModel;
import com.shreyas.tabbednews.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepo {


    public MutableLiveData<NewsList> makeApiCall(String newsApi) {
        ApiService apiService = RetrofitInstance.getRetrofitClient().create(ApiService.class);
        Call<NewsList> call = apiService.getAllNews(newsApi, Utils.API_KEY);
        List<NewsModel> list = new ArrayList<>();
        MutableLiveData<NewsList> model = new MutableLiveData<>();
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                Log.d("asd_makeApiCall", ""+response);
                if(response.body() != null) {
                    model.postValue(response.body());
                }else {
                    model.postValue(new NewsList("fail", list));
                }
            }

            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Log.d("asd_makeApiCall", "onFailure: "+t);
                model.postValue(new NewsList("fail", list));
            }
        });
        return model;
    }

}
