package com.victoryam.wepaws;

import com.victoryam.wepaws.Domain.Animal;
import com.victoryam.wepaws.Domain.Category;
import com.victoryam.wepaws.Domain.Vet;

public class Result {

    private final Vet vet;
    private final Animal animal;
    private final Category category;
    private final float rating;

    public Result(Vet vet, Animal animal, Category category, float rating) {
        this.vet = vet;
        this.animal = animal;
        this.category = category;
        this.rating = rating;
    }

    public Vet getVet() {
      return this.vet;
    };

    public Animal getAnimal() {
        return this.animal;
    };

    public Category getCategory() {
        return this.category;
    };

    public float getRating() {
        return this.rating;
    };

}
