package com.victoryam.wepaws.WebService;

import com.victoryam.wepaws.WebService.Model.ClinicMasterModel;
import com.victoryam.wepaws.WebService.Model.ClinicReviewModel;
import com.victoryam.wepaws.WebService.Model.MasterModel;
import com.victoryam.wepaws.WebService.Model.NonQueryResultModel;
import com.victoryam.wepaws.WebService.Model.WildSearchModel;
import com.victoryam.wepaws.WebService.Task.AddClinicReviewRatingTask;
import com.victoryam.wepaws.WebService.Task.CreateAccountTask;
import com.victoryam.wepaws.WebService.Task.GetClinicMasterTask;
import com.victoryam.wepaws.WebService.Task.GetClinicReviewTask;
import com.victoryam.wepaws.WebService.Task.GetMasterTask;
import com.victoryam.wepaws.WebService.Task.VerifyAccountTask;
import com.victoryam.wepaws.WebService.Task.WildSearchTask;
import com.victoryam.wepaws.WebService.Test.SendEmailTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class WebServiceManager {

    //parameter @clinic_name - "" or any text
    //parameter @district_id - 1 to 18, can be empty or single or multiply, ""  "2"  "2,6"
    //parameter @overnight - "" or "Y" or "N"
    public List<ClinicMasterModel> get_clinic_master(String clinic_name, String district_id, String overnight) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        GetClinicMasterTask task = new GetClinicMasterTask(clinic_name, district_id, overnight);
        Future<List<ClinicMasterModel>> future = executor.submit(task);
        List<ClinicMasterModel> clinicMasterList = future.get();
        executor.shutdown();

        return clinicMasterList;
    }

    //parameter @clinic_id - From 1 to 38, e.g. "1", "6", "24"
    public List<ClinicReviewModel> get_clinic_review(String clinic_id) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        GetClinicReviewTask task = new GetClinicReviewTask(clinic_id);
        Future<List<ClinicReviewModel>> future = executor.submit(task);
        List<ClinicReviewModel> clinicReviewList = future.get();
        executor.shutdown();

        return clinicReviewList;
    }

    //parameter @clinic_id - From 1 to 38, e.g. "1", "6", "24"
    //parameter @login - "" only
    //parameter @review - "" or any text
    //parameter @rating - "" or "-1" or "0" or "1"
    public String add_clinic_review(String clinic_id, String login, String review, String rating) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        AddClinicReviewRatingTask task = new AddClinicReviewRatingTask(clinic_id, login, review, rating);
        Future<String> future = executor.submit(task);
        String rowsAffected = future.get();
        executor.shutdown();

        return rowsAffected;
    }

    //parameter @input - any text
    public List<WildSearchModel> wild_search(String input) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        WildSearchTask task = new WildSearchTask(input);
        Future<List<WildSearchModel>> future = executor.submit(task);
        List<WildSearchModel> wildSearchList = future.get();
        executor.shutdown();

        return wildSearchList;
    }

    //parameter @login - text
    //parameter @password - text

    //info = 0 - SUCCESS
    //info = 1 - USER ALREADY EXISTS
    public NonQueryResultModel create_account(String login, String password) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        CreateAccountTask task = new CreateAccountTask(login, password);
        Future<NonQueryResultModel> future = executor.submit(task);
        NonQueryResultModel createAccountResult = future.get();
        executor.shutdown();

        return createAccountResult;
    }

    //parameter @login - text
    //parameter @password - text

    //info = 0 - SUCCESS
    //info = 1 - WRONG PASSWORD
    //info = 2 - USER DOES NOT EXIST
    //info = 3 - UNKNOWN
    public NonQueryResultModel verify_account(String login, String password) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        VerifyAccountTask task = new VerifyAccountTask(login, password);
        Future<NonQueryResultModel> future = executor.submit(task);
        NonQueryResultModel createAccountResult = future.get();
        executor.shutdown();

        return createAccountResult;
    }

    public NonQueryResultModel send_email(String emailTo, String subject, String content) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        SendEmailTask task = new SendEmailTask(emailTo, subject, content);
        Future<NonQueryResultModel> future = executor.submit(task);
        NonQueryResultModel createAccountResult = future.get();
        executor.shutdown();

        return createAccountResult;
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

        ExecutorService executor = Executors.newCachedThreadPool();
        GetMasterTask task = new GetMasterTask(url, jsonContent, GetMasterTask.HOTEL);
        Future<List<MasterModel>> future = executor.submit(task);
        List<MasterModel> hotelMasterList = future.get();
        executor.shutdown();

        return hotelMasterList;
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

        ExecutorService executor = Executors.newCachedThreadPool();
        GetMasterTask task = new GetMasterTask(url, jsonContent, GetMasterTask.SHOP);
        Future<List<MasterModel>> future = executor.submit(task);
        List<MasterModel> hotelMasterList = future.get();
        executor.shutdown();

        return hotelMasterList;
    }
}

