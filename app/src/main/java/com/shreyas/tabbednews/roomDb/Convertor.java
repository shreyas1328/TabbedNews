package com.shreyas.tabbednews.roomDb;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shreyas.tabbednews.model.SourceItemModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Convertor {
    @TypeConverter
    public static List<SourceItemModel> fromString(String value) {
        Type listType = new TypeToken<List<SourceItemModel>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<SourceItemModel> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
