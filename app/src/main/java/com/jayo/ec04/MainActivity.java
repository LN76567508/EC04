package com.jayo.ec04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jayo.ec04.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    //private Button btnGetStarted;
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        sharedPreferences = getSharedPreferences(LoginActivity.SESSION_PREFERENCE,MODE_PRIVATE);
        setContentView(binding.getRoot());
        boolean isSessionActivated = sharedPreferences.getBoolean(LoginActivity.SESSION_ACTIVATED,false);
        if(isSessionActivated){
            Intent intent = new Intent(this, PrincipalActivity.class);
            startActivity(intent);
            finish();
        }
        binding.btnGetStarted.setOnClickListener(v->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();

        });
        binding.txtMoview.setText(R.string.get_started_title);

    }
}