package com.example.mymedcine.signup.Presenter;

import com.example.mymedcine.network.FirebaseConnectionDelegated;

public interface SignupPresenterInterface {
    void signup(String email, String password, String name, FirebaseConnectionDelegated delegated);
}
