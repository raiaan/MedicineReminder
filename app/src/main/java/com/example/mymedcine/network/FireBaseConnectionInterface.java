package com.example.mymedcine.network;

import android.app.Activity;

import com.example.mymedcine.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;

public interface FireBaseConnectionInterface {
    void signup(User user, FirebaseConnectionDelegated delegated);
    void login(User user, FirebaseConnectionDelegated delegated);
   // void signWithGoogle(Activity activity);
   // void firebaseAuthWithGoogle(Activity activity, Task<GoogleSignInAccount> task, FirebaseConnectionDelegated firebaseConnectionDelegated) ;

    boolean isUserSignIn();
    void resetPassword(String email, FirebaseConnectionDelegated delegated) ;

    void logout();
}
