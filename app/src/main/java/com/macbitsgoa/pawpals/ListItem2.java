package com.macbitsgoa.pawpals;

/**
 * Created by priyesh srivastava on 3/15/2018.
 */

public class ListItem2 {
    private String feederName,foodItem,timeOfFeeding;

    public ListItem2(String feederName, String foodItem, String timeOfFeeding) {
        this.feederName = feederName;
        this.foodItem = foodItem;
        this.timeOfFeeding = timeOfFeeding;
    }

    public String getFeederName() {
        return feederName;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public String getTimeOfFeeding() {
        return timeOfFeeding;
    }
}
