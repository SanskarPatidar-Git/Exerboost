package com.arbutus.exerboost.activity.main.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.arbutus.exerboost.activity.MyBaseApplication;
import com.arbutus.exerboost.activity.auth.login.model.response.Data;
import com.arbutus.exerboost.repository.local.LocalController;
import com.arbutus.exerboost.repository.local.LocalSets;
import com.arbutus.exerboost.repository.remote.ApiController;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityRepository {

    private MutableLiveData<String> failureResponseMutableData = new MutableLiveData<>();
    private MutableLiveData<String> successResponseMutableData = new MutableLiveData<>();

    public LiveData<String> getFailureResponseMutableData() {
        return failureResponseMutableData;
    }

    public LiveData<String> getSuccessResponseMutableData() {
        return successResponseMutableData;
    }

    public void logOutUser(){

        String authToken = LocalSets.getAuthToken(LocalController.getInstance(MyBaseApplication.getContext()).getPreferences());
        System.out.println("=========== Log out auth token =================== "+authToken);

        Call<ResponseBody> call = ApiController.getInstance().getApiSets().logOutUser(authToken);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if(response.isSuccessful()){

                    try {
                        JSONObject object = new JSONObject(response.body().string());
                        if(object.getBoolean("status")){
                            successResponseMutableData.setValue(object.getString("message"));
                        } else {
                            failureResponseMutableData.setValue(object.getString("message"));
                        }
                    } catch (Exception e) {
                        System.out.println("--------------- Exception ------------------"+e.getMessage());
                        failureResponseMutableData.setValue("Failed");
                    }
                } else {
                    System.out.println("=========== error ========== "+response.message());
                    failureResponseMutableData.setValue("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("================ ERROR =================== " + t.getMessage());
                failureResponseMutableData.setValue("Error");
            }
        });

    }
}
