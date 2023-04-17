package com.arbutus.exerboost.activity.auth.forgot_password;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import com.arbutus.exerboost.activity.auth.login.LoginActivity;
import com.arbutus.exerboost.activity.auth.login.LoginRepository;
import com.arbutus.exerboost.activity.auth.login.model.request.LoginModel;
import com.arbutus.exerboost.activity.auth.resetPassword.ResetPasswordActivity;
import com.arbutus.exerboost.databinding.ActivityForgotPasswordBinding;
import com.arbutus.exerboost.databinding.ActivityLoginBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.Validation;

public class ForgotPasswordActivity extends AppCompatActivity {
    public Dialog progressDialog;
    private ActivityForgotPasswordBinding binding;
    private ForgotPasswordRepository repository  = new ForgotPasswordRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailAddressEditText.getText().toString().trim();
                if (binding.emailAddressInputLayout.isErrorEnabled()) {
                    binding.emailAddressEditText.requestFocus();
                } else if (Validation.isStringEmpty(email)) {
                    binding.emailAddressInputLayout.setError("Enter email Address");
                    binding.emailAddressEditText.requestFocus();
                } else {
                    if (AppBoilerPlateCode.isInternetConnected(ForgotPasswordActivity.this)) {
                        progressDialog = AppBoilerPlateCode.setProgressDialog(ForgotPasswordActivity.this);
                        ForgotPasswordModel forgotPasswordModel = new ForgotPasswordModel(email);
                        forgotPasswordUser(forgotPasswordModel);
                    } else {
                        AppBoilerPlateCode.showSnackBarForInternet(ForgotPasswordActivity.this, binding.rootLayoutOfForgotPassword);
                    }
                }
            }
        });
    }

    private void forgotPasswordUser(ForgotPasswordModel forgotPasswordModel ){
        //todo login user
        repository.forgotPassUserToServer(forgotPasswordModel);
    }

}
