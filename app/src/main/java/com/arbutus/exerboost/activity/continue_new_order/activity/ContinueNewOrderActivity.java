package com.arbutus.exerboost.activity.continue_new_order.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.address.add_new_address.AddNewAddressActivity;
import com.arbutus.exerboost.activity.continue_new_order.fragments.address.SelectAddressFragment;
import com.arbutus.exerboost.databinding.ActivityContinueNewOrderBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.FragmentController;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;

public class ContinueNewOrderActivity extends AppCompatActivity {

    private ActivityContinueNewOrderBinding binding;

    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding =ActivityContinueNewOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initListener();

        FragmentController.addFragment(fragmentManager,R.id.fragmentContainer,new SelectAddressFragment());
    }

    private void initListener() {

        binding.stepView.getState()
                .selectedTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .animationType(StepView.ANIMATION_CIRCLE)
                .selectedCircleColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .selectedCircleRadius(getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._14sdp))
                .selectedStepNumberColor(ContextCompat.getColor(this, R.color.colorPrimary))
                // You should specify only stepsNumber or steps array of strings.
                // In case you specify both steps array is chosen.
                .steps(new ArrayList<String>() {{
                    add("Address");
                    add("Payment");
                    add("Summary");
                }})
                // You should specify only steps number or steps array of strings.
                // In case you specify both steps array is chosen.
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(getResources().getDimensionPixelSize(com.intuit.sdp.R.dimen._1sdp))
                .textSize(getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._12ssp))
                .stepNumberTextSize(getResources().getDimensionPixelSize(com.intuit.ssp.R.dimen._16ssp))
                .typeface(ResourcesCompat.getFont(this, R.font.dmsans_medium))
                // other state methods are equal to the corresponding xml attributes
                .commit();

//        binding.addNewAddressTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AppBoilerPlateCode.navigateToActivity(ContinueNewOrderActivity.this, AddNewAddressActivity.class,null);
//            }
//        });
//
//        binding.header.headerBackImageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });

    }

}