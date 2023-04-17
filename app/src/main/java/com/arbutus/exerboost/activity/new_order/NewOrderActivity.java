package com.arbutus.exerboost.activity.new_order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.activity.continue_new_order.activity.ContinueNewOrderActivity;
import com.arbutus.exerboost.databinding.ActivityNewOrderBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.Validation;

public class NewOrderActivity extends AppCompatActivity {

    private ActivityNewOrderBinding binding;

    private NewOrderViewModel newOrderViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.header.headerTitleTextView.setText("New Order");
        newOrderViewModel = new ViewModelProvider(this).get(NewOrderViewModel.class);
        getBundles();

        getDataFromViewModel();
        initListener();
    }

    private void getDataFromViewModel() {

        String goal = newOrderViewModel.getGoal();
        if(!Validation.isStringEmpty(goal)){

          if(goal.equals("Stay Healthy")){
              binding.stayHealthyCardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
          } else if(goal.equals("Lost Fat")){
              binding.lostFatCardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
          } else {
              binding.ketoDietCardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
          }
        }

        String category = newOrderViewModel.getCategory();
        if(!Validation.isStringEmpty(category)){

            if(category.equals("15 days")){
                binding.day15CardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
            } else if(category.equals("25 days")){
                binding.day25CardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
            } else {
                binding.day75CardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
            }
        }
    }

    private void getBundles(){
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            newOrderViewModel.setProductId(bundle.getString("product_id"));
        }
    }

    private void initListener() {

        binding.categoryRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = radioGroup.getCheckedRadioButtonId();

                if(id == binding.vegCategoryRadioButton.getId()){
                    newOrderViewModel.setCategory("veg");
                } else if(id == binding.veganCategoryRadioButton.getId()){
                    newOrderViewModel.setCategory("vegan");
                } else {
                    newOrderViewModel.setCategory("non veg");
                }
            }
        });
        //================== MY GOAL ====================
        binding.stayHealthyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newOrderViewModel.setGoal("Stay Healthy");
                binding.stayHealthyCardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
                binding.lostFatCardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
                binding.ketoDietCardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
            }
        });

        binding.lostFatCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newOrderViewModel.setGoal("Lost Fat");
                binding.stayHealthyCardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
                binding.lostFatCardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
                binding.ketoDietCardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
            }
        });

        binding.ketoDietCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newOrderViewModel.setGoal("Keto Diet");
                binding.stayHealthyCardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
                binding.lostFatCardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
                binding.ketoDietCardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
            }
        });

        //======================= COMFORTABLE START WITH =========================

        binding.day15CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newOrderViewModel.setDuration("15 days");
                binding.day15CardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
                binding.day25CardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
                binding.day75CardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
            }
        });

        binding.day25CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newOrderViewModel.setDuration("25 days");
                binding.day15CardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
                binding.day25CardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
                binding.day75CardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
            }
        });

        binding.day75CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newOrderViewModel.setDuration("75 days");
                binding.day15CardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
                binding.day25CardView.setBackgroundResource(R.drawable.unselected_item_background_drawable);
                binding.day75CardView.setBackgroundResource(R.drawable.selected_item_background_drawable);
            }
        });

        binding.proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Validation.isStringEmpty(newOrderViewModel.getCategory())){
                    Toast.makeText(NewOrderActivity.this, "Please select Category", Toast.LENGTH_SHORT).show();
                }
                else if(Validation.isStringEmpty(newOrderViewModel.getGoal())){
                    Toast.makeText(NewOrderActivity.this, "Please select your goal", Toast.LENGTH_SHORT).show();

                } else if(Validation.isStringEmpty(newOrderViewModel.getDuration())){
                    Toast.makeText(NewOrderActivity.this, "Please select duration", Toast.LENGTH_SHORT).show();

                } else {

                    NewOrderModel newOrderModel = new NewOrderModel(newOrderViewModel.getProductId(), null , newOrderViewModel.getGoal(), newOrderViewModel.getDuration() , newOrderViewModel.getCategory());

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("order_data",newOrderModel);
                    System.out.println( "===================== "+newOrderModel.toString());
                    AppBoilerPlateCode.navigateToActivity(NewOrderActivity.this, ContinueNewOrderActivity.class, bundle);
                }
            }
        });
    }
}