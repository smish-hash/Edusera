package com.example.edusera.DAO;

import com.example.edusera.Model.CourseDataModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface courseDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CourseDataModel> courseData);

    @Query("select * from course_data_table")
    LiveData<List<CourseDataModel>> getCourseData();

    @Query("DELETE FROM course_data_table")
    void deleteAll();
}
