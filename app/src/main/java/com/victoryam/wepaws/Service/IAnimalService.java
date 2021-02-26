package com.victoryam.wepaws.Service;

import com.victoryam.wepaws.Domain.Animal;

import java.util.List;

public interface IAnimalService {
    public List<Animal> getAllAnimal();
    public void addAnimal(Animal animal);
    public void updateAnimal(Animal animal);
    public Animal getAnimalById(int animalId);
    public void removeAnimalById(int animalId);
}
