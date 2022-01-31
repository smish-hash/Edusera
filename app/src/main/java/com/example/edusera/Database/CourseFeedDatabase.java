package com.example.edusera.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.edusera.DAO.courseFeedDao;
import com.example.edusera.Model.CourseFeedModel;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CourseFeedModel.class}, version = 1)
public abstract class CourseFeedDatabase extends RoomDatabase {

    public abstract courseFeedDao courseFeedDao();
    private static final String DATABASE_NAME = "courseFeedDatabase";
    public static volatile CourseFeedDatabase INSTANCE = null;

    public static CourseFeedDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (CourseFeedDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, CourseFeedDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyn(INSTANCE);
        }
    };

    static  class  PopulateDbAsyn extends AsyncTask<Void,Void,Void> {
        private courseFeedDao courseFeedDao;
        public PopulateDbAsyn(CourseFeedDatabase courseFeedDatabase)
        {
            courseFeedDao=courseFeedDatabase.courseFeedDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            courseFeedDao.deleteAll();
            return null;
        }
    }
}
