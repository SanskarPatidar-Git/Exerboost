package com.arbutus.exerboost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.intro.IntroActivity;
import com.arbutus.exerboost.databinding.ActivityLoginBinding;
import com.arbutus.exerboost.databinding.ActivitySplashBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppBoilerPlateCode.navigateToActivity(SplashActivity.this, IntroActivity.class,null);
            }
        },2000);
    }
}