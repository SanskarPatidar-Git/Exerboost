package com.arbutus.exerboost.activity.new_order;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class NewOrderModel implements Parcelable {

    @SerializedName("product")
    private String product;

    @SerializedName("deliveryAddress")
    private String deliveryAddress;

    @SerializedName("goal")
    private String goal;

    @SerializedName("duration")
    private String duration;

    @SerializedName("type")
    private String type;

    private String streetAddress;

    public NewOrderModel(String product, String deliveryAddress, String goal, String duration, String type) {
        this.product = product;
        this.deliveryAddress = deliveryAddress;
        this.goal = goal;
        this.duration = duration;
        this.type = type;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.product);
        dest.writeString(this.deliveryAddress);
        dest.writeString(this.goal);
        dest.writeString(this.duration);
        dest.writeString(this.type);
        dest.writeString(this.streetAddress);
    }

    public void readFromParcel(Parcel source) {
        this.product = source.readString();
        this.deliveryAddress = source.readString();
        this.goal = source.readString();
        this.duration = source.readString();
        this.type = source.readString();
        this.streetAddress = source.readString();
    }

    protected NewOrderModel(Parcel in) {
        this.product = in.readString();
        this.deliveryAddress = in.readString();
        this.goal = in.readString();
        this.duration = in.readString();
        this.type = in.readString();
        this.streetAddress = in.readString();
    }

    public static final Parcelable.Creator<NewOrderModel> CREATOR = new Parcelable.Creator<NewOrderModel>() {
        @Override
        public NewOrderModel createFromParcel(Parcel source) {
            return new NewOrderModel(source);
        }

        @Override
        public NewOrderModel[] newArray(int size) {
            return new NewOrderModel[size];
        }
    };

    @Override
    public String toString() {
        return "NewOrderModel{" +
                "product='" + product + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", goal='" + goal + '\'' +
                ", duration='" + duration + '\'' +
                ", type='" + type + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                '}';
    }
}
