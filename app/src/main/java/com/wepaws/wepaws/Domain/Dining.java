package com.wepaws.wepaws.Domain;

import android.media.Rating;

import java.util.List;

public class Dining  {
    private int dining_id;
    private String dining_name;
    private String dining_name_cn;
    private String dining_address;
    private String dining_address_cn;
    private String district;
    private String description;
    private Boolean overnight;
    private int phone;
    private String status;
    private List<Species> speciesList;
    private List<Rating> ratingList;
    private List<Review> reviewList;

    public int getDiningId() {
        return dining_id;
    }

    public void setDiningId(int dining_id) {
        this.dining_id = dining_id;
    }

    public String getDiningName() {
        return dining_name;
    }

    public void setDiningName(String dining_name) {
        this.dining_name = dining_name;
    }

    public String getDiningNameCN() {
        return dining_name_cn;
    }

    public void setDiningNameCN(String dining_name_cn) {
        this.dining_name_cn = dining_name_cn;
    }

    public String getDiningAddress() {
        return dining_address;
    }

    public void setDiningAddress(String dining_address) {
        this.dining_address = dining_address;
    }

    public String getDiningAddressCN() {
        return dining_address_cn;
    }

    public void setDiningAddressCN(String dining_address_cn) {
        this.dining_address_cn = dining_address_cn;
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

//    @Override
//    public String getNameForResult() {
//        return getDiningName();
//    }
//
//    @Override
//    public String getAddressForResult() {
//        return getDiningAddress();
//    }
//
//    @Override
//    public String getSpeciesForResult() {
//        return getSpeciesList().toString();
//    }
//
//    @Override
//    public String getDescriptionForResult() {
//        return getDescription();
//    }
//
//    @Override
//    public String getRatingForResult() {
//        return getRatingList().toString();
//    }
}