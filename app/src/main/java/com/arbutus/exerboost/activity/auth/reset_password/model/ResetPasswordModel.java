package com.arbutus.exerboost.activity.auth.reset_password.model;

import com.google.gson.annotations.SerializedName;

public class ResetPasswordModel {

    @SerializedName("otp")
    private String otp;

    public ResetPasswordModel(String otp) {
        this.otp = otp;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
