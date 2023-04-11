package com.arbutus.exerboost.activity.auth.resetPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arbutus.exerboost.databinding.ActivityResetPasswordBinding;

public class ResetPasswordActivity extends AppCompatActivity {

    private ActivityResetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}