package com.arbutus.exerboost.activity.auth.login.model.request;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
