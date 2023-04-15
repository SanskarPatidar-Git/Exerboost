package com.arbutus.exerboost.activity.auth.register.models.request;

import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("confirm_password")
    private String confirmPassword;

    @SerializedName("policyAccept")
    private boolean policyAccepted;

    public RegisterModel(String email, String password, String confirmPassword , boolean policyAccepted) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.policyAccepted = policyAccepted;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isPolicyAccepted() {
        return policyAccepted;
    }

    public void setPolicyAccepted(boolean policyAccepted) {
        this.policyAccepted = policyAccepted;
    }
}
