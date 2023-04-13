package com.arbutus.exerboost.activity.add_new_address;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.arbutus.exerboost.activity.payment.card.AddCardModel;
import com.arbutus.exerboost.activity.payment.card.AddCardRepository;
import com.arbutus.exerboost.activity.payment.card.AddNewCardActivity;
import com.arbutus.exerboost.databinding.ActivityAddNewAddressBinding;
import com.arbutus.exerboost.databinding.ActivityAddNewCardBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.Validation;

public class AddNewAddressActivity extends AppCompatActivity {
private ActivityAddNewAddressBinding binding;
    private Dialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.header.headerTitleTextView.setText("Add New Address");

        initListeners();
    }

    private void initListeners() {

        binding.header.headerBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.streetOneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    binding.streetOneInputLayout.setError("Enter Street 1");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.streetOneInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.streetTwoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    binding.streetTwoInputLayout.setError("Enter Street 2");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.streetTwoInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.cityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    binding.cityInputLayout.setError("Enter City");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.cityInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.stateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    binding.stateInputLayout.setError("Enter State");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.stateInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.countryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    binding.countryInputLayout.setError("Enter Country");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.countryInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.postCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    binding.postCodeInputLayout.setError("Enter Post Code");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.postCodeInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String streetOne = binding.streetOneEditText.getText().toString().trim();
                String streetTwo = binding.streetTwoEditText.getText().toString().trim();
                String city = binding.cityEditText.getText().toString().trim();
                String state = binding.stateEditText.getText().toString().trim();
                String country = binding.countryEditText.getText().toString().trim();
                String postCode = binding.postCodeEditText.getText().toString().trim();

                if(Validation.isStringEmpty(streetOne)){
                    binding.streetOneInputLayout.setError("Enter Street 1");
                } else if(binding.streetOneInputLayout.isErrorEnabled()){
                    binding.streetOneEditText.requestFocus();
                } else if(Validation.isStringEmpty(streetTwo)){
                    binding.streetTwoInputLayout.setError("Enter Street 2");
                }  else if(binding.streetTwoInputLayout.isErrorEnabled()){
                    binding.streetTwoEditText.requestFocus();
                } else if(Validation.isStringEmpty(city)){
                    binding.cityInputLayout.setError("Enter City");
                }  else if(binding.cityInputLayout.isErrorEnabled()){
                    binding.cityEditText.requestFocus();
                } else if(Validation.isStringEmpty(state)){
                    binding.stateInputLayout.setError("Enter State");
                }  else if(binding.stateInputLayout.isErrorEnabled()){
                    binding.stateEditText.requestFocus();
                } else if(Validation.isStringEmpty(country)) {
                    binding.countryInputLayout.setError("Enter Country");
                }  else if(binding.countryInputLayout.isErrorEnabled()){
                        binding.countryEditText.requestFocus();
                } else if(Validation.isStringEmpty(postCode)) {
                    binding.postCodeInputLayout.setError("Enter Post Code");
                }  else if(binding.postCodeInputLayout.isErrorEnabled()) {
                    binding.postCodeEditText.requestFocus();
                }
                else {

                    AddNewAddressModel model = new AddNewAddressModel(streetOne,streetTwo,city,state,country,postCode);
                    addNewAddress(model);
                }
            }
        });

    }

    private void addNewAddress(AddNewAddressModel model) {

        if(AppBoilerPlateCode.isInternetConnected(this)){
            progressDialog = AppBoilerPlateCode.setProgressDialog(AddNewAddressActivity.this);
            observeResponse(model);
        }
        else {
            AppBoilerPlateCode.showSnackBarForInternet(AddNewAddressActivity.this,binding.rootLayoutOfAddNewAddress);
        }
    }

    private void observeResponse(AddNewAddressModel model) {
        AddNewAddressRepository repository = new AddNewAddressRepository();
        repository.addNewAddress(model);
    }
    }