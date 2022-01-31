package com.example.edusera.ViewModel;

import android.app.Application;

import com.example.edusera.Model.CourseDataModel;
import com.example.edusera.Database.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CourseDataViewModel extends AndroidViewModel {
    private Repository repository;
    public LiveData<List<CourseDataModel>> getAllCourseData;

    public CourseDataViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        getAllCourseData = repository.getAllCourseData;
    }

    public void insert(List<CourseDataModel> courseData) {
        repository.insertCourseData(courseData);
    }

    public LiveData<List<CourseDataModel>> getGetAllCourseData() {
        return getAllCourseData;
    }
}
