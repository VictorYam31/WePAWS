package com.wepaws.wepaws.WebService;

import com.wepaws.wepaws.WebService.Model.ClinicMasterModel;
import com.wepaws.wepaws.WebService.Model.ClinicReviewModel;
import com.wepaws.wepaws.WebService.Model.MasterModel;
import com.wepaws.wepaws.WebService.Model.NonQueryResultModel;
import com.wepaws.wepaws.WebService.Model.ReviewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WebServiceManager {

    private static final int CLINIC = 0;
    private static final int HOTEL = 1;
    private static final int SHOP = 2;
    private static final int WILD = 99;

    //parameter @clinic_name - "" or any text
    //parameter @district_id - 1 to 18, can be empty or single or multiply, ""  "2"  "2,6"
    //parameter @overnight - "" or "Y" or "N"
    public List<ClinicMasterModel> get_clinic_master(String clinic_name, String district_id, String overnight) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_master";
        String jsonContent = null;
        try {
            jsonContent = new JSONObject()
                    .put("clinic_name", clinic_name)
                    .put("district_id", district_id)
                    .put("overnight", overnight)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToListClinicMasterModel(get_webservice_result(url, jsonContent));
    }

    //parameter @clinic_id - From 1 to 38, e.g. "1", "6", "24"
    public List<ClinicReviewModel> get_clinic_review(String clinic_id) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/clinicws.asmx/get_clinic_review";
        String jsonContent = null;
        try {
            jsonContent = new JSONObject()
                    .put("clinic_id", clinic_id)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToListClinicReviewModel(get_webservice_result(url, jsonContent));
    }

    //parameter @clinic_id - From 1 to 38, e.g. "1", "6", "24"
    //parameter @login - "" only
    //parameter @review - "" or any text
    //parameter @rating - "" or "-1" or "0" or "1"
    public String add_clinic_review(String clinic_id, String login, String review, String rating) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/clinicws.asmx/add_clinic_review_rating";
        String jsonContent = null;
        try {
            jsonContent = new JSONObject()
                    .put("clinic_id", clinic_id)
                    .put("login", login)
                    .put("review", review)
                    .put("rate", rating)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return get_webservice_result(url, jsonContent);
    }

    //parameter @input - any text
    public List<MasterModel> wild_search(String input) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/generalws.asmx/wild_search";
        String jsonContent = null;
        try {
            jsonContent = new JSONObject()
                    .put("input", input)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToListMasterModel(get_webservice_result(url, jsonContent), WILD);
    }

    //parameter @hotel_name - "" or any text
    //parameter @district_id - 1 to 18, can be empty or single or multiply, ""  "2"  "2,6"
    public List<MasterModel> get_hotel_master(String hotel_name, String district_id) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/hotelws.asmx/get_hotel_master";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("hotel_name", hotel_name)
                    .put("district_id", district_id)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToListMasterModel(get_webservice_result(url, jsonContent), HOTEL);
    }

    //parameter @hotel_id - From 1 to 63, e.g. "1", "6", "24"
    public List<ReviewModel> get_hotel_review(String hotel_id) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/hotelws.asmx/get_hotel_review";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("hotel_id", hotel_id)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToListReviewModel(get_webservice_result(url, jsonContent), HOTEL);
    }

    //parameter @id - From 1 to 38, e.g. "1", "6", "24"
    //parameter @login - "" only
    //parameter @review - "" or any text
    //parameter @rate - "" or "-1" or "0" or "1"
    public NonQueryResultModel add_hotel_review(String id, String login, String review, String rate) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/hotelws.asmx/add_hotel_review";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("hotel_id", id)
                    .put("login", login)
                    .put("review", review)
                    .put("rate", rate)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToNonQueryModel(get_webservice_result(url, jsonContent));
    }

    //parameter @shop_name - "" or any text
    //parameter @district_id - 1 to 18, can be empty or single or multiply, ""  "2"  "2,6"
    public List<MasterModel> get_shop_master(String hotel_name, String district_id) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/shopws.asmx/get_shop_master";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("shop_name", hotel_name)
                    .put("district_id", district_id)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToListMasterModel(get_webservice_result(url, jsonContent), SHOP);
    }

    //parameter @shop_id - From 1 to 138, e.g. "1", "6", "24"
    public List<ReviewModel> get_shop_review(String shop_id) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/shopws.asmx/get_shop_review";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("shop_id", shop_id)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToListReviewModel(get_webservice_result(url, jsonContent), SHOP);
    }

    //parameter @id - From 1 to 38, e.g. "1", "6", "24"
    //parameter @login - "" only
    //parameter @review - "" or any text
    //parameter @rate - "" or "-1" or "0" or "1"
    public NonQueryResultModel add_shop_review(String id, String login, String review, String rate) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/shopws.asmx/add_shop_review";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("shop_id", id)
                    .put("login", login)
                    .put("review", review)
                    .put("rate", rate)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToNonQueryModel(get_webservice_result(url, jsonContent));
    }

    //parameter @login - text
    //parameter @password - text

    //info = 0 - SUCCESS
    //info = 1 - USER ALREADY EXISTS
    public NonQueryResultModel create_account(String login, String password) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/accountws.asmx/create_account";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("login", login)
                    .put("password", password)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToNonQueryModel(get_webservice_result(url, jsonContent));
    }

    public NonQueryResultModel create_account(String login, String email, String password) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/accountws.asmx/create_account_email";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("login", login)
                    .put("email", email)
                    .put("password", password)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToNonQueryModel(get_webservice_result(url, jsonContent));
    }

    //parameter @login - text
    //parameter @password - text

    //info = 0 - SUCCESS
    //info = 1 - WRONG PASSWORD
    //info = 2 - USER DOES NOT EXIST
    //info = 3 - UNKNOWN
    public NonQueryResultModel verify_account(String login, String password) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/accountws.asmx/verify_account";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("login", login)
                    .put("password", password)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToNonQueryModel(get_webservice_result(url, jsonContent));
    }

    public NonQueryResultModel send_email(String emailTo, String subject, String content) throws ExecutionException, InterruptedException {
        String url = "https://wepaws.azurewebsites.net/emailws.asmx/send_email";
        String jsonContent = "";
        try {
            jsonContent = new JSONObject()
                    .put("emailTo", emailTo)
                    .put("subject", subject)
                    .put("content", content)
                    .toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonToNonQueryModel(get_webservice_result(url, jsonContent));
    }

    private String get_webservice_result(String url, String jsonContent) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        WebServiceTask task = new WebServiceTask(url, jsonContent);
        Future<String> future = executor.submit(task);
        String result = future.get();
        executor.shutdown();
        return result;
    }

    private NonQueryResultModel jsonToNonQueryModel(String data) {
        JSONArray arr;
        NonQueryResultModel result = null;
        try {
            arr = new JSONArray(data);
            result = new NonQueryResultModel(arr.getJSONObject(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<ClinicMasterModel> jsonToListClinicMasterModel(String data) {
        JSONArray arr;
        List<ClinicMasterModel> clinicMasterList = new ArrayList<>();

        try {
            arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                clinicMasterList.add(new ClinicMasterModel(arr.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return clinicMasterList;
    }

    private List<ClinicReviewModel> jsonToListClinicReviewModel(String data) {
        JSONArray arr;
        List<ClinicReviewModel> clinicReviewList = new ArrayList<>();

        try {
            arr = new JSONArray(data);
            for (int i = 0; i < arr.length(); i++) {
                clinicReviewList.add(new ClinicReviewModel(arr.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return clinicReviewList;
    }

    private List<MasterModel> jsonToListMasterModel(String data, int category) {
        JSONArray arr;
        List<MasterModel> masterList = new ArrayList<>();

        try {
            arr = new JSONArray(data);
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
                    case WILD:
                        model.setWildMasterModel(arr.getJSONObject(i));
                        break;
                }
                masterList.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return masterList;
    }

    private List<ReviewModel> jsonToListReviewModel(String data, int category) {
        JSONArray arr;
        List<ReviewModel> reviewList = new ArrayList<>();

        try {
            arr = new JSONArray(data);
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
                reviewList.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return reviewList;
    }

}

