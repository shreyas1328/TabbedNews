package com.shreyas.tabbednews.roomDb.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.shreyas.tabbednews.model.SourceModel;

@Dao
public interface NewsSourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SourceModel sourceModel);

    @Query("SELECT * FROM newsSource")
    LiveData<SourceModel> getAllNewSource();

    @Query("DELETE FROM newsSource")
    void deleteNewSource();
}
