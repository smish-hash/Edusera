package com.example.edusera.Interface;

import com.example.edusera.Model.CourseDataModel;
import com.example.edusera.Model.CourseFeedModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("GetCourseFeed")
    Call<List<CourseFeedModel>> getFeeds(@Query("code") String code);

    @GET("GetCourseData")
    Call<List<CourseDataModel>> getCourseData(@Query("code") String code);
}
