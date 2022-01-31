package com.example.edusera.Model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "course_data_table")
public class CourseDataModel {

    @PrimaryKey
    @NonNull
    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("topics")
    @ColumnInfo(name = "topics")
    private List<TopicModel> topics = null;

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public List<TopicModel> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicModel> topics) {
        this.topics = topics;
    }
}
