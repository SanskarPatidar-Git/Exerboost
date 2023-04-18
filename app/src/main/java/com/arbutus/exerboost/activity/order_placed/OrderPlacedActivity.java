package com.arbutus.exerboost.activity.order_placed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.arbutus.exerboost.activity.main.activity.MainActivity;
import com.arbutus.exerboost.databinding.ActivityOrderPlacedBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;

public class OrderPlacedActivity extends AppCompatActivity {
    ActivityOrderPlacedBinding binding;

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

        binding.giveRatingButton.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            AlertDialog.Builder builder = new AlertDialog.Builder(OrderPlacedActivity.this);
                                                            View dialogView = getLayoutInflater().inflate(R.layout.dialog_rating, null);
                                                            builder.setView(dialogView);
                                                            AlertDialog customDialog = builder.create();
                                                            customDialog.show();
                                                            onBackPressed();
                                                        }
                                                    });
    }
    @Override
    public void onBackPressed() {
        AppBoilerPlateCode.navigateToActivity(OrderPlacedActivity.this, MainActivity.class,null);
    }
}