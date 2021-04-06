package com.victoryam.wepaws.WebService;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebServiceTask implements Callable<String> {
    String url, jsonContent;

    public WebServiceTask(String pUrl, String pJsonContent) {
        url = pUrl;
        jsonContent = pJsonContent;
    }

    public String call() {
        return callPostWebService(url, jsonContent);
    }

    public String callPostWebService(String url, String jsonBody) {
        String data = null;

        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonBody);
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                data = response.body().string();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
