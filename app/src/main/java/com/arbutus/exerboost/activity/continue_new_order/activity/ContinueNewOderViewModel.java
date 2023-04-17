package com.arbutus.exerboost.activity.continue_new_order.activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.arbutus.exerboost.activity.new_order.NewOrderModel;

public class ContinueNewOderViewModel extends ViewModel {

    private String addressId ;

    private String cardId;

    private ContinueNewOrderRepository repository = new ContinueNewOrderRepository();

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void createOrder(NewOrderModel model){
        repository.createOrderToServer(model);
    }

    public LiveData<String> getSuccess(){
        return repository.getSuccessResponseMutableData();
    }

    public LiveData<String> getFailure(){
        return repository.getFailureResponseMutableData();
    }
}

