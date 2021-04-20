package com.wepaws.wepaws.Domain;

public class VetReview {
    private int vet_id;
    private String user;
    private String review;
    private String displayed;

    public int getVetId() {
        return vet_id;
    }

    public void setVetId(int vet_id) {
        this.vet_id = vet_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDisplayed() {
        return displayed;
    }

    public void getDisplayed(String displayed) {
        this.displayed = displayed;
    }
}
