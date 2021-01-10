package com.shreyas.tabbednews.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.shreyas.tabbednews.Network.ApiService;
import com.shreyas.tabbednews.Network.RetrofitInstance;
import com.shreyas.tabbednews.model.SourceItemModel;
import com.shreyas.tabbednews.roomDb.Database.NewsDatabase;
import com.shreyas.tabbednews.roomDb.MyCallBack;
import com.shreyas.tabbednews.roomDb.dao.NewsSourceDao;
import com.shreyas.tabbednews.model.SourceModel;
import com.shreyas.tabbednews.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsSourceRepo {

    private NewsDatabase newsDatabase;
    private LiveData<SourceModel> sourceModel;

    public NewsSourceRepo(Application application) {
        newsDatabase = NewsDatabase.getInstance(application);
        sourceModel =  newsDatabase.newsSourceDao().getAllNewSource();
    }

    public void insert(SourceModel sourceModel) {
        Log.d("my_doInBackground", "doInBackground: "+sourceModel);
        new InsertAsyncTask(newsDatabase).execute(sourceModel);
    }

    public LiveData<SourceModel> getAllNewsSource() {
        return sourceModel;
    }

    static class InsertAsyncTask extends AsyncTask<SourceModel, Void, Void> {

        private NewsSourceDao newsSourceDao;

        InsertAsyncTask(NewsDatabase newsDatabase) {
            newsSourceDao = newsDatabase.newsSourceDao();
        }

        @Override
        protected Void doInBackground(SourceModel... sourceModels) {
//            Log.d("my_doInBackground", "doInBackground: "+sourceModels[0]);
            newsSourceDao.insert(sourceModels[0]);
            return null;
        }
    }


    public void delete() {
        new DeleteAsyncTask(newsDatabase).execute();
    }

    static class DeleteAsyncTask extends AsyncTask<Void, Void, Void> {

        private NewsSourceDao newsSourceDao;

        DeleteAsyncTask(NewsDatabase newsDatabase) {
            newsSourceDao = newsDatabase.newsSourceDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            newsSourceDao.deleteNewSource();
            return null;
        }
    }

    public MutableLiveData<SourceModel> makeApiCall() {
        ApiService apiService = RetrofitInstance.getRetrofitClient().create(ApiService.class);
        Call<SourceModel> call = apiService.getNewsSource(Utils.API_KEY);
        List<SourceItemModel> list = new ArrayList<>();
        MutableLiveData<SourceModel> model = new MutableLiveData<>();
        call.enqueue(new Callback<SourceModel>() {
            @Override
            public void onResponse(Call<SourceModel> call, Response<SourceModel> response) {
                Log.d("my_respponse_body", "onResponse: " + response.body());
//                myCallBack.onComplete(response.body());
//                if(response.body() != null) {
//                    insert(response.body());
//                }else {
//                    insert(new SourceModel("fail", list));
//                }

                if (response.body() != null) {
                    model.setValue(response.body());
                } else {
                    model.setValue(new SourceModel("fail", list));
                }

                Log.d("my_doInBackground", "doInBackground: "+model.getValue());

                insert(model.getValue());
            }

            @Override
            public void onFailure(Call<SourceModel> call, Throwable t) {
//                myCallBack.onComplete(new SourceModel("fail", list));
                model.setValue(new SourceModel("fail", list));
                insert(model.getValue());
            }
        });
        return model;
    }
}
