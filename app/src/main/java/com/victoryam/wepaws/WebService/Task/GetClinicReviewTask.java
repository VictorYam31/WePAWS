package com.victoryam.wepaws.WebService.Task;

import com.victoryam.wepaws.WebService.Model.ClinicReviewModel;
import com.victoryam.wepaws.WebService.WebServiceProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class GetClinicReviewTask implements Callable<List<ClinicReviewModel>> {

    String clinic_id;

    public GetClinicReviewTask(String pClinicID) {
        clinic_id = pClinicID;
    }

    public List<ClinicReviewModel> call() throws JSONException {
        String url = "https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_review";
        String jsonBody = new JSONObject()
                .put("clinic_id", clinic_id)
                .toString();

        String data = new WebServiceProvider().callPostWebService(url, jsonBody);
        JSONArray arr = new JSONArray(data);
        List<ClinicReviewModel> clinicReviewList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            clinicReviewList.add(new ClinicReviewModel(arr.getJSONObject(i)));
        }
        return clinicReviewList;
    }
}
