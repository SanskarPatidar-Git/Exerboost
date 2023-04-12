package com.arbutus.exerboost.activity.auth.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.arbutus.exerboost.activity.auth.login.LoginActivity;
import com.arbutus.exerboost.activity.auth.register.models.request.RegisterModel;
import com.arbutus.exerboost.databinding.ActivityLoginBinding;
import com.arbutus.exerboost.databinding.ActivityRegisterBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.Validation;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private Dialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initListeners();
    }

    private void initListeners() {

        binding.skipTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppBoilerPlateCode.navigateToActivity(RegisterActivity.this, LoginActivity.class,null);
            }
        });

        binding.loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppBoilerPlateCode.navigateToActivity(RegisterActivity.this, LoginActivity.class,null);
            }
        });
        binding.emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int isValid = Validation.isValidEmail(charSequence.toString());

                if (isValid==1) {
                    binding.emailAddressInputLayout.setError("Enter email");
                } else if(isValid==2) {
                    binding.emailAddressInputLayout.setError("Invalid email format");
                } else if(isValid==0){
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.emailAddressInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                int isValid = Validation.isValidPassword(charSequence.toString());

                if(isValid==1)
                    binding.passwordInputLayout.setError("Enter password");
                else if(isValid==2)
                    binding.passwordInputLayout.setError("Password contains at least 8 characters");
                else if(isValid==3)
                    binding.passwordInputLayout.setError("Password contains at most 30 characters");
                else if(isValid==4)
                    binding.passwordInputLayout.setError("Password contains at least one upper case alphabet");
                else if(isValid==5)
                    binding.passwordInputLayout.setError("Password contains at least one lower case alphabet");
                else if(isValid==6)
                    binding.passwordInputLayout.setError("Password contains at least one digit");
                else if(isValid==7)
                    binding.passwordInputLayout.setError("Password contains at least one special character");
                else if(isValid==8)
                    binding.passwordInputLayout.setError("Password does not contain any white space");
                else if(isValid==0){
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.passwordInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.confirmPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                int isValid = Validation.isValidConfirmPassword(binding.passwordEditText.getText().toString(),charSequence.toString());

                if(isValid==1){
                    binding.passwordInputLayout.setError("Enter password");
                    binding.passwordEditText.requestFocus();
                }else if(isValid==2)
                    binding.confirmPasswordInputLayout.setError("Enter confirm password");
                else if(isValid==3)
                    binding.confirmPasswordInputLayout.setError("Password contains at least 8 characters");
                else if(isValid==4)
                    binding.confirmPasswordInputLayout.setError("Password not matched");
                else{
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.confirmPasswordInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.emailEditText.getText().toString().trim();
                String password = binding.passwordEditText.getText().toString().trim();
                String confirmPassword = binding.confirmPasswordEditText.getText().toString().trim();

                if (binding.emailAddressInputLayout.isErrorEnabled()) {
                    binding.emailEditText.requestFocus();
                } else if(Validation.isStringEmpty(email)) {
                    binding.emailAddressInputLayout.setError("Enter email");
                    binding.emailEditText.requestFocus();
                } else if (binding.passwordInputLayout.isErrorEnabled()) {
                    binding.passwordEditText.requestFocus();
                } else if (Validation.isStringEmpty(password)) {
                    binding.passwordInputLayout.setError("Enter password");
                    binding.passwordEditText.requestFocus();
                }else if (binding.confirmPasswordInputLayout.isErrorEnabled()) {
                    binding.confirmPasswordEditText.requestFocus();
                }else if (Validation.isStringEmpty(confirmPassword)) {
                    binding.confirmPasswordInputLayout.setError("Enter confirm password");
                    binding.confirmPasswordEditText.requestFocus();
                }else if(!binding.termsAndConditionCheckBox.isChecked()){
                    Toast.makeText(RegisterActivity.this, "please accept terms and policy", Toast.LENGTH_SHORT).show();
                }
                else {
                    RegisterModel registerModel = new RegisterModel(email,password,confirmPassword,true);
                    createUser(registerModel);
                }
            }
        });
    }

    private void createUser(RegisterModel model) {

        if (AppBoilerPlateCode.isInternetConnected(this)) {
            progressDialog = AppBoilerPlateCode.setProgressDialog(RegisterActivity.this);
            registerUser(model);
        } else {
            AppBoilerPlateCode.showSnackBarForInternet(this,binding.rootLayoutOfRegister);
        }
    }

    private void registerUser(RegisterModel model) {

        RegisterRepository repository = new RegisterRepository();
        LiveData<String> successLiveData = repository.getSuccessResponseMutableData();
        LiveData<String> failureLiveData = repository.getFailureResponseMutableData();
        repository.registerUserToServer(model);

        successLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();
                AppBoilerPlateCode.navigateToActivityWithFinish(RegisterActivity.this,LoginActivity.class,null);
            }
        });

        failureLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Failed : "+s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}