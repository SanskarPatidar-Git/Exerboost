package com.arbutus.exerboost.activity.main.fragments.order.models.request;

import com.google.gson.annotations.SerializedName;

public class YourOrderRequestModel {

    @SerializedName("status")
    private String status;

    public YourOrderRequestModel(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
