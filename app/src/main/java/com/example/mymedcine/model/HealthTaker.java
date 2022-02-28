package com.example.mymedcine.model;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.List;

public class HealthTaker implements Serializable {

    @Exclude
    private String key;
     String senderEmail;
     String recevierEmail;
     String senderName;
     private List<Drug> drugList;

    public HealthTaker(String senderEmail, String recevierEmail, String senderName, List<Drug> drugList) {
        this.senderEmail = senderEmail;
        this.recevierEmail = recevierEmail;
        this.senderName = senderName;
        this.drugList = drugList;
    }

    public HealthTaker(String senderEmail, String recevierEmail, String senderName) {
        this.senderEmail = senderEmail;
        this.recevierEmail = recevierEmail;
        this.senderName = senderName;

    }

    public HealthTaker() {
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getRecevierEmail() {
        return recevierEmail;
    }

    public void setRecevierEmail(String recevierEmail) {
        this.recevierEmail = recevierEmail;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
