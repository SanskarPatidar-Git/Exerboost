package com.arbutus.exerboost.activity.auth.forgot_password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.arbutus.exerboost.activity.auth.forgot_password.models.ForgotPasswordModel;
import com.arbutus.exerboost.activity.auth.reset_password.ResetPasswordActivity;
import com.arbutus.exerboost.databinding.ActivityForgotPasswordBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.Validation;

public class ForgotPasswordActivity extends AppCompatActivity {
    public Dialog progressDialog;
    private ActivityForgotPasswordBinding binding;
    private String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initListener();
    }

    private void initListener() {

        binding.cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.emailAddressEditText.addTextChangedListener(new TextWatcher() {
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

        binding.sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = binding.emailAddressEditText.getText().toString().trim();

                if (binding.emailAddressInputLayout.isErrorEnabled()) {
                    binding.emailAddressEditText.requestFocus();

                } else if (Validation.isStringEmpty(email)) {
                    binding.emailAddressInputLayout.setError("Enter email");
                    binding.emailAddressEditText.requestFocus();

                } else {

                    if (AppBoilerPlateCode.isInternetConnected(ForgotPasswordActivity.this)) {

                        progressDialog = AppBoilerPlateCode.setProgressDialog(ForgotPasswordActivity.this);

                        ForgotPasswordModel model = new ForgotPasswordModel(email);
                        forgotPasswordUser(model);

                    } else {
                        AppBoilerPlateCode.showSnackBarForInternet(ForgotPasswordActivity.this, binding.rootLayoutOfForgotPassword);
                    }
                }
            }
        });
    }

    private void forgotPasswordUser(ForgotPasswordModel forgotPasswordModel ){

        ForgotPasswordRepository repository  = new ForgotPasswordRepository();
        LiveData<String> authTokenLiveData = repository.forgotPassUserToServer(forgotPasswordModel);

        authTokenLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                authToken = s;
            }
        });

        LiveData<String> successMutableData = repository.getSuccessResponseMutableData();
        successMutableData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();

                Toast.makeText(ForgotPasswordActivity.this, s, Toast.LENGTH_SHORT).show();
                System.out.println("========= OTP SENT ================");

                Bundle bundle =new Bundle();
                bundle.putString("auth_token",authToken);
                AppBoilerPlateCode.navigateToActivityWithFinish(ForgotPasswordActivity.this, ResetPasswordActivity.class,bundle);
            }
        });

        LiveData<String> failureMutableData = repository.getFailureResponseMutableData();

        failureMutableData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();

                System.out.println("============ Failure =========== "+s);
                Toast.makeText(ForgotPasswordActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
