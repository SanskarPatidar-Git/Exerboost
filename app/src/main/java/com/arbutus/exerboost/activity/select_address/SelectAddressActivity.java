package com.arbutus.exerboost.activity.select_address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.add_new_address.AddNewAddressActivity;
import com.arbutus.exerboost.databinding.ActivitySelectAddressBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class SelectAddressActivity extends AppCompatActivity {

    private ActivitySelectAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivitySelectAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.header.headerTitleTextView.setText("Select Address");
        initListener();
        setAdapterForAddress();

        binding.stepView.getState()
                .selectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .animationType(StepView.ANIMATION_CIRCLE)
                .selectedCircleColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .selectedCircleRadius(getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._14sdp))
                .selectedStepNumberColor(ContextCompat.getColor(this, R.color.colorPrimary))
                // You should specify only stepsNumber or steps array of strings.
                // In case you specify both steps array is chosen.
                .steps(new ArrayList<String>() {{
                    add("First step");
                    add("Second step");
                    add("Third step");
                }})
                // You should specify only steps number or steps array of strings.
                // In case you specify both steps array is chosen.
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._1sdp))
                .textSize(getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._14ssp))
                .stepNumberTextSize(getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._16ssp))
                .typeface(ResourcesCompat.getFont(this, R.font.dmsans_medium))
                // other state methods are equal to the corresponding xml attributes
                .commit();
    }

    private void setAdapterForAddress() {
        SelectAddressAdapter adapter = new SelectAddressAdapter(this);
        binding.addressRecyclerView.setAdapter(adapter);

    }

    private void initListener() {

        binding.addNewAddressTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppBoilerPlateCode.navigateToActivity(SelectAddressActivity.this, AddNewAddressActivity.class,null);
            }
        });

        binding.header.headerBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.addressRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}