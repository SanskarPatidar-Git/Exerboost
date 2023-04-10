package com.arbutus.exerboost.activity.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.databinding.ActivityIntroBinding;
import com.arbutus.exerboost.databinding.ActivityLoginBinding;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {

    private ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setAdapter();
    }

    private void setAdapter() {

        ArrayList<IntroModel> introModelArrayList = new ArrayList<>();
        introModelArrayList.add(new IntroModel("Order from your favourite stores or vendors",R.drawable.slider_image_one));
        introModelArrayList.add(new IntroModel("Choose from a wide range of delicious meals",R.drawable.slider_image_second));
        introModelArrayList.add(new IntroModel("Enjoy instant delivery and payment",R.drawable.slider_image_three));

        IntroSliderAdapter adapter = new IntroSliderAdapter(this,introModelArrayList);

        binding.viewPager.setAdapter(adapter);
    }
}