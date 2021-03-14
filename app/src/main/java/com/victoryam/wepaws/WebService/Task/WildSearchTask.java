package com.victoryam.wepaws.WebService.Task;

import com.victoryam.wepaws.WebService.Model.WildSearchModel;
import com.victoryam.wepaws.WebService.WebServiceProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class WildSearchTask implements Callable<List<WildSearchModel>> {

    private String input;

    public WildSearchTask(String pInput) {
        input = pInput;
    }

    public List<WildSearchModel> call() throws JSONException {
        String url = "https://wepaws.azurewebsites.net/clinicws.asmx/wild_search";
        String jsonContent = new JSONObject()
                .put("input", input)
                .toString();
        String data = new WebServiceProvider().callPostWebService(url, jsonContent);
        JSONArray arr = new JSONArray(data);
        List<WildSearchModel> clinicMasterList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            clinicMasterList.add(new WildSearchModel(arr.getJSONObject(i)));
        }
        return clinicMasterList;
    }
}

