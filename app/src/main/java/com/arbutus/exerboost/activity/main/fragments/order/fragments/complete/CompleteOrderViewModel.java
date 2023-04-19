package com.arbutus.exerboost.activity.main.fragments.order.fragments.complete;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arbutus.exerboost.activity.main.fragments.order.models.response.YourOrderRootModel;
import com.arbutus.exerboost.activity.main.fragments.order.models.request.YourOrderRequestModel;


import java.util.List;

public class CompleteOrderViewModel extends ViewModel {

    private CompleteOrderRepository repository = new CompleteOrderRepository();

    public void getCompleteOrderFromRepository(YourOrderRequestModel model){
        repository.getCompletedOrder(model);
    }

    public MutableLiveData<List<YourOrderRootModel>> getYourCompletedOrderModelMutableLiveData() {
        return repository.getCompletedOrderModelMutableLiveData();
    }

    public MutableLiveData<String> getFailureStringMutableLiveData() {
        return repository.getFailureStringMutableLiveData();
    }
}
