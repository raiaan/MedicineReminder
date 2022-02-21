package com.example.mymedcine.signup.view;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mymedcine.MainActivity;
import com.example.mymedcine.R;
import com.example.mymedcine.signup.Presenter.SignupPresenter;
import com.example.mymedcine.signup.Presenter.SignupPresenterInterface;

public class SignupActivity extends AppCompatActivity implements SignupViewInterface {

    String name, password, email;
    SignupPresenterInterface signupPresenter;

    Button btnRegister;
    ProgressBar singinPB;
    EditText edtxtUserName, edtxtEmail, edtxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_singup);

        signupPresenter = new SignupPresenter(this);
        singinPB = findViewById(R.id.singinPB);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

    }

    @Override
    public void signup() {
        if (checkUserData()){
            singinPB.setVisibility(VISIBLE);
            signupPresenter.signup(email, password, name);
        }
    }

    @Override
    public void showSuccessfulSignup() {
        singinPB.setVisibility(View.GONE);
        Toast.makeText(this, "You have signed up successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void ShowFiledSignup() {
        singinPB.setVisibility(View.GONE);
        Toast.makeText(this, "filed sign up ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTryAgain() {
        singinPB.setVisibility(View.GONE);
        Toast.makeText(this, "filed sign up, please try again ", Toast.LENGTH_SHORT).show();
    }

    private boolean checkUserData() {
        name = edtxtUserName.getText().toString().trim();
        password = edtxtPassword.getText().toString().trim();
        email = edtxtEmail.getText().toString().trim();
        boolean result = true;

        if(name.isEmpty()){
            edtxtUserName.setError(getString(R.string.emptyNameError));
            edtxtUserName.requestFocus();
            result = false;
        }

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