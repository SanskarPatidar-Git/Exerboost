package com.arbutus.exerboost.activity.auth.reset_password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arbutus.exerboost.activity.auth.forgot_password.ForgotPasswordActivity;
import com.arbutus.exerboost.activity.auth.forgot_password.models.ForgotPasswordModel;
import com.arbutus.exerboost.activity.auth.new_password.NewPasswordActivity;
import com.arbutus.exerboost.activity.auth.reset_password.model.ResetPasswordModel;
import com.arbutus.exerboost.databinding.ActivityResetPasswordBinding;
import com.arbutus.exerboost.utilities.AppBoilerPlateCode;

public class ResetPasswordActivity extends AppCompatActivity {

    private ActivityResetPasswordBinding binding;
    private String authToken;

    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initBundle();
        initListener();

    }

    private void initBundle() {

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            authToken = bundle.getString("auth_token");
        }
    }

    private void initListener() {

        binding.cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String otp = binding.pinView.getText().toString();
                if(otp.length()==0){
                    Toast.makeText(ResetPasswordActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                } else if(otp.length()<4){
                    Toast.makeText(ResetPasswordActivity.this, "Enter 4 digit OTP", Toast.LENGTH_SHORT).show();
                } else {

                    verifyOtp(otp);
                }
            }
        });

    }

    private void verifyOtp(String otp) {

        if (AppBoilerPlateCode.isInternetConnected(ResetPasswordActivity.this)) {

            progressDialog = AppBoilerPlateCode.setProgressDialog(ResetPasswordActivity.this);
            observeResponse(otp);

        } else {
            AppBoilerPlateCode.showSnackBarForInternet(ResetPasswordActivity.this, binding.rootLayoutOfResetPassword);
        }
    }

    private void observeResponse(String otp) {

        ResetPasswordModel model = new ResetPasswordModel(otp);

        ResetPasswordRepository repository = new ResetPasswordRepository();
        LiveData<String> authTokenLiveData = repository.resetPassword(model, authToken);

        LiveData<String> successLiveData = repository.getSuccessResponseMutableData();
        successLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                progressDialog.dismiss();
                System.out.println("============== VALID ============ "+s);
            }
        });

        LiveData<String> failureLiveData = repository.getFailureResponseMutableData();
        failureLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

                progressDialog.dismiss();
                Toast.makeText(ResetPasswordActivity.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                System.out.println("============= INVALID OTP =================");
            }
        });

        authTokenLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                System.out.println("============ AUTH TOKEN =========== "+s);
                authToken = s;
                Bundle bundle = new Bundle();
                bundle.putString("auth_token",authToken);
                AppBoilerPlateCode.navigateToActivityWithFinish(ResetPasswordActivity.this, NewPasswordActivity.class,bundle);
            }
        });
    }
}