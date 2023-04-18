package com.arbutus.exerboost.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.arbutus.exerboost.activity.intro.IntroActivity;
import com.arbutus.exerboost.activity.main.activity.MainActivity;
import com.arbutus.exerboost.databinding.ActivitySplashBinding;
import com.arbutus.exerboost.repository.local.LocalController;
import com.arbutus.exerboost.repository.local.LocalSets;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.Validation;

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
                String authToken = LocalSets.getAuthToken(LocalController.getInstance(SplashActivity.this).getPreferences());

                if(Validation.isStringEmpty(authToken)){
                    AppBoilerPlateCode.navigateToActivityWithFinish(SplashActivity.this, IntroActivity.class,null);
                } else
                    AppBoilerPlateCode.navigateToActivityWithFinish(SplashActivity.this, MainActivity.class,null);

            }
        },2000);
    }
}