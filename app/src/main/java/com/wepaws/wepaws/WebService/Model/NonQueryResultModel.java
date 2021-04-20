package com.wepaws.wepaws.WebService.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class NonQueryResultModel {
    int isSuccess;
    int info;

    public NonQueryResultModel(){
    }

    //isSuccess = 0 Fail
    //isSuccess = 1 Success
    public NonQueryResultModel(JSONObject jsonObject){
        try {
            isSuccess = jsonObject.getInt("isSuccess");
            info = jsonObject.getInt("info");
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    public int getIsSuccess(){
        return isSuccess;
    }

    public int getInfo(){
        return info;
    }
}

