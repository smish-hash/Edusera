package com.example.edusera.Model;

import com.google.gson.annotations.SerializedName;

public class TopicModel {

    @SerializedName("title")
    private String title;

    @SerializedName("duration")
    private String duration;

    @SerializedName("topicId")
    private Integer topicId;

    @SerializedName("topicType")
    private String topicType;

    @SerializedName("videoLink")
    private String videoLink = null;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public String getTopicType() {
        return topicType;
    }

    public void setTopicType(String topicType) {
        this.topicType = topicType;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}
