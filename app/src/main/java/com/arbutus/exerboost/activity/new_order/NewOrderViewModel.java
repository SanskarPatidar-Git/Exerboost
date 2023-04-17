package com.arbutus.exerboost.activity.new_order;

import androidx.lifecycle.ViewModel;

public class NewOrderViewModel extends ViewModel {

    private String goal;
    private String duration;

    private String category;

    private String productId;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
