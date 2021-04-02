package com.victoryam.wepaws.WebService.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ClinicReviewModel {
    int clinic_id;
    String login;
    int rate;
    String review;
    String created_datetime;
    String updated_datetime;

    public String getReview(){
        return review;
    }

    public ClinicReviewModel(JSONObject jsonObject){
        try {
            clinic_id = jsonObject.getInt("clinic_id");
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
}

