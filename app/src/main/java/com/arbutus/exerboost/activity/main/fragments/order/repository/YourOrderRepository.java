package com.arbutus.exerboost.activity.main.fragments.order.repository;


import androidx.lifecycle.MutableLiveData;

import com.arbutus.exerboost.activity.MyBaseApplication;
import com.arbutus.exerboost.activity.main.fragments.order.models.BaseYourOrderModel;
import com.arbutus.exerboost.repository.BaseResponseModelList;
import com.arbutus.exerboost.repository.local.LocalController;
import com.arbutus.exerboost.repository.local.LocalSets;
import com.arbutus.exerboost.repository.remote.ApiController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YourOrderRepository {

    private MutableLiveData<List<BaseYourOrderModel>> completedOrderModelMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<List<BaseYourOrderModel>> pendingOrderModelMutableLiveData = new MutableLiveData<>();

    private MutableLiveData<String> failureStringMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<BaseYourOrderModel>> getYourCompletedOrderModelMutableLiveData() {
        return completedOrderModelMutableLiveData;
    }

    public MutableLiveData<List<BaseYourOrderModel>> getYourPendingOrderModelMutableLiveData() {
        return pendingOrderModelMutableLiveData;
    }

    public MutableLiveData<String> getFailureStringMutableLiveData() {
        return failureStringMutableLiveData;
    }

    public void getAllOrder(){

        String authToken = LocalSets.getAuthToken(LocalController.getInstance(MyBaseApplication.getContext()).getPreferences());

        Call<BaseResponseModelList<BaseYourOrderModel>> call = ApiController.getInstance().getApiSets().getAllOrder(authToken);

        call.enqueue(new Callback<BaseResponseModelList<BaseYourOrderModel>>() {
            @Override
            public void onResponse(Call<BaseResponseModelList<BaseYourOrderModel>> call, Response<BaseResponseModelList<BaseYourOrderModel>> response) {

                BaseResponseModelList baseResponseModelList = response.body();

                if(baseResponseModelList!=null){

                    if(response.isSuccessful() && baseResponseModelList.isStatus()){
                        //filter data
                        filterOrderData(baseResponseModelList.getData());

                    } else {
                        failureStringMutableLiveData.setValue(baseResponseModelList.getMessage());
                    }
                } else {
                    //error body
                    Gson gson = new GsonBuilder().create();

                    try {

                        BaseResponseModelList responseModelObject = gson.fromJson(response.errorBody().string(),BaseResponseModelList.class);
                        failureStringMutableLiveData.setValue(responseModelObject.getMessage());

                    } catch (Exception e) {
                        System.out.println("------------- ERROR ------------ "+e.getMessage());
                        failureStringMutableLiveData.setValue("Failed");
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponseModelList<BaseYourOrderModel>> call, Throwable t) {
                failureStringMutableLiveData.setValue("Something went wrong");
                System.out.println("------------ EXCEPTION ------------------- "+t.getMessage());
            }
        });
    }

    private void filterOrderData(List<BaseYourOrderModel> modelList) {

        System.out.println("=========== YOUR ORDER LIST SIZE ++++============== "+modelList.size());

        List<BaseYourOrderModel> completedOrderModelList = new ArrayList<>();
        List<BaseYourOrderModel> pendingOrderModelList = new ArrayList<>();

        for(BaseYourOrderModel model : modelList){

            if(model.getStatus().equals("pending")){
                pendingOrderModelList.add(model);
            } else if(model.getStatus().equals("completed")){
                completedOrderModelList.add(model);
            }
        }

        completedOrderModelMutableLiveData.setValue(completedOrderModelList);
        pendingOrderModelMutableLiveData.setValue(pendingOrderModelList);

    }
}
