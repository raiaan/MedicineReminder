package com.example.mymedcine.login.presenter;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.mymedcine.R;
import com.example.mymedcine.login.view.LoginViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements  LoginPresenterInterface{


    LoginViewInterface loginViewInterface;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    FirebaseAuth mAuth;

    public LoginPresenter(LoginViewInterface loginViewInterface , SharedPreferences sharedPreferences){
        this.loginViewInterface = loginViewInterface;
        mAuth = FirebaseAuth.getInstance();
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
    }

    @Override
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    saveUserIntoSharedPreferences(email, password);
                    loginViewInterface.showSuccessfulLogin();
                }else{
                    loginViewInterface.showFailedLogin();
                }
            }
        });

    }

    @Override
    public void saveUserIntoSharedPreferences(String email, String password) {
        editor.putString("email",email );
        editor.putString("password", password);
        editor.putBoolean("login", true);
        editor.commit();
    }

}
