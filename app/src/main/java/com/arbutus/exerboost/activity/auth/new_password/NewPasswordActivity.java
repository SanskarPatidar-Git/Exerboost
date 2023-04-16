package com.arbutus.exerboost.activity.auth.new_password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.arbutus.exerboost.activity.auth.new_password.model.NewPasswordModel;
import com.arbutus.exerboost.activity.auth.register.RegisterActivity;
import com.arbutus.exerboost.databinding.ActivityNewPasswordBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.Validation;

public class NewPasswordActivity extends AppCompatActivity {

    private ActivityNewPasswordBinding binding;

    private Dialog progressDialog;
    private String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getBundles();
        initListener();
    }

    private void getBundles() {

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            authToken = bundle.getString("auth_token");
        }
    }

    private void initListener() {

        binding.cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.newPasswordEditText.addTextChangedListener(new TextWatcher() {
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

                int isValid = Validation.isValidConfirmPassword(binding.newPasswordEditText.getText().toString(),charSequence.toString());

                if(isValid==1){
                    binding.passwordInputLayout.setError("Enter password");
                    binding.newPasswordEditText.requestFocus();
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

        binding.updatePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = binding.newPasswordEditText.getText().toString().trim();
                String confirmPassword = binding.confirmPasswordEditText.getText().toString().trim();

                if (binding.passwordInputLayout.isErrorEnabled()) {
                    binding.newPasswordEditText.requestFocus();

                } else if (Validation.isStringEmpty(password)) {
                    binding.passwordInputLayout.setError("Enter password");
                    binding.newPasswordEditText.requestFocus();

                }else if (binding.confirmPasswordInputLayout.isErrorEnabled()) {
                    binding.confirmPasswordEditText.requestFocus();

                }else if (Validation.isStringEmpty(confirmPassword)) {
                    binding.confirmPasswordInputLayout.setError("Enter confirm password");
                    binding.confirmPasswordEditText.requestFocus();

                } else {

                    NewPasswordModel model = new NewPasswordModel(password);
                    UpdatePassword(model);
                }
            }
        });
    }

    private void UpdatePassword(NewPasswordModel model) {

        if (AppBoilerPlateCode.isInternetConnected(this)) {
            progressDialog = AppBoilerPlateCode.setProgressDialog(NewPasswordActivity.this);
            update(model);
        } else {
            AppBoilerPlateCode.showSnackBarForInternet(this,binding.rootLayoutOfNewPassword);
        }
    }

    private void update(NewPasswordModel model) {

        NewPasswordRepository repository = new NewPasswordRepository();
        repository.updatePasswordToServer(model,authToken);

        LiveData<String> successLiveData = repository.getSuccessResponseMutableData();
        successLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(NewPasswordActivity.this, s, Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        LiveData<String> failureLiveData = repository.getFailureResponseMutableData();
        failureLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(NewPasswordActivity.this, s, Toast.LENGTH_SHORT).show();
                System.out.println("========= Failure ============= ");
            }
        });
    }
}