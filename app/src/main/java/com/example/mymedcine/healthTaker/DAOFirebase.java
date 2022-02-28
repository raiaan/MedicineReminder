package com.example.mymedcine.healthTaker;

import com.example.mymedcine.healthTaker.view.AddHealthTakerFragment;
import com.example.mymedcine.model.Prescription;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class DAOFirebase {

    DatabaseReference databaseReference;

    public DAOFirebase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(AddHealthTakerFragment.class.getSimpleName());
    }

    public Task<Void> addHealthTaker(Prescription healthTaker) {
        return databaseReference.push().setValue(healthTaker);
    }

    public Task<Void> updateHealthTaker(String key, HashMap<String,Object> hashMap){

        return databaseReference.child(key).updateChildren(hashMap);
    }
    public Task<Void> remove(String key)
    {
        return databaseReference.child(key).removeValue();
    }

    public Query getAllDrugs(){
        return databaseReference.orderByKey();
    }

    public Query getPatientDrugs(String key){
        return databaseReference.orderByChild("senderName").equalTo(key);
    }

    public Query get()
    {
        return databaseReference;
    }
}
