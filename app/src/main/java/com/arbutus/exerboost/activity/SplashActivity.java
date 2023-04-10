package com.arbutus.exerboost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.databinding.ActivityLoginBinding;
import com.arbutus.exerboost.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}