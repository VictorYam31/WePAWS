package com.victoryam.wepaws.WebService.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class ClinicMasterModel {
    int clinic_id;
    String clinic_name;
    String clinic_name_cn;
    String clinic_address;
    String clinic_address_cn;
    int district;
    String phone;
    String overnight;
    int negative_count;
    int neutral_count;
    int positive_count;
    int review_count;

    public ClinicMasterModel(JSONObject jsonObject){
        try {
            clinic_id = jsonObject.getInt("clinic_id");
            clinic_name = jsonObject.getString("clinic_name");
            clinic_name_cn = jsonObject.getString("clinic_name_cn");
            clinic_address = jsonObject.getString("clinic_address");
            clinic_address_cn = jsonObject.getString("clinic_address_cn");
            district = jsonObject.getInt("district");
            phone = jsonObject.getString("phone");
            overnight = jsonObject.getString("overnight");
            negative_count = jsonObject.getInt("negative_count");
            neutral_count = jsonObject.getInt("neutral_count");
            positive_count = jsonObject.getInt("positive_count");
            review_count = jsonObject.getInt("review_count");
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }
}
