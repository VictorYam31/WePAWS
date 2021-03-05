package com.victoryam.wepaws;

import com.victoryam.wepaws.Domain.Category;
import com.victoryam.wepaws.Domain.PetSpecies;
import com.victoryam.wepaws.Domain.VetMaster;

public class Result {

    private final VetMaster vet;
    private final PetSpecies animal;
    private final Category category;
    private final float rating;

    public Result(VetMaster vet, PetSpecies animal, Category category, float rating) {
        this.vet = vet;
        this.animal = animal;
        this.category = category;
        this.rating = rating;
    }

    public VetMaster getVet() {
      return this.vet;
    };

    public PetSpecies getAnimal() {
        return this.animal;
    };

    public Category getCategory() {
        return this.category;
    };

    public float getRating() {
        return this.rating;
    };

}
