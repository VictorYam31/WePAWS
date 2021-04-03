package com.victoryam.wepaws.WebService.Model;

import android.text.PrecomputedText;

import com.victoryam.wepaws.Utils.IReview;

import org.json.JSONException;
import org.json.JSONObject;

public class ClinicReviewModel implements IReview {
    int clinic_id;
    String login;
    int rate;
    String review;
    String created_datetime;
    String updated_datetime;

    public int getClinicID() {
        return clinic_id;
    }

    public String getLoginID() {
        return login;
    }

    public int getRate() {
        return rate;
    }

    public String getReview() {
        return review;
    }

    public String getCreateDateTime() {
        return created_datetime;
    }

    public String getLastUpdateDateTime() {
        return updated_datetime;
    }

    public int getIDForReview() {
        return getClinicID();
    }

    public String getLoginIDForReview() {
        return getLoginID();
    }

    public int getRateForReview() {
        return getRate();
    }

    public String getReviewForReview() {
        return getReview();
    }

    public String gerCreateDateForReview() {
        return getCreateDateTime();
    }

    public String getLastUpdateDateForReview() {
        return getLastUpdateDateTime();
    }

    public ClinicReviewModel(JSONObject jsonObject) {
        try {
            clinic_id = jsonObject.getInt("clinic_id");
            login = jsonObject.getString("login");
            rate = jsonObject.getInt("rate");
            review = jsonObject.getString("review");
            created_datetime = jsonObject.getString("created_datetime");
            updated_datetime = jsonObject.getString("updated_datetime");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

