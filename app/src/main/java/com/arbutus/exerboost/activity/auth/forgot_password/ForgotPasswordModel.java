package com.arbutus.exerboost.activity.auth.forgot_password;

import com.google.gson.annotations.SerializedName;

public class ForgotPasswordModel {

    @SerializedName("email")
    private String email;

    public ForgotPasswordModel(String email) {
        this.email = email;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

}
