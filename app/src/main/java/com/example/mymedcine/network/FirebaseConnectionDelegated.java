package com.example.mymedcine.network;

import com.google.firebase.auth.FirebaseUser;

public interface FirebaseConnectionDelegated {
    void onCompleteResultSuccess(FirebaseUser user);
    void onFailureResult(String errorMessage);
}
