package com.arbutus.exerboost.activity.main.fragments.order.fragments.pending;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arbutus.exerboost.activity.main.fragments.order.models.response.YourOrderRootModel;
import com.arbutus.exerboost.activity.main.fragments.order.models.request.YourOrderRequestModel;

import java.util.List;

public class PendingOrderViewModel extends ViewModel {

    private PendingOrderRepository repository = new PendingOrderRepository();

    public void getCompleteOrderFromRepository(YourOrderRequestModel model){
        repository.getPendingOrder(model);
    }

    public MutableLiveData<List<YourOrderRootModel>> getYourCompletedOrderModelMutableLiveData() {
        return repository.getPendingOrderModelMutableLiveData();
    }

    public MutableLiveData<String> getFailureStringMutableLiveData() {
        return repository.getFailureStringMutableLiveData();
    }
}
