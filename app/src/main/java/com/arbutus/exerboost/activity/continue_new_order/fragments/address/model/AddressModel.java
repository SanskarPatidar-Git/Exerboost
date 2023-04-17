package com.arbutus.exerboost.activity.continue_new_order.fragments.address.model;

import com.google.gson.annotations.SerializedName;

public class AddressModel {

    @SerializedName("_id")
    private String addressId;

    @SerializedName("street1")
    private String street1Address;

    @SerializedName("street2")
    private String street2Address;

    @SerializedName("city")
    private String city;

    @SerializedName("state")
    private String state;

    @SerializedName("country")
    private String country;

    @SerializedName("postcode")
    private String postCode;

    @SerializedName("addresstype")
    private String addressType;


    public AddressModel(String addressId, String street1Address, String street2Address, String city, String state, String country, String postCode, String addressType) {
        this.addressId = addressId;
        this.street1Address = street1Address;
        this.street2Address = street2Address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
        this.addressType = addressType;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getStreet1Address() {
        return street1Address;
    }

    public void setStreet1Address(String street1Address) {
        this.street1Address = street1Address;
    }

    public String getStreet2Address() {
        return street2Address;
    }

    public void setStreet2Address(String street2Address) {
        this.street2Address = street2Address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

}
