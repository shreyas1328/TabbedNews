package com.shreyas.tabbednews.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.shreyas.tabbednews.Repository.NewsRepo;
import com.shreyas.tabbednews.model.NewsList;
import com.shreyas.tabbednews.model.NewsModel;
import com.shreyas.tabbednews.model.SourceModel;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private NewsRepo newsRepo;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        newsRepo = new NewsRepo();
    }

    public MutableLiveData<NewsList> getNewsFromApi(String newsApi) {
        return newsRepo.makeApiCall(newsApi);
    }


}
