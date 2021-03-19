package com.victoryam.wepaws.WebService.Task;

import com.victoryam.wepaws.WebService.WebServiceProvider;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

public class AddClinicReviewRatingTask implements Callable<String> {

    String clinic_id, login, review, rating;

    public AddClinicReviewRatingTask(String pClinicID, String pLogin, String pReview, String pRating) {
        clinic_id = pClinicID;
        login = pLogin;
        review = pReview;
        rating = pRating;
    }

    public String call() throws JSONException {
        String url = "https://wepaws.azurewebsites.net/clinicws.asmx/add_clinic_review_rating";
        String jsonBody = new JSONObject()
                .put("clinic_id", clinic_id)
                .put("login", login)
                .put("review", review)
                .put("rate", rating)
                .toString();
        return new WebServiceProvider().callPostWebService(url, jsonBody);
    }
}
