package com.example.mymedcine.network;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.mymedcine.R;
import com.example.mymedcine.model.User;
import com.example.mymedcine.signup.view.SignupActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FireBaseConnection implements FireBaseConnectionInterface{

    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth mAuth;

    private static FireBaseConnection fireBaseConnection = null;
    static final String  USER_REF = "users";
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseUser currentUser;
    private GoogleSignInClient googleSingInClient;

    private FireBaseConnection(){
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(USER_REF);
    }

    public static FireBaseConnection getInstance(){
        if(fireBaseConnection == null){
            fireBaseConnection = new FireBaseConnection();
        }
        return fireBaseConnection;
    }


    @Override
    public void signup(User user, FirebaseConnectionDelegated delegated) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
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
                             //   addUser(user , delegated);
                                currentUser = mAuth.getCurrentUser();
                                delegated.onCompleteResultSuccess(currentUser);
                            } else  {
                                delegated.onFailureResult(task.getException().getLocalizedMessage());
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean isUserSignIn() {
        if (currentUser != null) {
            return true;
        }
        return false;
    }

    @Override
    public void login(User user, FirebaseConnectionDelegated delegated) {
        mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        currentUser = authResult.getUser();
                        delegated.onCompleteResultSuccess(currentUser);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                delegated.onFailureResult("filed to login \nplease check your credentials");
            }
        });
    }

//    @Override
//    public void signWithGoogle(Activity activity) {
//        GoogleSignInOptions gso = new GoogleSignInOptions
//                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string)
//                .requestEmail()
//                .build();
//        googleSingInClient = GoogleSignIn.getClient(activity, gso);
//        Intent intent = googleSingInClient.getSignInIntent();
//        activity.startActivityForResult(intent, RC_SIGN_IN);
//    }
//
//    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build();
//
//    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//    @Override
//    public void firebaseAuthWithGoogle(Activity activity, Task<GoogleSignInAccount> task, FirebaseConnectionDelegated firebaseConnectionDelegated) {
//
//    }

    @Override
    public void resetPassword(String email, FirebaseConnectionDelegated delegated) {
            mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    delegated.onCompleteResultSuccess(null);
                } else {
                    delegated.onFailureResult("An error accrued while sending the email, please try once more");
                }
            }
        });
    }

    @Override
    public void logout() {
        mAuth.signOut();
    }

    public void addUser(User user, FirebaseConnectionDelegated delegated){
        myRef.child(mAuth.getCurrentUser().getUid()).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                currentUser.sendEmailVerification();
            }})
                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                delegated.onFailureResult(e.getLocalizedMessage());
            }
        });
    }
}
