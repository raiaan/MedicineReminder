package com.example.mymedcine.login.view;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.ActionMode;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymedcine.MainActivity;
import com.example.mymedcine.R;

import com.example.mymedcine.database.ConcreteLocalSource;
import com.example.mymedcine.homescreen.HomeActivity;
import com.example.mymedcine.login.presenter.LoginPresenter;
import com.example.mymedcine.login.presenter.LoginPresenterInterface;
import com.example.mymedcine.model.Repository;
import com.example.mymedcine.model.RepositoryInterface;
import com.example.mymedcine.network.FireBaseConnection;
import com.example.mymedcine.network.FirebaseConnectionDelegated;
import com.example.mymedcine.signup.view.SignupActivity;
import com.example.mymedcine.splashScreen.SplashScreen;
import com.example.mymedcine.utils.SharedPreferencesUtils;
import com.google.android.gms.auth.TokenData;
import com.google.firebase.auth.FirebaseUser;

import java.util.zip.Inflater;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface, FirebaseConnectionDelegated {

    LoginPresenterInterface loginPresenterInterface;
    RepositoryInterface repository;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String email, password;
    TextView txtRegister, txtRestPassword;
    EditText edtxtEmail, edtxtPassword;
    ProgressBar loginPB;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        repository = Repository.getInstance(FireBaseConnection.getInstance(), ConcreteLocalSource.getInstance(this),this);
        loginPresenterInterface = new LoginPresenter(this, this, repository);

        sharedPreferences = getSharedPreferences(SharedPreferencesUtils.FILE_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        edtxtEmail = findViewById(R.id.edtxtEmail);
        edtxtPassword = findViewById(R.id.edtxtPassword);
        loginPB = findViewById(R.id.loginPB);

        txtRestPassword = findViewById(R.id.txtResetPassword);
        txtRestPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

        txtRegister = findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                finish();
            }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

   @Override
    protected void onStart() {
        super.onStart();
        if (sharedPreferences.getBoolean(SharedPreferencesUtils.LOGIN_KEY,false)){
            Intent intent=new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void login() {
        if (checkPassword() && checkEmail(edtxtEmail)){
            loginPB.setVisibility(VISIBLE);
            loginPresenterInterface.login(email, password, this);
        }
    }

    @Override
    public void onCompleteResultSuccess(FirebaseUser user) {
        loginPB.setVisibility(View.GONE);
        if (user != null){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            editor.putString(SharedPreferencesUtils.USERNAME_KEY, user.getDisplayName());
            editor.putString(SharedPreferencesUtils.EMAIL_KEY, user.getEmail());
            editor.putBoolean(SharedPreferencesUtils.LOGIN_KEY, true);
            editor.commit();
            Log.i("TAG", "onCompleteResultSuccess: " + sharedPreferences.getBoolean(SharedPreferencesUtils.LOGIN_KEY, false));
            finish();
        } else {
            Toast.makeText(this, "Email sent successfully, please check your email", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailureResult(String errorMessage) {
        loginPB.setVisibility(View.GONE);
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void showDialog(){
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.reset_password_layout, null);
            dialogBuilder.setView(dialogView);

            final EditText edt = (EditText) dialogView.findViewById(R.id.edtxtResetPassword);

            dialogBuilder.setIcon(R.drawable.email_icon);
            dialogBuilder.setTitle("Enter your email");

            dialogBuilder.setPositiveButton("Send",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if(checkEmail(edt)){
                                        loginPB.setVisibility(VISIBLE);
                                        loginPresenterInterface.resetPassword(email, LoginActivity.this);
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Please, Enter a valid email", Toast.LENGTH_SHORT).show();
                                        showDialog();
                                    }
                                }
                            });
            dialogBuilder.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
            AlertDialog b = dialogBuilder.create();
            b.show();
        }

    public boolean checkEmail(EditText editText){
        boolean result = true;
        email = editText.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText.setError(getString(R.string.invalidEmailError));
            editText.requestFocus();
            result = false;
        }
        if(email.isEmpty()) {
            editText.setError(getString(R.string.emptyEmailError));
            editText.requestFocus();
            result = false;
        }
            return result;
    }

    private boolean checkPassword() {
        password = edtxtPassword.getText().toString().trim();
        boolean result = true;

        if (password.isEmpty()) {
            edtxtEmail.setError(getString(R.string.emptyPasswordError));
            edtxtEmail.requestFocus();
            result = false;
        }
        if (password.length() < 6){
            edtxtPassword.setError(getString(R.string.shortPasswordError));
            edtxtPassword.requestFocus();
            result = false;
        }
        return result;
    }
}