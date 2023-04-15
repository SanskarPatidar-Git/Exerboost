package com.arbutus.exerboost.activity.address.add_new_address;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.arbutus.exerboost.R;
import com.arbutus.exerboost.databinding.ActivityAddNewAddressBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.Validation;

import java.util.ArrayList;

public class AddNewAddressActivity extends AppCompatActivity {
    private ActivityAddNewAddressBinding binding;
    private Dialog progressDialog;

    private String addressType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.header.headerTitleTextView.setText("Add New Address");

        initListeners();
        setSpinnerAdapter();
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

        binding.addressTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try{
                    TextView textView = (TextView) adapterView.getSelectedView();
                    textView.setTextColor(getColor(R.color.colorSecondary));
                    textView.setTypeface(getTypeFace());
                    if(i!=0){
                        addressType = adapterView.getSelectedItem().toString();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                boolean isDefault = false;

                //============================= VALIDATE FIELDS ================================

                if(Validation.isStringEmpty(streetOne)){
                    binding.streetOneInputLayout.setError("Enter Street 1");
                    binding.streetOneEditText.requestFocus();

                } else if(binding.streetOneInputLayout.isErrorEnabled()){
                    binding.streetOneEditText.requestFocus();

                } else if(Validation.isStringEmpty(streetTwo)){
                    binding.streetTwoInputLayout.setError("Enter Street 2");
                    binding.streetTwoEditText.requestFocus();

                }  else if(binding.streetTwoInputLayout.isErrorEnabled()){
                    binding.streetTwoEditText.requestFocus();

                } else if(Validation.isStringEmpty(city)){
                    binding.cityInputLayout.setError("Enter City");
                    binding.cityEditText.requestFocus();

                }  else if(binding.cityInputLayout.isErrorEnabled()){
                    binding.cityEditText.requestFocus();

                } else if(Validation.isStringEmpty(state)){
                    binding.stateInputLayout.setError("Enter State");
                    binding.stateEditText.requestFocus();

                }  else if(binding.stateInputLayout.isErrorEnabled()){
                    binding.stateEditText.requestFocus();

                } else if(Validation.isStringEmpty(country)) {
                    binding.countryInputLayout.setError("Enter Country");
                    binding.countryEditText.requestFocus();

                }  else if(binding.countryInputLayout.isErrorEnabled()){
                        binding.countryEditText.requestFocus();

                } else if(Validation.isStringEmpty(postCode)) {
                    binding.postCodeInputLayout.setError("Enter Post Code");
                    binding.postCodeEditText.requestFocus();

                }  else if(binding.postCodeInputLayout.isErrorEnabled()) {
                    binding.postCodeEditText.requestFocus();

                } else if(Validation.isStringEmpty(addressType)){
                    Toast.makeText(AddNewAddressActivity.this, "Please select address type", Toast.LENGTH_SHORT).show();

                } else {

                    if(binding.setAsDefaultCheckBox.isChecked())
                        isDefault = true;

                    AddNewAddressModel model = new AddNewAddressModel(streetOne,streetTwo,city,state,country,postCode,addressType , isDefault);
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

        LiveData<String> successLiveData = repository.getSuccessResponseMutableData();
        LiveData<String> failureLiveData = repository.getFailureResponseMutableData();

        successLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(AddNewAddressActivity.this, s, Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        failureLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(AddNewAddressActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void  setSpinnerAdapter(){

        ArrayList<String> addressTypeList = new ArrayList<>();
        addressTypeList.add("Select Address Type");
        addressTypeList.add("Home");
        addressTypeList.add("Work");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,addressTypeList){

            @Override
            public boolean isEnabled(int position) {
                return position!=0;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView textView = (TextView) view;
                if(position==0){
                    textView.setTextColor(getColor(R.color.black_text_disabled));
                }
                textView.setTypeface(getTypeFace());
                return view;
            }

        };
        binding.addressTypeSpinner.setAdapter(adapter);
    }

    private Typeface getTypeFace(){
        Typeface typeface = ResourcesCompat.getFont(this, R.font.dmsans_medium);
        return typeface;
    }
}