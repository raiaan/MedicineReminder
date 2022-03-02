package com.example.mymedcine.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Prescription;

@Database(entities = {Drug.class, Prescription.class},
        version = 10, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;
    public abstract MedDAO medDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "medicine")
                    .fallbackToDestructiveMigration().build();
        }

        return instance;
    }


}
