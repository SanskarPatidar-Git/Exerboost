package com.arbutus.exerboost.activity.auth.new_password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arbutus.exerboost.databinding.ActivityNewPasswordBinding;

public class NewPasswordActivity extends AppCompatActivity {

    private ActivityNewPasswordBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}