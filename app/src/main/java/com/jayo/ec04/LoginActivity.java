package com.jayo.ec04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.jayo.ec04.databinding.ActivityLoginBinding;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private SharedPreferences sharedPreferences;
    public static  String SESSION_PREFERENCE = "SESSION_PREFERENCE";
    public static  String SESSION_ACTIVATED = "SESSION_ACTIVATED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences(SESSION_PREFERENCE,MODE_PRIVATE);
        binding.btnLogin.setOnClickListener(v -> {
            sharedPreferences.edit().putBoolean(SESSION_ACTIVATED,true).apply();
            //Toast.makeText(this,"Login Press", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PrincipalActivity.class);
            intent.putExtra(PrincipalActivity.EMAIL,binding.tilEmail.getEditText().getText().toString());
            startActivity(intent);
            finish();
        });
        binding.tilEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isOk = isCredentialsValidate(s.toString(),binding.tilPassword.getEditText().getText().toString());
                binding.btnLogin.setEnabled(isOk);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.tilPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean isOk = isCredentialsValidate(binding.tilEmail.getEditText().getText().toString(),s.toString());
                binding.btnLogin.setEnabled(isOk);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private boolean isCredentialsValidate(String email, String password) {
        boolean isEmailValid = email.equals("ejemplo@idat.com.pe");
        boolean isPasswordValid = password.equals("Peru123.");

        if (!isEmailValid) {
            binding.tilEmail.setError("Credenciales incorrectas");
        } else {
            binding.tilEmail.setError(null);
        }

        if (!isPasswordValid) {
            binding.tilPassword.setError("Credenciales incorrectas");
        } else {
            binding.tilPassword.setError(null);
        }

        return isEmailValid && isPasswordValid;
    }

}