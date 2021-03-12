package com.victoryam.wepaws.Domain;

import android.media.Rating;

import com.victoryam.wepaws.Utils.IResult;

import java.util.List;

public class Clinic implements IResult {
    private int clinic_id;
    private String clinic_name;
    private String clinic_name_cn;
    private String clinic_address;
    private String clinic_address_cn;
    private String district;
    private String description;
    private Boolean overnight;
    private int phone;
    private String status;
    private List<VetMaster> vetMasterList;
    private List<Species> speciesList;
    private List<Rating> ratingList;
    private List<Review> reviewList;

    public int getClinicId() {
        return clinic_id;
    }

    public void setClinicId(int clinic_id) {
        this.clinic_id = clinic_id;
    }

    public String getClinicName() {
        return clinic_name;
    }

    public void setClinicName(String clinic_name) {
        this.clinic_name = clinic_name;
    }

    public String getClinicNameCN() {
        return clinic_name_cn;
    }

    public void setClinicNameCN(String clinic_name_cn) {
        this.clinic_name_cn = clinic_name_cn;
    }

    public String getClinicAddress() {
        return clinic_address;
    }

    public void setClinicAddress(String clinic_address) {
        this.clinic_address = clinic_address;
    }

    public String getClinicAddressCN() {
        return clinic_address_cn;
    }

    public void setClinicAddressCN(String clinic_address_cn) {
        this.clinic_address_cn = clinic_address_cn;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOvernight() {
        return overnight;
    }

    public void setOvernight(Boolean overnight) {
        this.overnight = overnight;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Species> getSpeciesList() {
        return speciesList;
    }

    public void setSpeciesList(List<Species> speciesList) {
        this.speciesList = speciesList;
    }

    public List<VetMaster> getVetMasterList() {
        return vetMasterList;
    }

    public void setVetMasterList(List<VetMaster> vetMasterList) {
        this.vetMasterList = vetMasterList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    @Override
    public String getNameForResult() {
        return getClinicName();
    }

    @Override
    public String getAddressForResult() {
        return getClinicAddress();
    }

    @Override
    public String getSpeciesForResult() {
        return getSpeciesList().toString();
    }

    @Override
    public String getDescriptionForResult() {
        return getDescription();
    }

    @Override
    public String getRatingForResult() {
        return getRatingList().toString();
    }
}

