/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao;

import hr.algebra.models.Pet;
import hr.algebra.models.PetOwner;
import hr.algebra.models.Veterinarian;
import java.util.List;

/**
 *
 * @author dbasi
 */
public interface Repository {
    
    int addPet(Pet data) throws Exception;
    void updatePet(Pet pet) throws Exception;
    void deletePet(Pet pet) throws Exception;
    Pet getPet(int idPet) throws Exception;
    List<Pet> getPets() throws Exception;
   
    int addPetOwner(PetOwner data) throws Exception;
    void updatePetOwner(PetOwner petOwner) throws Exception;
    void deletePetOwner(PetOwner petOwner) throws Exception;
    PetOwner getPetOwner(int idPetOwner) throws Exception;
    List<PetOwner> getPetOwners() throws Exception;
    
    int addVeterinarian(Veterinarian data) throws Exception;
    void updateVeterinarian(Veterinarian veterinarian) throws Exception;
    void deleteVeterinarian(Veterinarian veterinarian) throws Exception;
    Veterinarian getVeterinarian(int idVeterinarian) throws Exception;
    List<Veterinarian> getVeterinarians() throws Exception;
    
    default void release() throws Exception {}
}
