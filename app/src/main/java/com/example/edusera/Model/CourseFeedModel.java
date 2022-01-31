package com.example.edusera.Model;


import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_feed_table")
public class CourseFeedModel {

    /*@PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Integer id;*/

    @SerializedName("courseID")
    @ColumnInfo(name = "courseID")
    private String courseID;

    @PrimaryKey
    @NonNull
    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("instructorID")
    @ColumnInfo(name = "instructorID")
    private String instructorID;

    @SerializedName("instructorName")
    @ColumnInfo(name = "instructorName")
    private String instructorName;

    @SerializedName("instructorImage")
    @ColumnInfo(name = "instructorImage")
    private String instructorImage;

    @SerializedName("courseThumbnail")
    @ColumnInfo(name = "courseThumbnail")
    private String courseThumbnail;

    @SerializedName("startDate")
    @ColumnInfo(name = "startDate")
    private String startDate;

    @SerializedName("duration")
    @ColumnInfo(name = "duration")
    private String duration;

    @SerializedName("isLive")
    @ColumnInfo(name = "isLive")
    private Boolean isLive;

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(String instructorID) {
        this.instructorID = instructorID;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getInstructorImage() {
        return instructorImage;
    }

    public void setInstructorImage(String instructorImage) {
        this.instructorImage = instructorImage;
    }

    public String getCourseThumbnail() {
        return courseThumbnail;
    }

    public void setCourseThumbnail(String courseThumbnail) {
        this.courseThumbnail = courseThumbnail;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Boolean getLive() {
        return isLive;
    }

    public void setLive(Boolean live) {
        isLive = live;
    }

/*    @NonNull
    public Integer getId() {
        return id;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }*/
}
