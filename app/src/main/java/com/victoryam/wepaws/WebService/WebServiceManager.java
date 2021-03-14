package com.victoryam.wepaws.WebService;

import com.victoryam.wepaws.WebService.Model.ClinicMasterModel;
import com.victoryam.wepaws.WebService.Model.ClinicReviewModel;
import com.victoryam.wepaws.WebService.Model.WildSearchModel;
import com.victoryam.wepaws.WebService.Task.AddClinicReviewRatingTask;
import com.victoryam.wepaws.WebService.Task.GetClinicMasterTask;
import com.victoryam.wepaws.WebService.Task.GetClinicReviewTask;
import com.victoryam.wepaws.WebService.Task.WildSearchTask;

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
    //
    //
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


}

