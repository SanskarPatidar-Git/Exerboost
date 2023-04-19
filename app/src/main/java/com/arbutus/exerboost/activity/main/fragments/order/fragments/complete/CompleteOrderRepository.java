package com.arbutus.exerboost.activity.main.fragments.order.fragments.complete;

import androidx.lifecycle.MutableLiveData;

import com.arbutus.exerboost.activity.MyBaseApplication;
import com.arbutus.exerboost.activity.main.fragments.order.models.response.YourOrderRootModel;
import com.arbutus.exerboost.activity.main.fragments.order.models.request.YourOrderRequestModel;
import com.arbutus.exerboost.repository.BaseResponseModelList;
import com.arbutus.exerboost.repository.local.LocalController;
import com.arbutus.exerboost.repository.local.LocalSets;
import com.arbutus.exerboost.repository.remote.ApiController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompleteOrderRepository {

    private MutableLiveData<List<YourOrderRootModel>> completedOrderModelMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<String> failureStringMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<YourOrderRootModel>> getCompletedOrderModelMutableLiveData() {
        return completedOrderModelMutableLiveData;
    }

    public MutableLiveData<String> getFailureStringMutableLiveData() {
        return failureStringMutableLiveData;
    }

    public void getCompletedOrder(YourOrderRequestModel model){

        String authToken = LocalSets.getAuthToken(LocalController.getInstance(MyBaseApplication.getContext()).getPreferences());

        Call<BaseResponseModelList<YourOrderRootModel>> call = ApiController.getInstance().getApiSets().getAllOrder(model,authToken);
        call.enqueue(new Callback<BaseResponseModelList<YourOrderRootModel>>() {
            @Override
            public void onResponse(Call<BaseResponseModelList<YourOrderRootModel>> call, Response<BaseResponseModelList<YourOrderRootModel>> response) {

                BaseResponseModelList<YourOrderRootModel> baseResponseModelList = response.body();

                if(baseResponseModelList!=null){

                    if(response.isSuccessful() && baseResponseModelList.isStatus()){
                        completedOrderModelMutableLiveData.setValue(baseResponseModelList.getData());
                    } else {
                        failureStringMutableLiveData.setValue(baseResponseModelList.getMessage());
                    }
                } else {
                    //error body
                    Gson gson = new GsonBuilder().create();

                    try {

                        baseResponseModelList = gson.fromJson(response.errorBody().string(),BaseResponseModelList.class);
                        failureStringMutableLiveData.setValue(baseResponseModelList.getMessage());

                    } catch (Exception e) {
                        System.out.println("------------- ERROR ------------ "+e.getMessage());
                        failureStringMutableLiveData.setValue("Failed");
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModelList<YourOrderRootModel>> call, Throwable t) {
                failureStringMutableLiveData.setValue("Something went wrong");
                System.out.println("------------ EXCEPTION ------------------- "+t.getMessage());
            }
        });
    }
}
