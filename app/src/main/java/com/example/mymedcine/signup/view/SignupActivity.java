package com.example.mymedcine.signup.view;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.mymedcine.MainActivity;
import com.example.mymedcine.R;
import com.example.mymedcine.databinding.ActivitySingupBinding;
import com.example.mymedcine.signup.Presenter.SignupPresenter;
import com.example.mymedcine.signup.Presenter.SignupPresenterInterface;

public class SignupActivity extends AppCompatActivity implements SignupViewInterface {

    ActivitySingupBinding binding;
    String name, password, email;
    SignupPresenterInterface signupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySingupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signupPresenter = new SignupPresenter(this);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

    }

    @Override
    public void signup() {
        if (checkUserData()){
            binding.singinPB.setVisibility(VISIBLE);
            signupPresenter.signup(email, password, name);
        }
    }

    @Override
    public void showSuccessfulSignup() {
        binding.singinPB.setVisibility(View.GONE);
        Toast.makeText(this, "You have signed up successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void ShowFiledSignup() {
        binding.singinPB.setVisibility(View.GONE);
        Toast.makeText(this, "filed sign up ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTryAgain() {
        binding.singinPB.setVisibility(View.GONE);
        Toast.makeText(this, "filed sign up, please try again ", Toast.LENGTH_SHORT).show();
    }

    private boolean checkUserData() {
        name = binding.edtxtuserName.getText().toString().trim();
        password = binding.edtxtPassword.getText().toString().trim();
        email = binding.edtxtEmail.getText().toString().trim();
        boolean result = true;

        if(name.isEmpty()){
            binding.edtxtuserName.setError(getString(R.string.emptyNameError));
            binding.edtxtuserName.requestFocus();
            result = false;
        }

        if(email.isEmpty()){
            binding.edtxtEmail.setError(getString(R.string.emptyEmailError) );
            binding.edtxtEmail.requestFocus();
            result = false;
        }

        if (password.isEmpty()) {
            binding.edtxtEmail.setError(getString(R.string.emptyPasswordError));
            binding.edtxtEmail.requestFocus();
            result = false;
        }
        if (password.length() < 6){
            binding.edtxtPassword.setError(getString(R.string.shortPasswordError));
            binding.edtxtPassword.requestFocus();
            result = false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.edtxtEmail.setError(getString(R.string.invalidEmailError));
            binding.edtxtPassword.requestFocus();
            result = false;
        }
        return result;
    }
   }