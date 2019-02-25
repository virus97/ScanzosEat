package com.simonescanzani.scanzoseat.datamodels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class ProductConverter {

    @TypeConverter
    public static String fromList(List<Product> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static List<Product> fromString(String list) {
        Type listType = new TypeToken<List<Product>>(){}.getType();
        Gson gson = new Gson();
        List<Product> json = gson.fromJson(list,listType);
        return json;
    }
}
