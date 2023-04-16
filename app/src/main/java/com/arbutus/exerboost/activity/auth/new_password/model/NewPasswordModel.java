package com.arbutus.exerboost.activity.auth.new_password.model;

import com.google.gson.annotations.SerializedName;

public class NewPasswordModel {

    @SerializedName("password")
    private String password;

    public NewPasswordModel(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
