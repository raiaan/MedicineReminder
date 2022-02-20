package com.example.mymedcine.signup.Presenter;

import androidx.annotation.NonNull;

import com.example.mymedcine.model.User;
import com.example.mymedcine.signup.view.SignupViewInterface;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupPresenter implements SignupPresenterInterface{

    User user;
    SignupViewInterface signupViewInterface;
    private FirebaseAuth mAuth;

    public SignupPresenter(SignupViewInterface signupViewInterface){
        mAuth = FirebaseAuth.getInstance();
        this.signupViewInterface = signupViewInterface;
    }

    @Override
    public void signup(String email, String password, String name) {
        user = new User(name, email, password);

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        signupViewInterface.showSuccessfulSignup();
                                    } else  {
                                        signupViewInterface.showTryAgain();
                                    }
                                }
                            });
                        } else{
                            signupViewInterface.ShowFiledSignup();
                        }
                    }
                });
    }
}
