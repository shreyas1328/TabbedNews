package com.shreyas.tabbednews.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.shreyas.tabbednews.Repository.NewsSourceRepo;
import com.shreyas.tabbednews.model.SourceModel;

public class NewsSourceViewModel extends AndroidViewModel {

    private NewsSourceRepo newsSourceRepo;
    private LiveData<SourceModel> sourceModelLiveData;

    public NewsSourceViewModel(@NonNull Application application) {
        super(application);
        newsSourceRepo = new NewsSourceRepo(application);
        sourceModelLiveData = newsSourceRepo.getAllNewsSource();
    }

//    public void insert(SourceModel sourceModel) {
//        newsSourceRepo.insert(sourceModel);
//    }

    public LiveData<SourceModel> getAllNewsSource(){
        return sourceModelLiveData;
    }

    public MutableLiveData<SourceModel> getNewsSourceFromApi(){
       return newsSourceRepo.makeApiCall();
    }

    public void deleteDb() {
        newsSourceRepo.delete();
    }
}
