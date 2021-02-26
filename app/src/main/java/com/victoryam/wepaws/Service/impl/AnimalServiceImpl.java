package com.victoryam.wepaws.Service.impl;

import com.victoryam.wepaws.Domain.Animal;
import com.victoryam.wepaws.Service.IAnimalService;

import java.util.LinkedList;
import java.util.List;

public class AnimalServiceImpl implements IAnimalService {
    public List<Animal> getAllAnimal() {
        return new LinkedList<Animal>();
    }

    public void addAnimal(Animal animal) {

    }

    public void updateAnimal(Animal animal) {

    }

    public Animal getAnimalById(int animalId) {
        return new Animal();
    }

    public void removeAnimalById(int animalId) {

    }
}
