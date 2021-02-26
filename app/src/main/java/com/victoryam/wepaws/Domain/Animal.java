package com.victoryam.wepaws.Domain;

public class Animal {
    private int animal_id;
    private String animal_name;
    private String animal_name_chi;

    public int getAnimalId() {
        return animal_id;
    }

    public void setAnimalId(int animal_id) {
        this.animal_id = animal_id;
    }

    public String getAnimalName() {
        return animal_name;
    }

    public void setAnimalName(String animal_name) {
        this.animal_name = animal_name;
    }

    public String getAnimalNameChi() {
        return animal_name_chi;
    }

    public void setAnimalNameChi(String animal_name_chi) {
        this.animal_name_chi = animal_name_chi;
    }
}
