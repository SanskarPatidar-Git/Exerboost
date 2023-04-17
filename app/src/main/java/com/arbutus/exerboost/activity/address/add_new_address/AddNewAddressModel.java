package com.arbutus.exerboost.activity.address.add_new_address;

import com.google.gson.annotations.SerializedName;

public class AddNewAddressModel {

    @SerializedName("street1")
    private String streetOne;

    @SerializedName("street2")
    private String streetTwo ;

    @SerializedName("city")
    private  String city;

    @SerializedName("state")
    private  String state;

    @SerializedName("country")
    private String country;

    @SerializedName("postcode")
    private String postCode ;

    @SerializedName("addresstype")
    private String addressType;

    private boolean isDefaultAddress;

    public AddNewAddressModel(String streetOne, String streetTwo, String city, String state, String country, String postCode , String addressType , boolean isDefaultAddress) {
        this.streetOne = streetOne;
        this.streetTwo = streetTwo;
        this.city = city;
        this.state = state;
        this.country = country;
        this.postCode = postCode;
        this.addressType = addressType;
        this.isDefaultAddress = isDefaultAddress;
    }

    public boolean isDefaultAddress() {
        return isDefaultAddress;
    }

    public void setDefaultAddress(boolean defaultAddress) {
        isDefaultAddress = defaultAddress;
    }

    public String getStreetOne() {
        return streetOne;
    }

    public void setStreetOne(String streetOne) {
        this.streetOne = streetOne;
    }

    public String getStreetTwo() {
        return streetTwo;
    }

    public void setStreetTwo(String streetTwo) {
        this.streetTwo = streetTwo;
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
