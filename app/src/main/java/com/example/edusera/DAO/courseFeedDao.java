package com.example.edusera.DAO;

import com.example.edusera.Model.CourseFeedModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface courseFeedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<CourseFeedModel> courseFeeds);

    @Query("select * from course_feed_table")
    LiveData<List<CourseFeedModel>> getCourseFeed();

    @Query("DELETE FROM course_feed_table")
    void deleteAll();
}
