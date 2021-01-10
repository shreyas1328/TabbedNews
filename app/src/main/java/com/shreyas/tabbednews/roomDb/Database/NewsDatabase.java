package com.shreyas.tabbednews.roomDb.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.shreyas.tabbednews.roomDb.Convertor;
import com.shreyas.tabbednews.roomDb.dao.NewsSourceDao;
import com.shreyas.tabbednews.model.SourceModel;

@Database(entities = {SourceModel.class}, version = 1)
@TypeConverters(Convertor.class)
public abstract class NewsDatabase extends RoomDatabase {
    private static String DATABASE = "NEWS_SOURCE";

    public abstract NewsSourceDao newsSourceDao();

    private static volatile NewsDatabase INSTANCE;

    public static NewsDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (NewsSourceDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, NewsDatabase.class, DATABASE).addCallback(callback).build();
                }
            }

        }
        return INSTANCE;
    }

    static Callback callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAyncTask(INSTANCE);
        }
    };

    static class PopulateAyncTask extends AsyncTask<Void, Void, Void> {

        private NewsSourceDao newsSourceDao;

        public PopulateAyncTask(NewsDatabase newsDatabase) {
            newsSourceDao = newsDatabase.newsSourceDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            newsSourceDao.deleteNewSource();
            return null;
        }
    }
}
