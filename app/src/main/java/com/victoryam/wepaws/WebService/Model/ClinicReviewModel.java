package com.victoryam.wepaws.WebService.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ClinicReviewModel {
    int clinic_id;
    String login;
    String review;
    String status;

    public String getReview(){
        return review;
    }

    public ClinicReviewModel(JSONObject jsonObject){
        try {
            clinic_id = jsonObject.getInt("clinic_id");
            review = jsonObject.getString("review");
            status = jsonObject.getString("status");
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}

