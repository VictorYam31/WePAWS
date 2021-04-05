package com.victoryam.wepaws.WebService.Task;

import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.WebServiceProvider;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.Callable;

public class NonQueryTask implements Callable<NonQueryResultModel> {

    String url, jsonContent;


    public NonQueryTask(String pUrl, String pJsonContent) {
        url = pUrl;
        jsonContent = pJsonContent;
    }

    public NonQueryResultModel call() throws JSONException {
        String data = new WebServiceProvider().callPostWebService(url, jsonContent);
        JSONArray arr = new JSONArray(data);
        NonQueryResultModel createAccountResult = new NonQueryResultModel(arr.getJSONObject(0));
        return createAccountResult;
    }

}
