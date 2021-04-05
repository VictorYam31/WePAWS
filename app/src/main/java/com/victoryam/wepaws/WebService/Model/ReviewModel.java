package com.victoryam.wepaws.WebService.Model;


import org.json.JSONException;
import org.json.JSONObject;

public class ReviewModel {
    private int id = -1;
    private String login = "Guest";
    private int rate = -1;
    private String review = "";
    private String created_datetime = "";
    private String updated_datetime = "";

    public void setHotelReviewModel(JSONObject jsonObject){
        try {
            id = jsonObject.getInt("hotel_id");
            login = jsonObject.getString("login");
            rate = jsonObject.getInt("rate");
            review = jsonObject.getString("review");
            created_datetime = jsonObject.getString("created_datetime");
            updated_datetime = jsonObject.getString("updated_datetime");
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    public void setShopReviewModel(JSONObject jsonObject){
        try {
            id = jsonObject.getInt("shop_id");
            login = jsonObject.getString("login");
            rate = jsonObject.getInt("rate");
            review = jsonObject.getString("review");
            created_datetime = jsonObject.getString("created_datetime");
            updated_datetime = jsonObject.getString("updated_datetime");
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public int getRate() {
        return rate;
    }

    public String getReview() {
        return review;
    }

    public String getUpdated_datetime() {
        return updated_datetime;
    }
}
