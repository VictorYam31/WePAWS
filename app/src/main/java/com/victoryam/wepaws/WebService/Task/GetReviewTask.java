package com.victoryam.wepaws.WebService.Task;

import com.victoryam.wepaws.WebService.Model.ReviewModel;
import com.victoryam.wepaws.WebService.WebServiceProvider;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class GetReviewTask implements Callable<List<ReviewModel>> {

    String url, jsonContent;
    int category;

    public static final int CLINIC = 0;
    public static final int HOTEL = 1;
    public static final int SHOP = 2;

    public GetReviewTask(String pUurl, String pJsonContent, int pCategory) {
        url = pUurl;
        jsonContent = pJsonContent;
        category = pCategory;
    }

    public List<ReviewModel> call() throws JSONException {
        String data = new WebServiceProvider().callPostWebService(url, jsonContent);
        JSONArray arr = new JSONArray(data);
        List<ReviewModel> masterList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            ReviewModel model = new ReviewModel();
            switch (category) {
                case CLINIC:
                    break;
                case HOTEL:
                    model.setHotelReviewModel(arr.getJSONObject(i));
                    break;
                case SHOP:
                    model.setShopReviewModel(arr.getJSONObject(i));
                    break;
            }
            masterList.add(model);
        }
        return masterList;
    }
}
