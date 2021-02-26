package com.victoryam.wepaws.Domain;

public class Vet {
    private int vet_id;
    private String vet_name;
    private String vet_name_chi;
    private String vet_add;
    private String vet_add_chi;

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

    public String getVetNameChi() {
        return vet_name_chi;
    }

    public void setVetNameChi(String vet_name_chi) {
        this.vet_name = vet_name_chi;
    }

    public String getVetAdd() {
        return vet_add;
    }

    public void setVetAdd(String vet_add) {
        this.vet_add = vet_add;
    }

    public String getVetAddChi() {
        return vet_add_chi;
    }

    public void setVetAddChi(String vet_add_chi) {
        this.vet_add_chi = vet_add_chi;
    }
}
