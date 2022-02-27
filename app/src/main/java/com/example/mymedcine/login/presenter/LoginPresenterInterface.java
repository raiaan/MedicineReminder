package com.example.mymedcine.login.presenter;

import android.view.View;

import com.example.mymedcine.network.FirebaseConnectionDelegated;

public interface LoginPresenterInterface {

    void login(String emil, String Password, FirebaseConnectionDelegated delegated);


    void resetPassword(String email, FirebaseConnectionDelegated delegated);

    boolean isUserSignedUp();
}
