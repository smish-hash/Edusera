package com.example.edusera.Database;

import com.example.edusera.Model.TopicModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class DataConverter {
    @TypeConverter
    public static String fromTopicList(List<TopicModel> topics) {
        Gson gson = new Gson();
        String json = gson.toJson(topics);
        return json;
    }

    @TypeConverter
    public static List<TopicModel> toTopicList(String topic) {
        Type listType = new TypeToken<List<TopicModel>>() {}.getType();
        return new Gson().fromJson(topic, listType);
    }
}
