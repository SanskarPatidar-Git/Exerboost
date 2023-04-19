package com.arbutus.exerboost.activity.main.fragments.order.models.response;

import com.google.gson.annotations.SerializedName;

public class YourOrderRootModel {

    @SerializedName("_id")
    private String id;

    @SerializedName("product")
    private String product;

    @SerializedName("customer")
    private Customer customer;

    @SerializedName("status")
    private String status;

    @SerializedName("deliveryAddress")
    private DeliveryAddress deliveryAddress;

    @SerializedName("createdAt")
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
