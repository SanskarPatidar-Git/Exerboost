package com.arbutus.exerboost.activity.auth.forgot_password;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.arbutus.exerboost.activity.auth.login.model.response.Data;
import com.arbutus.exerboost.repository.remote.ApiController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordRepository {

    private MutableLiveData<String> failureResponseMutableData = new MutableLiveData<>();
    private MutableLiveData<Data> successResponseMutableData = new MutableLiveData<>();

    public LiveData<String> getFailureResponseMutableData() {
        return failureResponseMutableData;
    }

    public LiveData<Data> getSuccessResponseMutableData() {
        return successResponseMutableData;
    }

    public void forgotPassUserToServer(ForgotPasswordModel forgotPasswordModel) {

        Call<ForgotPasswordResponse> call = ApiController.getInstance().getApiSets().forgotPasswordUser(forgotPasswordModel);

        call.enqueue(new Callback<ForgotPasswordResponse>() {
            @Override
            public void onResponse(Call<ForgotPasswordResponse> call, Response<ForgotPasswordResponse> response) {

                ForgotPasswordResponse model = response.body();
                if (model != null) {

                    if (response.isSuccessful() && model.isStatus()) {
                        successResponseMutableData.setValue(model.getData());
                    } else {
                        failureResponseMutableData.setValue(model.getMessage());
                    }
                }
                else {
                    Gson gson = new GsonBuilder().create();
                    ForgotPasswordResponse responseModel = null;
                    try {
                        responseModel = gson.fromJson(response.errorBody().string(), ForgotPasswordResponse.class);
                        System.out.println("================ NULL MODEL =================== " + responseModel.getMessage());
                        failureResponseMutableData.setValue(responseModel.getMessage());
                    } catch (Exception e) {
                        System.out.println("============= IO EXCEPTION ========== " + e.getMessage());
                        failureResponseMutableData.setValue("Failed");
                    }
                }

            }

            @Override
            public void onFailure(Call<ForgotPasswordResponse> call, Throwable t) {
                System.out.println("================ ERROR =================== " + t.getMessage());
                failureResponseMutableData.setValue("Error");
            }
        });
    }

}
