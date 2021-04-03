package com.victoryam.wepaws.WebService.Task;

import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.WebServiceProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

public class VerifyAccountTask implements Callable<NonQueryResultModel> {

    String login, password;

    public VerifyAccountTask(String pLogin, String pPassword) {
        login = pLogin;
        password = pPassword;
    }

    public NonQueryResultModel call() throws JSONException {
        String url = "https://wepaws.azurewebsites.net/accountws.asmx/verify_account";
        String jsonContent = new JSONObject()
                .put("login", login)
                .put("password", password)
                .toString();
        String data = new WebServiceProvider().callPostWebService(url, jsonContent);
        JSONArray arr = new JSONArray(data);

        return new NonQueryResultModel(arr.getJSONObject(0));
    }
}
