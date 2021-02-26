package com.victoryam.wepaws.Service;

import com.victoryam.wepaws.Domain.Vet;

import java.util.List;

public interface IVetService {
    public List<Vet> getAllVet();
    public void addVet(Vet vet);
    public void updateVet(Vet vet);
    public Vet getVetById(int vetId);
    public void removeVetById(int VetId);
}
