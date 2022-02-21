package com.example.mymedcine.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.mymedcine.database.Converter;

import java.util.ArrayList;

@Entity(tableName = "PrescriptionTable")
@TypeConverters(Converter.class)
public class Prescription {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "patient")
    public String patient;
    @ColumnInfo(name = "admin_mail")
    public String admin_mail;
    @ColumnInfo(name = "drugs")
    public ArrayList<Drug> drugs;
    @ColumnInfo(name = "healthTaker_mail")
    public ArrayList<String> healthTaker_mail;


    public Prescription(String admin_mail, String patient, ArrayList<String> healthTaker_mail, ArrayList<Drug> drugs) {
        this.admin_mail = admin_mail;
        this.patient = patient;
        this.healthTaker_mail = healthTaker_mail;
        this.drugs = drugs;
    }

    public Prescription() {
    }

    public String getAdmin_mail() {
        return admin_mail;
    }

    public void setAdmin_mail(String admin_mail) {
        this.admin_mail = admin_mail;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public ArrayList<String> getHealthTaker_mail() {
        return healthTaker_mail;
    }

    public void setHealthTaker_mail(ArrayList<String> healthTaker_mail) {
        this.healthTaker_mail = healthTaker_mail;
    }

    public ArrayList<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<Drug> drugs) {
        this.drugs = drugs;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "patient='" + patient + '\'' +
                ", admin_mail='" + admin_mail + '\'' +
                ", drugs=" + drugs +
                ", healthTaker_mail=" + healthTaker_mail +
                '}';
    }


}
