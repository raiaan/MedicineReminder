package com.example.mymedcine.database;

import androidx.room.TypeConverter;

import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Prescription;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converter {

    @TypeConverter
    public String fromDrugsToGson(ArrayList<Drug> drugs){
        return new Gson().toJson(drugs);
    }

    @TypeConverter
    public static ArrayList<Drug> fromGsonToDrugs(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public String fromMailsToGson(ArrayList<String> takerMail){
        return new Gson().toJson(takerMail);
    }

    @TypeConverter
    public static ArrayList<String> fromGsonToMails(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

}
