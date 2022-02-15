package com.example.mymedcine.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.User;

import java.util.List;

@Dao
public interface MedDAO {

    @Query("SELECT * FROM userTable")
    LiveData<List<User>> getAllDrugs();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void add(Drug drug);

    @Delete
    void delete(Drug drug);


}

