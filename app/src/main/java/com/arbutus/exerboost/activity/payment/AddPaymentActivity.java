package com.arbutus.exerboost.activity.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arbutus.exerboost.databinding.ActivityAddPaymentBinding;

public class AddPaymentActivity extends AppCompatActivity {

    private ActivityAddPaymentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}