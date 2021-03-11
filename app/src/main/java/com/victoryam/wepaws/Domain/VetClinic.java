package com.victoryam.wepaws.Domain;

import java.util.List;

public class VetClinic {
    private int clinic_id;
    private String clinic_name;
    private String clinic_name_cn;
    private String clinic_address;
    private String clinic_address_cn;
    private String district;
    private String opening_hours;
    private int phone;
    private String status;
    private List<VetMaster> vetMasterList;
    private List<PetSpecies> petSpeciesList;

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

    public String getOpeningHours() {
        return opening_hours;
    }

    public void setOpeningHours(String opening_hours) {
        this.opening_hours = opening_hours;
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

    public List<PetSpecies> getPetSpeciesList() {
        return petSpeciesList;
    }

    public void setPetSpeciesList(List<PetSpecies> petSpeciesList) {
        this.petSpeciesList = petSpeciesList;
    }


    //Clinic Review

    //Clinic Rating

    public List<VetMaster> getVetMasterList() {
        return vetMasterList;
    }

    public void setVetMasterList(List<VetMaster> vetMasterList) {
        this.vetMasterList = vetMasterList;
    }
}

