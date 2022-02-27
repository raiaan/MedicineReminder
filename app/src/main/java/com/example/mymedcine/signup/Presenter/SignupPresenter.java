package com.example.mymedcine.signup.Presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.mymedcine.model.Repository;
import com.example.mymedcine.model.RepositoryInterface;
import com.example.mymedcine.model.User;
import com.example.mymedcine.network.FireBaseConnectionInterface;
import com.example.mymedcine.network.FirebaseConnectionDelegated;
import com.example.mymedcine.signup.view.SignupActivity;
import com.example.mymedcine.signup.view.SignupViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPresenter implements SignupPresenterInterface{

    Context context;
    SignupViewInterface signupViewInterface;
    RepositoryInterface repository;

    public SignupPresenter(Context context, SignupViewInterface signupViewInterface, RepositoryInterface repository) {
        this.context = context;
        this.signupViewInterface = signupViewInterface;
        this.repository = repository;
    }

    @Override
    public void signup(String email, String password, String name, FirebaseConnectionDelegated delegated) {
        User user = new User(name, email, password);
        repository.signup(user , delegated);
    }
}
