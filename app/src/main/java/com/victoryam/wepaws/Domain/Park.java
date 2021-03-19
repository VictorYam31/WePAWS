package com.victoryam.wepaws.Domain;

import android.media.Rating;

import com.victoryam.wepaws.Utils.IResult;

import java.util.List;

public class Park  {
    private int park_id;
    private String park_name;
    private String park_name_cn;
    private String park_address;
    private String park_address_cn;
    private String district;
    private String description;
    private Boolean overnight;
    private int phone;
    private String status;
    private List<Species> speciesList;
    private List<Rating> ratingList;
    private List<Review> reviewList;

    public int getParkId() {
        return park_id;
    }

    public void setParkId(int park_id) {
        this.park_id = park_id;
    }

    public String getParkName() {
        return park_name;
    }

    public void setParkName(String park_name) {
        this.park_name = park_name;
    }

    public String getParkNameCN() {
        return park_name_cn;
    }

    public void setParkNameCN(String park_name_cn) {
        this.park_name_cn = park_name_cn;
    }

    public String getParkAddress() {
        return park_address;
    }

    public void setParkAddress(String park_address) {
        this.park_address = park_address;
    }

    public String getParkAddressCN() {
        return park_address_cn;
    }

    public void setParkAddressCN(String park_address_cn) {
        this.park_address_cn = park_address_cn;
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
//        return getParkName();
//    }
//
//    @Override
//    public String getAddressForResult() {
//        return getParkAddress();
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