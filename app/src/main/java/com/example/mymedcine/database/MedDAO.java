package com.example.mymedcine.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mymedcine.model.Drug;
import com.example.mymedcine.model.Prescription;
import com.example.mymedcine.model.User;

import java.util.List;

@Dao
public interface MedDAO {

    @Query("SELECT * FROM drugTable")
    LiveData<List<Drug>> getAllDrugs();

    @Query("SELECT * FROM PrescriptionTable")
    LiveData<List<Prescription>> getAllPrescription();

    @Query("SELECT * FROM drugTable where name = :name ")
    LiveData<Drug> getDrugData(String name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void add(Drug drug);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void add(Prescription prescription);

    @Delete
    void delete(Drug drug);

    @Delete
    void delete(Prescription prescription);

    @Update
    void update(Drug drug);

    @Update
    void update(Prescription prescription);

    @Query("SELECT * FROM drugTable WHERE state = 'active' AND ((weekDays  LIKE '%' + :day + '%') OR (is_chronic LIKE 1 ))")
    LiveData<List<Drug>> getAllDrugsOfTheDay(String day);

    @Query("SELECT * FROM drugTable WHERE instructions LIKE '%Before eating%'")
    LiveData<List<Drug>> getDummyData();

}

