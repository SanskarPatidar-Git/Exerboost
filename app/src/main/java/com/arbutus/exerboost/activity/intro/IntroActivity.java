package com.arbutus.exerboost.activity.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.databinding.ActivityIntroBinding;
import com.arbutus.exerboost.databinding.ActivityLoginBinding;

public class IntroActivity extends AppCompatActivity {

    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}