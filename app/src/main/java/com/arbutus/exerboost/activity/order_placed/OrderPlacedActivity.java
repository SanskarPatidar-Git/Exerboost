package com.arbutus.exerboost.activity.order_placed;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import com.example.library_starview.SmartRate;
import com.arbutus.exerboost.databinding.ActivityOrderPlacedBinding;

public class OrderPlacedActivity extends AppCompatActivity {
ActivityOrderPlacedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderPlacedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

binding.goBackToHomeButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

//        SmartRate.Rate(OrderPlacedActivity.this
//                        , "Rate Us"
//                        , "Tell others what you think about this app"
//                        , "Continue"
//                        , "Please take a moment and rate us on Google Play"
//                        , "click here"
//                        , "Cancel"
//                        , "Thanks for the feedback"
//                        , Color.parseColor("#0878D1")
//                        , 4
//                        , new SmartRate.CallBack_UserRating() {
//                            @Override
//                            public void userRating(int rating) {
//                                // Do something
//                                // maybe from now disable this button
//                            }
//                        }
//                );
        SmartRate.Rate(OrderPlacedActivity.this
                , "Rate Our Rider"
                , "Kevin peter"
                , "Continue"
                , "Please take a moment and rate us on Google Play"
                , "click here"
                , "Ask me later"
                , "Never ask again"
                , "Cancel"
                , "Thanks for the feedback"
                , Color.parseColor("#F0821D")
                , 4
                , 0
                , 0
        );

    }
});

    }
}