package com.victoryam.wepaws.WebService.Task;

import com.victoryam.wepaws.R;
import com.victoryam.wepaws.WebService.Model.ClinicMasterModel;
import com.victoryam.wepaws.WebService.Model.MasterModel;
import com.victoryam.wepaws.WebService.WebServiceProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class GetMasterTask implements Callable<List<MasterModel>> {

    String url, jsonContent;
    int category;

    public static final int CLINIC = 0;
    public static final int HOTEL = 1;
    public static final int SHOP = 2;

    public GetMasterTask(String pUurl, String pJsonContent, int pCategory) {
        url = pUurl;
        jsonContent = pJsonContent;
        category = pCategory;
    }

    public List<MasterModel> call() throws JSONException {
        String data = new WebServiceProvider().callPostWebService(url, jsonContent);
        JSONArray arr = new JSONArray(data);
        List<MasterModel> masterList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            MasterModel model = new MasterModel();
            switch (category) {
                case CLINIC:
                    break;
                case HOTEL:
                    model.SetHotelMasterModel(arr.getJSONObject(i));
                    break;
                case SHOP:
                    model.SetShopMasterModel(arr.getJSONObject(i));
                    break;
            }
            masterList.add(model);
        }
        return masterList;
    }
}
