package com.arbutus.exerboost.activity.main.fragments.order.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arbutus.exerboost.activity.main.fragments.order.models.BaseYourOrderModel;
import com.arbutus.exerboost.activity.main.fragments.order.repository.YourOrderRepository;

import java.util.List;

public class YourOrderViewModel extends ViewModel {

    private YourOrderRepository repository = new YourOrderRepository();

    public void getCompleteOrder(){
        repository.getAllOrder();
    }

    public MutableLiveData<List<BaseYourOrderModel>> getYourCompletedOrderModelMutableLiveData() {
        return repository.getYourCompletedOrderModelMutableLiveData();
    }

    public MutableLiveData<List<BaseYourOrderModel>> getYourPendingOrderModelMutableLiveData() {
        return repository.getYourPendingOrderModelMutableLiveData();
    }

    public MutableLiveData<String> getFailureStringMutableLiveData() {
        return repository.getFailureStringMutableLiveData();
    }
}
