package com.victoryam.wepaws.WebService.Test;

import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.WebServiceProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.Callable;

public class SendEmailTask implements Callable<NonQueryResultModel> {

    String emailTo, subject, content;

    public SendEmailTask(String pEmailTo, String pSubject, String pContent) {
        emailTo = pEmailTo;
        subject = pSubject;
        content = pContent;
    }

    public NonQueryResultModel call() throws JSONException {
        String url = "https://wepaws.azurewebsites.net/emailws.asmx/send_email";
        String jsonContent = new JSONObject()
                .put("emailTo", emailTo)
                .put("subject", subject)
                .put("content", content)
                .toString();
        String data = new WebServiceProvider().callPostWebService(url, jsonContent);
        System.out.println(data);
        JSONArray arr = new JSONArray(data);

        NonQueryResultModel sendEmailResult = new NonQueryResultModel(arr.getJSONObject(0));
        return sendEmailResult;
    }
}
