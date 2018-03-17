package com.macbitsgoa.pawpals;

/**
 * Created by priyesh srivastava on 3/16/2018.
 */

public class FeederInfoItem {
    private String feederName;
    private String feedingTime;
    private String foodItem;

    public FeederInfoItem(String feederName, String feedingTime, String foodItem) {
        this.feederName = feederName;
        this.feedingTime = feedingTime;
        this.foodItem = foodItem;
    }

    public String getFeederName() {
        return feederName;
    }

    public String getFeedingTime() {
        return feedingTime;
    }

    public String getFoodItem() {
        return foodItem;
    }
}
