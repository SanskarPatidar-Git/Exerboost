package com.arbutus.exerboost.activity.auth.register;

import androidx.lifecycle.MutableLiveData;

import com.arbutus.exerboost.activity.auth.login.model.response.LoginResponse;
import com.arbutus.exerboost.activity.auth.register.models.request.RegisterModel;
import com.arbutus.exerboost.activity.auth.register.models.response.RegisterResponse;
import com.arbutus.exerboost.repository.remote.ApiController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {

    private MutableLiveData<String> successResponseMutableData = new MutableLiveData<>();

    private MutableLiveData<String> failureResponseMutableData = new MutableLiveData<>();

    public MutableLiveData<String> getSuccessResponseMutableData() {
        return successResponseMutableData;
    }

    public MutableLiveData<String> getFailureResponseMutableData() {
        return failureResponseMutableData;
    }

    public void registerUserToServer(RegisterModel model){

        Call<RegisterResponse> call = ApiController.getInstance().getApiSets().registerUser(model);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                RegisterResponse registerResponse = response.body();
                if (registerResponse != null) {

                    if (response.isSuccessful() && registerResponse.isStatus()) {
                        successResponseMutableData.setValue(registerResponse.getMessage());
                    } else {
                        failureResponseMutableData.setValue(registerResponse.getMessage());
                    }
                }
                else {
                    Gson gson = new GsonBuilder().create();
                    RegisterResponse responseModel = null;
                    try {
                        responseModel = gson.fromJson(response.errorBody().string(), RegisterResponse.class);
                    } catch (IOException e) {
                        System.out.println("============= IO EXCEPTION ========== "+e.getMessage());
                    }
                    System.out.println("================ NULL MODEL =================== "+responseModel.getMessage());
                    failureResponseMutableData.setValue(responseModel.getMessage());
                }

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                System.out.println("================ ERROR =================== " + t.getMessage());
                failureResponseMutableData.setValue("Error");
            }
        });
    }
}
