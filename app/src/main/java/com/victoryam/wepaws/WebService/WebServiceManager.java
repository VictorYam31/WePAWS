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

    public List<ClinicMasterModel> get_clinic_master(String clinic_name, String district, String overnight) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        GetClinicMasterTask task = new GetClinicMasterTask(clinic_name, district, overnight);
        Future<List<ClinicMasterModel>> future = executor.submit(task);
        List<ClinicMasterModel> clinicMasterList = future.get();
        executor.shutdown();

        return clinicMasterList;
    }

    public List<ClinicReviewModel> get_clinic_review(String clinic_id) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        GetClinicReviewTask task = new GetClinicReviewTask(clinic_id);
        Future<List<ClinicReviewModel>> future = executor.submit(task);
        List<ClinicReviewModel> clinicReviewList = future.get();
        executor.shutdown();

        return clinicReviewList;


    }

    public String add_clinic_review(String clinic_id, String login, String review, String rating) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();
        AddClinicReviewRatingTask task = new AddClinicReviewRatingTask(clinic_id, login, review, rating);
        Future<String> future = executor.submit(task);
        String rowsAffected = future.get();
        executor.shutdown();

        return rowsAffected;
    }

    public List<WildSearchModel> wild_search(String input) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();
        WildSearchTask task = new WildSearchTask(input);
        Future<List<WildSearchModel>> future = executor.submit(task);
        List<WildSearchModel> wildSearchList = future.get();
        executor.shutdown();

        return wildSearchList;
    }


}

