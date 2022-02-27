package com.example.mymedcine.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.mymedcine.R;
import com.example.mymedcine.login.view.LoginActivity;
import com.example.mymedcine.login.view.LoginViewInterface;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.model.RepositoryInterface;
import com.example.mymedcine.model.User;
import com.example.mymedcine.network.FirebaseConnectionDelegated;
import com.example.mymedcine.utils.SharedPreferencesUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPresenter implements  LoginPresenterInterface{

    Context context;
    LoginViewInterface loginViewInterface;
    RepositoryInterface repository;

    public LoginPresenter(Context context, LoginViewInterface loginViewInterface, RepositoryInterface repository) {
        this.context = context;
        this.loginViewInterface = loginViewInterface;
        this.repository = repository;
    }

    @Override
    public void login(String email, String password, FirebaseConnectionDelegated delegated) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        repository.login(user,delegated);
    }

    @Override
    public void resetPassword(String email, FirebaseConnectionDelegated delegated) {
        repository.resetPassword(email, delegated);
    }

    @Override
    public boolean isUserSignedUp() {
        return repository.isUserSignedUp();
    }
}
