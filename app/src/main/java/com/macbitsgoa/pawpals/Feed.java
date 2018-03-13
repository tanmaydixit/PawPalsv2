package com.macbitsgoa.pawpals;

/**
 * Created by admin on 13-Mar-18.
 */

public class Feed {

    private String dogId;
    private String name;
    private String dateTime;
    private String food;

    public String getDogId(){
        return dogId;
    }

    public String getName(){
        return name;
    }

    public String getDateTime(){
        return dateTime;
    }

    public String getFood(){
        return food;
    }

    public void setDogId(String dogId){
        this.dogId = dogId;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDateTime(String dateTime){
        this.dateTime = dateTime;
    }

    public void setFood(String food){
        this.food = food;
    }



}



