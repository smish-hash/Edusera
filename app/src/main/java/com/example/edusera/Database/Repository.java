package com.example.edusera.Database;

import android.app.Application;
import android.os.AsyncTask;

import com.example.edusera.DAO.courseDataDao;
import com.example.edusera.DAO.courseFeedDao;
import com.example.edusera.Database.CourseDataDatabase;
import com.example.edusera.Database.CourseFeedDatabase;
import com.example.edusera.Model.CourseDataModel;
import com.example.edusera.Model.CourseFeedModel;

import java.util.List;

import androidx.lifecycle.LiveData;

public class Repository {
    public courseFeedDao courseFeedDao;
    public LiveData<List<CourseFeedModel>> getAllCourseFeed;
    private CourseFeedDatabase courseFeedDatabase;

    public courseDataDao courseDataDao;
    public LiveData<List<CourseDataModel>> getAllCourseData;
    private CourseDataDatabase courseDataDatabase;

    public Repository(Application application){
        courseFeedDatabase = CourseFeedDatabase.getInstance(application);
        courseFeedDao = courseFeedDatabase.courseFeedDao();
        getAllCourseFeed = courseFeedDao.getCourseFeed();

        courseDataDatabase = CourseDataDatabase.getINSTANCE(application);
        courseDataDao = courseDataDatabase.courseDataDao();
        getAllCourseData = courseDataDao.getCourseData();
    }

    public void insertCourseFeed(List<CourseFeedModel> feeds) {
        new InsertAsyncTask(courseFeedDao).execute(feeds);
    }

    public void insertCourseData(List<CourseDataModel> data) {
        new InsertDataAsyncTask(courseDataDao).execute(data);
    }

    private static class InsertAsyncTask extends AsyncTask<List<CourseFeedModel>, Void, Void> {

        private courseFeedDao courseFeedDao;

        public InsertAsyncTask(courseFeedDao courseFeedDao) {
            this.courseFeedDao = courseFeedDao;
        }

        @Override
        protected Void doInBackground(List<CourseFeedModel>... lists) {
            courseFeedDao.insert(lists[0]);
            return null;
        }
    }

    private static class InsertDataAsyncTask extends AsyncTask<List<CourseDataModel>, Void, Void>{
        private courseDataDao courseDataDao;

        public InsertDataAsyncTask(courseDataDao courseDataDao) {
            this.courseDataDao = courseDataDao;
        }

        @Override
        protected Void doInBackground(List<CourseDataModel>... lists) {
            courseDataDao.insert(lists[0]);
            return null;
        }
    }
}
