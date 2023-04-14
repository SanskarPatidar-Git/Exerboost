package com.arbutus.exerboost.activity.select_address;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.arbutus.exerboost.activity.add_new_address.AddNewAddressActivity;
import com.arbutus.exerboost.databinding.ActivitySelectAddressBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;

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