package com.victoryam.wepaws.Domain;

import android.media.Rating;

import com.victoryam.wepaws.Domain.Review;
import com.victoryam.wepaws.Domain.Species;
import com.victoryam.wepaws.Utils.IResult;

import java.util.List;

public class Hotel {
    private int hotel_id;
    private String hotel_name;
    private String hotel_name_cn;
    private String hotel_address;
    private String hotel_address_cn;
    private String district;
    private String description;
    private Boolean overnight;
    private int phone;
    private String status;
    private List<Species> speciesList;
    private List<Rating> ratingList;
    private List<Review> reviewList;

    public int getHotelId() {
        return hotel_id;
    }

    public void setHotelId(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotelName() {
        return hotel_name;
    }

    public void setHotelName(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotelNameCN() {
        return hotel_name_cn;
    }

    public void setHotelNameCN(String hotel_name_cn) {
        this.hotel_name_cn = hotel_name_cn;
    }

    public String getHotelAddress() {
        return hotel_address;
    }

    public void setHotelAddress(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    public String getHotelAddressCN() {
        return hotel_address_cn;
    }

    public void setHotelAddressCN(String hotel_address_cn) {
        this.hotel_address_cn = hotel_address_cn;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Boolean getOvernight() {
        return overnight;
    }

    public void setOvernight(Boolean overnight) {
        this.overnight = overnight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
//        return getHotelName();
//    }
//
//    @Override
//    public String getAddressForResult() {
//        return getHotelAddress();
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