package com.example.mymedcine.login.view;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mymedcine.MainActivity;
import com.example.mymedcine.R;

import com.example.mymedcine.homescreen.HomeActivity;
import com.example.mymedcine.login.presenter.LoginPresenter;
import com.example.mymedcine.login.presenter.LoginPresenterInterface;
import com.example.mymedcine.signup.view.SignupActivity;

public class LoginActivity extends AppCompatActivity implements LoginViewInterface{

    LoginPresenterInterface loginPresenterInterface;
    String email, password;
    TextView txtRegister;
    EditText edtxtEmail, edtxtPassword;
    ProgressBar loginPB;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        loginPresenterInterface = new LoginPresenter(this);

        edtxtEmail = findViewById(R.id.edtxtEmail);
        edtxtPassword = findViewById(R.id.edtxtPassword);
        loginPB = findViewById(R.id.loginPB);

        txtRegister = findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
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
    public void login() {
        if (checkUserData()){
            loginPB.setVisibility(VISIBLE);
            loginPresenterInterface.login(email, password);
        }
    }

    @Override
    public void showSuccessfulLogin() {
        loginPB.setVisibility(View.GONE);
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    @Override
    public void showFailedLogin() {
        loginPB.setVisibility(View.GONE);
        Toast.makeText(this, "filed to login \nplease check your credentials", Toast.LENGTH_SHORT).show();
    }

    private boolean checkUserData() {
        password = edtxtPassword.getText().toString().trim();
        email = edtxtEmail.getText().toString().trim();
        boolean result = true;

        if(email.isEmpty()){
            edtxtEmail.setError(getString(R.string.emptyEmailError) );
            edtxtEmail.requestFocus();
            result = false;
        }

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
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edtxtEmail.setError(getString(R.string.invalidEmailError));
            edtxtPassword.requestFocus();
            result = false;
        }
        return result;
    }
}