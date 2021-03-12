package com.victoryam.wepaws.Domain;

import android.media.Rating;

import com.victoryam.wepaws.Utils.IResult;

import java.util.List;

public class Store implements IResult {
    private int store_id;
    private String store_name;
    private String store_name_cn;
    private String store_address;
    private String store_address_cn;
    private String district;
    private String description;
    private Boolean overnight;
    private int phone;
    private String status;
    private List<Species> speciesList;
    private List<Rating> ratingList;
    private List<Review> reviewList;

    public int getStoreId() {
        return store_id;
    }

    public void setStoreId(int store_id) {
        this.store_id = store_id;
    }

    public String getStoreName() {
        return store_name;
    }

    public void setStoreName(String store_name) {
        this.store_name = store_name;
    }

    public String getStoreNameCN() {
        return store_name_cn;
    }

    public void setStoreNameCN(String store_name_cn) {
        this.store_name_cn = store_name_cn;
    }

    public String getStoreAddress() {
        return store_address;
    }

    public void setStoreAddress(String store_address) {
        this.store_address = store_address;
    }

    public String getStoreAddressCN() {
        return store_address_cn;
    }

    public void setStoreAddressCN(String store_address_cn) {
        this.store_address_cn = store_address_cn;
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

    @Override
    public String getNameForResult() {
        return getStoreName();
    }

    @Override
    public String getAddressForResult() {
        return getStoreAddress();
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