package com.arbutus.exerboost.activity.auth.login;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.arbutus.exerboost.activity.auth.login.model.response.Data;
import com.arbutus.exerboost.activity.auth.login.model.request.LoginModel;
import com.arbutus.exerboost.activity.auth.login.model.response.LoginResponse;
import com.arbutus.exerboost.activity.auth.social.SocialSignInModel;
import com.arbutus.exerboost.repository.local.LocalController;
import com.arbutus.exerboost.repository.local.LocalSets;
import com.arbutus.exerboost.repository.remote.ApiController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    private MutableLiveData<String> failureResponseMutableData = new MutableLiveData<>();
    private MutableLiveData<Data> successResponseMutableData = new MutableLiveData<>();

    public LiveData<String> getFailureResponseMutableData() {
        return failureResponseMutableData;
    }

    public LiveData<Data> getSuccessResponseMutableData() {
        return successResponseMutableData;
    }

    public void loginUserToServer(LoginModel loginModel) {

        Call<LoginResponse> call = ApiController.getInstance().getApiSets().loginUser(loginModel);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse model = response.body();
                if (model != null) {

                    if (response.isSuccessful() && model.isStatus()) {
                        successResponseMutableData.setValue(model.getData());
                    } else {
                        failureResponseMutableData.setValue(model.getMessage());
                    }
                }
                else {
                    Gson gson = new GsonBuilder().create();
                    LoginResponse responseModel = null;
                    try {
                        responseModel = gson.fromJson(response.errorBody().string(), LoginResponse.class);
                    } catch (IOException e) {
                        System.out.println("============= IO EXCEPTION ========== "+e.getMessage());
                    }
                    System.out.println("================ NULL MODEL =================== "+responseModel.getMessage());
                    failureResponseMutableData.setValue(responseModel.getMessage());
                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("================ ERROR =================== " + t.getMessage());
                failureResponseMutableData.setValue("Error");
            }
        });
    }

    public void loginUserWithGoogle(SocialSignInModel model){

        Call<LoginResponse> call = ApiController.getInstance().getApiSets().loginUserWithSocial(model);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                LoginResponse model = response.body();
                if (model != null) {

                    if (response.isSuccessful() && model.isStatus()) {
                        successResponseMutableData.setValue(model.getData());
                    } else {
                        failureResponseMutableData.setValue(model.getMessage());
                    }
                }
                else {
                    Gson gson = new GsonBuilder().create();
                    LoginResponse responseModel = null;
                    try {
                        responseModel = gson.fromJson(response.errorBody().string(), LoginResponse.class);
                    } catch (IOException e) {
                        System.out.println("============= IO EXCEPTION ========== "+e.getMessage());
                    }
                    System.out.println("================ NULL MODEL =================== "+responseModel.getMessage());
                    failureResponseMutableData.setValue(responseModel.getMessage());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("================ ERROR =================== " + t.getMessage());
                failureResponseMutableData.setValue("Error");
            }
        });
    }

    public void setUserDataToLocal(Context context) {

        try {
            System.out.println("========== TOKEN ============= " + successResponseMutableData.getValue().getToken());
            LocalSets.setUserData(LocalController.getInstance(context).getPreferences(), successResponseMutableData.getValue().getUser(), successResponseMutableData.getValue().getToken());
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
    }
}
