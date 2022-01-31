package com.example.edusera.Database;

import android.content.Context;
import android.os.AsyncTask;

import com.example.edusera.DAO.courseDataDao;
import com.example.edusera.Model.CourseDataModel;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CourseDataModel.class}, version = 1)
@TypeConverters({DataConverter.class})
public abstract class CourseDataDatabase extends RoomDatabase {

    public abstract courseDataDao courseDataDao();
    private static final String DATABASE_NAME = "courseDataDatabase";
    public static volatile CourseDataDatabase INSTANCE = null;

    public static CourseDataDatabase getINSTANCE(Context context) {
        if (INSTANCE == null) {
            synchronized (CourseDataDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, CourseDataDatabase.class, DATABASE_NAME)
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
            new PopulateDBAsyn(INSTANCE);
        }
    };

    static class PopulateDBAsyn extends AsyncTask<Void, Void, Void> {
        private courseDataDao courseDataDao;
        public PopulateDBAsyn(CourseDataDatabase courseDataDatabase) {
            courseDataDao = courseDataDatabase.courseDataDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            courseDataDao.deleteAll();
            return null;
        }
    }
}
