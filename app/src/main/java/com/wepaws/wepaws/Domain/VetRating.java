package com.wepaws.wepaws.Domain;

public class VetRating {
    private int vet_id;
    private String user;
    private String rate;


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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
