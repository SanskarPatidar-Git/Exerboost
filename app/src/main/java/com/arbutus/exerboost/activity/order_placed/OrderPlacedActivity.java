package com.arbutus.exerboost.activity.order_placed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.MainActivity;
import com.arbutus.exerboost.databinding.ActivityOrderPlacedBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;

public class OrderPlacedActivity extends AppCompatActivity {

    private ActivityOrderPlacedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderPlacedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.headerBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });
        binding.goBackHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    @Override
    public void onBackPressed() {
        AppBoilerPlateCode.navigateToActivity(OrderPlacedActivity.this, MainActivity.class,null);
    }
}