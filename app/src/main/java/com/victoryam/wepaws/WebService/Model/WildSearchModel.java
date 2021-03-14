package com.victoryam.wepaws.WebService.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class WildSearchModel {
    private String category;
    private int clinic_id;
    private String clinic_name;
    private String clinic_name_cn;
    private String clinic_address;
    private String clinic_address_cn;
    private String phone;
    private String overnight;
    private String review;

    public WildSearchModel(JSONObject jsonObject) {

        try {
            category = jsonObject.getString("category");
            clinic_id = jsonObject.getInt("clinic_id");
            clinic_name = jsonObject.getString("clinic_name");
            clinic_name_cn = jsonObject.getString("clinic_name_cn");
            clinic_address = jsonObject.getString("clinic_address");
            clinic_address_cn = jsonObject.getString("clinic_address_cn");
            phone = jsonObject.getString("phone");
            overnight = jsonObject.getString("overnight");
            review = jsonObject.getString("review");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCategory() {
        return category;
    }

    public int getClinic_id() {
        return clinic_id;
    }

    public String getClinic_name() {
        return clinic_name;
    }

    public String getClinic_name_cn() {
        return clinic_name_cn;
    }

    public String getClinic_address() {
        return clinic_address;
    }

    public String getClinic_address_cn() {
        return clinic_address_cn;
    }

    public String getPhone() {
        return phone;
    }

    public String getOvernight() {
        return overnight;
    }

    public String getReview() {
        return review;
    }
}
