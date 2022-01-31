package com.example.edusera.ViewModel;

import android.app.Application;

import com.example.edusera.Model.CourseFeedModel;
import com.example.edusera.Database.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CourseFeedViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<List<CourseFeedModel>> getAllCourseFeeds;

    public CourseFeedViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        getAllCourseFeeds = repository.getAllCourseFeed;
    }

    public void insert(List<CourseFeedModel> courseFeeds) {
        repository.insertCourseFeed(courseFeeds);
    }

    public LiveData<List<CourseFeedModel>> getGetAllCourseFeeds() {
        return getAllCourseFeeds;
    }
}
