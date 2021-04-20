package com.wepaws.wepaws.Domain;

public class Species {
    private int species_id;
    private String species_name;
    private String species_name_cn;

    public int getSpeciesId() {
        return species_id;
    }

    public void setSpeciesId(int species_id) {
        this.species_id = species_id;
    }

    public String getSpeciesName() {
        return species_name;
    }

    public void setSpeciesName(String species_name) {
        this.species_name = species_name;
    }

    public String getSpeciesNameCN() {
        return species_name_cn;
    }

    public void setSpeciesNameCN(String species_name_cn) {
        this.species_name_cn = species_name_cn;
    }
}
