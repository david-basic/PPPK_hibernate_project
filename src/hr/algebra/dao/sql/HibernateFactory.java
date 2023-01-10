/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao.sql;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author dbasi
 */
public class HibernateFactory {

    public static final String SELECT_PETS = "Pet.findAll";
    public static final String FIND_PET_BY_ID = "Pet.findByIDPet";
    public static final String FIND_PET_BY_NAME = "Pet.findByPetName";
    public static final String FIND_PET_BY_SPECIES = "Pet.findBySpecies";
    public static final String FIND_PET_BY_AGE = "Pet.findByAge";
    
    public static final String SELECT_PET_OWNERS = "PetOwner.findAll";
    public static final String FIND_PET_OWNER_BY_ID = "PetOwner.findByIDPetOwner";
    public static final String FIND_PET_OWNER_BY_FIRST_NAME = "PetOwner.findByFirstName";
    public static final String FIND_PET_OWNER_BY_LAST_NAME = "PetOwner.findByLastName";
    public static final String FIND_PET_OWNER_BY_EMAIL = "PetOwner.findByEmail";
    
    public static final String SELECT_VETERINARIANS = "Veterinarian.findAll";
    public static final String FIND_VETERINARIAN_BY_ID = "Veterinarian.findByIDVeterinarian";
    public static final String FIND_VETERINARIAN_BY_FIRST_NAME = "Veterinarian.findByFirstName";
    public static final String FIND_VETERINARIAN_BY_LAST_NAME = "Veterinarian.findByLastName";
    public static final String FIND_VETERINARIAN_BY_EMAIL = "Veterinarian.findByEmail";
    
    private HibernateFactory() {
    }
    
    private static final String PERSISTENCE_UNIT = "VetManagerPU";
    
    private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
    
    public static EntityManagerWrapper getEntityManager(){
        return new EntityManagerWrapper(EMF.createEntityManager());
    }
    
    public static void release(){
        EMF.close();
    }
}
