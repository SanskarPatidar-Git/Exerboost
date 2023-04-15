package com.arbutus.exerboost.activity.main.fragments.home;

import androidx.lifecycle.MutableLiveData;


import com.arbutus.exerboost.activity.BaseResponseModelList;
import com.arbutus.exerboost.activity.MyBaseApplication;
import com.arbutus.exerboost.activity.main.fragments.home.model.Data;
import com.arbutus.exerboost.repository.BaseResponseModel;
import com.arbutus.exerboost.repository.local.LocalController;
import com.arbutus.exerboost.repository.local.LocalSets;
import com.arbutus.exerboost.repository.remote.ApiController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentRepository {

    private MutableLiveData<List<Data>> successResponseMutableData = new MutableLiveData<>();

    private MutableLiveData<String> failureResponseMutableData = new MutableLiveData<>();

    public MutableLiveData<List<Data>> getSuccessResponseMutableData() {
        return successResponseMutableData;
    }

    public MutableLiveData<String> getFailureResponseMutableData() {
        return failureResponseMutableData;
    }

    public void getAllProductsFromServer(){

        String authToken = LocalSets.getAuthToken(LocalController.getInstance(MyBaseApplication.getContext()).getPreferences());
        System.out.println("=========== AUTH TOKEN ==========  "+authToken);
        Call<BaseResponseModelList<Data>> call = ApiController.getInstance().getApiSets().getAllProducts(authToken);

        call.enqueue(new Callback<BaseResponseModelList<Data>>() {
            @Override
            public void onResponse(Call<BaseResponseModelList<Data>> call, Response<BaseResponseModelList<Data>> response) {

                BaseResponseModelList responseModel = response.body();

                if(responseModel !=null){

                    if(response.isSuccessful() && responseModel.isStatus()){

                        successResponseMutableData.setValue(responseModel.getData());
                        System.out.println("SUCCESS ==================="+ responseModel.getData().size());
                    } else {

                        failureResponseMutableData.setValue("Failed : "+responseModel.getMessage());
                    }
                } else {

                    Gson gson = new GsonBuilder().create();

                    try {
                        responseModel = gson.fromJson(response.errorBody().string(), BaseResponseModelList.class);
                        System.out.println("================ NULL MODEL =================== " + responseModel.getMessage());
                        failureResponseMutableData.setValue(responseModel.getMessage());
                    } catch (Exception e) {
                        System.out.println("============= IO EXCEPTION ========== " + e.getMessage());
                        failureResponseMutableData.setValue("Failed");
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModelList<Data>> call, Throwable t) {
                System.out.println("========= Exception =========== "+t.getMessage());
                failureResponseMutableData.setValue("Something went wrong");
            }
        });
    }
}
