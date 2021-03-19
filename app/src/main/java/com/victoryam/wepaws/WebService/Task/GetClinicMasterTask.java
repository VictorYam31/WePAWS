package com.victoryam.wepaws.WebService.Task;

import com.victoryam.wepaws.WebService.Model.ClinicMasterModel;
import com.victoryam.wepaws.WebService.WebServiceProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class GetClinicMasterTask implements Callable<List<ClinicMasterModel>> {

    String clinic_name, district, overnight;

    public GetClinicMasterTask(String pClinicName, String pDistrict, String pOvernight) {
        clinic_name = pClinicName;
        district = pDistrict;
        overnight = pOvernight;
    }

    public List<ClinicMasterModel> call() throws JSONException {
        String url = "https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_master";
        String jsonContent = new JSONObject()
                .put("clinic_name", clinic_name)
                .put("district_id", district)
                .put("overnight", overnight)
                .toString();
        String data = new WebServiceProvider().callPostWebService(url, jsonContent);
        JSONArray arr = new JSONArray(data);
        List<ClinicMasterModel> clinicMasterList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            clinicMasterList.add(new ClinicMasterModel(arr.getJSONObject(i)));
        }
        return clinicMasterList;
    }
}
