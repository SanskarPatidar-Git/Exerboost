package com.arbutus.exerboost.activity.payment.card;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.arbutus.exerboost.activity.auth.login.LoginActivity;
import com.arbutus.exerboost.activity.auth.login.model.request.LoginModel;
import com.arbutus.exerboost.databinding.ActivityAddNewCardBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.CardFormat;
import com.arbutus.exerboost.utilities.Validation;

public class AddNewCardActivity extends AppCompatActivity {

    private ActivityAddNewCardBinding binding;

    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddNewCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.header.headerTitleTextView.setText("Add New Card");
        initListeners();
    }

    private void initListeners() {

        binding.header.headerBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.nameOnCardEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               if(charSequence.length()==0){
                   binding.nameOnCardInputLayout.setError("Enter name");
               } else {
                   AppBoilerPlateCode.setInputLayoutErrorDisable(binding.nameOnCardInputLayout);
               }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.cardNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = charSequence.length();
                if(length==0){
                    binding.cardNumberInputLayout.setError("Enter card number");
                } else if(length>0 && length<19){
                    CardFormat.cardFormatting((Editable)charSequence);
                    binding.cardNumberInputLayout.setError("Invalid card number");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.cardNumberInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.expiryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    binding.expiryInputLayout.setError("Enter Expiry date");
                } else if(!CardFormat.isExpiryFormatIsOk((Editable) charSequence)) {
                    binding.expiryInputLayout.setError("Invalid Expiry date");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.expiryInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.cvvEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = charSequence.length();
                if(length==0){
                    binding.cvvInputLayout.setError("Enter CVV");
                } else if(length>0 && length<3){
                    binding.cvvInputLayout.setError("Invalid CVV");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.cvvInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.billingAddressEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length()==0){
                    binding.billingAddressInputLayout.setError("Enter billing address");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.billingAddressInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cardName = binding.nameOnCardEditText.getText().toString().trim();
                String cardNumber = binding.cardNumberEditText.getText().toString().trim();
                String expiryDate = binding.expiryEditText.getText().toString().trim();
                String cvv = binding.cvvEditText.getText().toString().trim();
                String billingAddress = binding.billingAddressEditText.getText().toString().trim();

                if(Validation.isStringEmpty(cardName)){
                    binding.nameOnCardInputLayout.setError("Enter name ");
                    binding.nameOnCardEditText.requestFocus();
                } else if(binding.nameOnCardInputLayout.isErrorEnabled()){
                    binding.nameOnCardEditText.requestFocus();
                } else if(Validation.isStringEmpty(cardNumber)){
                    binding.cardNumberInputLayout.setError("Enter card number ");
                    binding.cardNumberEditText.requestFocus();
                }  else if(binding.cardNumberInputLayout.isErrorEnabled()){
                    binding.cardNumberEditText.requestFocus();
                } else if(Validation.isStringEmpty(expiryDate)){
                    binding.expiryInputLayout.setError("Enter expiry date ");
                    binding.expiryEditText.requestFocus();
                }  else if(binding.expiryInputLayout.isErrorEnabled()){
                    binding.expiryEditText.requestFocus();
                } else if(Validation.isStringEmpty(cvv)){
                    binding.cvvInputLayout.setError("Enter cvv ");
                    binding.cvvEditText.requestFocus();
                }  else if(binding.cvvInputLayout.isErrorEnabled()){
                    binding.cvvEditText.requestFocus();
                } else if(Validation.isStringEmpty(billingAddress)){
                    binding.billingAddressInputLayout.setError("Enter billing address");
                    binding.billingAddressEditText.requestFocus();
                } else {

                    AddCardModel model = new AddCardModel(cardName,cardNumber,expiryDate,cvv,billingAddress);
                    addCard(model);
                }
            }
        });
    }

    private void addCard(AddCardModel model) {

        if(AppBoilerPlateCode.isInternetConnected(this)){
            progressDialog = AppBoilerPlateCode.setProgressDialog(AddNewCardActivity.this);
            observeResponse(model);
        }
        else {
            AppBoilerPlateCode.showSnackBarForInternet(AddNewCardActivity.this,binding.rootLayoutOfAddNewCard);
        }
    }

    private void observeResponse(AddCardModel model) {
        AddCardRepository repository = new AddCardRepository();
        repository.addCard(model);
    }
}