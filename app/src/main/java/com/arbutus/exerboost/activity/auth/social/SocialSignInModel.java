package com.arbutus.exerboost.activity.auth.social;

import com.google.gson.annotations.SerializedName;

public class SocialSignInModel {

    @SerializedName("socialId")
    private String socialId;

    @SerializedName("socialType")
    private String socialType;

    public SocialSignInModel(String socialId, String socialType) {
        this.socialId = socialId;
        this.socialType = socialType;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }
}
