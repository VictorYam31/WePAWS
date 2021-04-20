package com.wepaws.wepaws.Domain;

import java.util.List;

public class VetMaster {
    private int vet_id;
    private String vet_name;
    private String vet_name_cn;
    private String vet_address;
    private String vet_address_cn;
    private List<VetRating> vetRatingList;
    private List<VetReview> vetReviewList;

    public int getVetId() {
        return vet_id;
    }

    public void setVetId(int vet_id) {
        this.vet_id = vet_id;
    }

    public String getVetName() {
        return vet_name;
    }

    public void setVetName(String vet_name) {
        this.vet_name = vet_name;
    }

    public String getVetNameCN() {
        return vet_name_cn;
    }

    public void setVetNameCN(String vet_name_cn) {
        this.vet_name_cn = vet_name_cn;
    }

    public String getVetAddress() {
        return vet_address;
    }

    public void setVetAddress(String vet_address) {
        this.vet_address = vet_address;
    }

    public String getVetAddressCN() {
        return vet_address_cn;
    }

    public void setVetAddressCN(String vet_address_cn) {
        this.vet_address_cn = vet_address_cn;
    }

    public List<VetRating> getVetRatingList() {
        return vetRatingList;
    }

    public void setVetRatingList(List<VetRating> vetRatingList) {
        this.vetRatingList = vetRatingList;
    }

    public List<VetReview> getVetReviewList() {
        return vetReviewList;
    }

    public void setVetReviewList(List<VetReview> vetReviewList) {
        this.vetReviewList = vetReviewList;
    }
}
