package com.macbitsgoa.pawpals;

/**
 * Created by priyesh srivastava on 3/12/2018.
 */

public class ListItem {
    private String dogName;
    private String dogID;
    private String lastFedTime;
    private String dogImage;


    public ListItem(String dogName, String dogID, String lastFedTime, String dogImage) {
        this.dogName = dogName;
        this.dogID = dogID;
        this.lastFedTime = lastFedTime;
        this.dogImage = dogImage;
    }

    public String getDogName() {
        return dogName;
    }

    public String getDogID() {
        return dogID;
    }

    public String getLastFedTime() {
        return lastFedTime;
    }

    public String getDogImage() {
        return dogImage;
    }
}
