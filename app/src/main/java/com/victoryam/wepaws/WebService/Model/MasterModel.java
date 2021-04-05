package com.victoryam.wepaws.WebService.Model;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

public class MasterModel {
    private int id = -1;
    private String name = "";
    private String name_cn = "";
    private String address = "";
    private String address_cn = "";
    private String desc = "";
    private int district_id = -1;
    private String phone = "";
    private String overnight = "";
    private int negative_count = 0;
    private int neutral_count = 0;
    private int positive_count = 0;
    private int review_count = 0;


    public void SetHotelMasterModel(@NotNull JSONObject jsonObject){
        try {
            id = jsonObject.getInt("hotel_id");
            name = jsonObject.getString("hotel_name");
            name_cn = jsonObject.getString("hotel_name_cn");
            address = jsonObject.getString("hotel_address");
            address_cn = jsonObject.getString("hotel_address_cn");
            desc = jsonObject.getString("hotel_desc");
            district_id = jsonObject.getInt("district_id");
            phone = jsonObject.getString("phone");
            overnight = jsonObject.getString("overnight");
            negative_count = jsonObject.getInt("negative_count");
            neutral_count = jsonObject.getInt("neutral_count");
            positive_count = jsonObject.getInt("positive_count");
            review_count = jsonObject.getInt("review_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void SetShopMasterModel(@NotNull JSONObject jsonObject){
        try {
            id = jsonObject.getInt("shop_id");
            name = jsonObject.getString("shop_name");
            name_cn = jsonObject.getString("shop_name_cn");
            address = jsonObject.getString("shop_address");
            address_cn = jsonObject.getString("shop_address_cn");
            desc = jsonObject.getString("shop_desc");
            district_id = jsonObject.getInt("district_id");
            phone = jsonObject.getString("phone");
            negative_count = jsonObject.getInt("negative_count");
            neutral_count = jsonObject.getInt("neutral_count");
            positive_count = jsonObject.getInt("positive_count");
            review_count = jsonObject.getInt("review_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getDesc() {
        return desc;
    }
}
