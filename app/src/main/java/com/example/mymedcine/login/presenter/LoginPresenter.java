package com.example.mymedcine.login.presenter;

import androidx.annotation.NonNull;

import com.example.mymedcine.login.view.LoginViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements  LoginPresenterInterface{


    LoginViewInterface loginViewInterface;
    FirebaseAuth mAuth;

    public LoginPresenter(LoginViewInterface loginViewInterface){
        this.loginViewInterface = loginViewInterface;
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    loginViewInterface.showSuccessfulLogin();
                }else{
                    loginViewInterface.showFailedLogin();
                }
            }
        });

    }
}
