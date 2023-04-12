package com.arbutus.exerboost.activity.auth.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.arbutus.exerboost.activity.MainActivity;
import com.arbutus.exerboost.activity.auth.login.model.response.Data;
import com.arbutus.exerboost.activity.auth.login.model.request.LoginModel;
import com.arbutus.exerboost.databinding.ActivityLoginBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;
import com.arbutus.exerboost.utilities.Validation;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private Dialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initListener();
    }

    private void initListener() {

        binding.emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (Validation.isStringEmpty(charSequence.toString())) {
                    binding.emailAddressInputLayout.setError("Enter email or username");
                } else {
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
                if (Validation.isStringEmpty(charSequence.toString())) {
                    binding.passwordInputLayout.setError("Enter password");
                } else {
                    AppBoilerPlateCode.setInputLayoutErrorDisable(binding.passwordInputLayout);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.signInWithGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = binding.emailEditText.getText().toString().trim();
                String password = binding.passwordEditText.getText().toString().trim();

                 if (Validation.isStringEmpty(email)) {
                    binding.emailAddressInputLayout.setError("Enter email or username");
                    binding.emailEditText.requestFocus();

                } else if (Validation.isStringEmpty(password)) {
                    binding.passwordEditText.requestFocus();
                    binding.passwordInputLayout.setError("Enter password");

                } else {

                     if(AppBoilerPlateCode.isInternetConnected(LoginActivity.this)){

                         progressDialog = AppBoilerPlateCode.setProgressDialog(LoginActivity.this);
                         LoginModel loginModel = new LoginModel(email,password);
                         loginUser(loginModel);
                     }
                     else {
                         AppBoilerPlateCode.showSnackBarForInternet(LoginActivity.this,binding.rootLayoutOfLogin);
                     }
                }
            }
        });
    }

    private void loginUser(LoginModel loginModel){
        //todo login user

        LoginRepository repository = new LoginRepository();
        LiveData<String> failureResponseLiveData = repository.getFailureResponseMutableData();
        LiveData<Data> successResponseLiveData = repository.getSuccessResponseMutableData();

        repository.loginUserToServer(loginModel);

        failureResponseLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();
                System.out.println("FAILURE ++++++++++++++++++ "+s);
            }
        });

        successResponseLiveData.observe(this, new Observer<Data>() {
            @Override
            public void onChanged(Data data) {
                // todo store data and token in local storage
                progressDialog.dismiss();
                System.out.println("SUCCESS ++++++++++++++++++ ");
                repository.setUserDataToLocal(LoginActivity.this);

                AppBoilerPlateCode.navigateToActivityWithFinish(LoginActivity.this, MainActivity.class,null);
            }
        });
    }
}