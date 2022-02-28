package com.example.mymedcine.healthTaker.presenter;

import androidx.lifecycle.LifecycleOwner;

public interface HealthTakerInterface {
    void drugList(LifecycleOwner owner);
    void emailList(LifecycleOwner owner);
}
