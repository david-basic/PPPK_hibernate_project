/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.dao.sql;

import hr.algebra.dao.Repository;
import hr.algebra.models.Pet;
import hr.algebra.models.PetOwner;
import hr.algebra.models.Veterinarian;
import java.util.List;
import javax.persistence.EntityManager;

public class HibernateRepository implements Repository {

    @Override
    public void release() throws Exception {
        HibernateFactory.release();
    }

    // <editor-fold defaultstate="collapsed" desc="Pet">
    @Override
    public int addPet(Pet data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            Pet pet = new Pet(data);
            em.persist(pet);
            em.getTransaction().commit();

            return pet.getIDPet();
        }
    }

    @Override
    public void updatePet(Pet pet) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.find(Pet.class, pet.getIDPet()).updateData(pet);
        }
    }

    @Override
    public void deletePet(Pet pet) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(pet) ? pet : em.merge(pet));
            em.getTransaction().commit();
        }
    }

    @Override
    public Pet getPet(int idPet) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.find(Pet.class, idPet);
        }
    }

    @Override
    public List<Pet> getPets() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.SELECT_PETS).getResultList();
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="PetOwner">
    @Override
    public int addPetOwner(PetOwner data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            PetOwner petOwner = new PetOwner(data);
            em.persist(petOwner);
            em.getTransaction().commit();

            return petOwner.getIDPetOwner();
        }
    }

    @Override
    public void updatePetOwner(PetOwner petOwner) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.find(PetOwner.class, petOwner.getIDPetOwner()).updateData(petOwner);
        }
    }

    @Override
    public void deletePetOwner(PetOwner petOwner) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(petOwner) ? petOwner : em.merge(petOwner));
            em.getTransaction().commit();
        }
    }

    @Override
    public PetOwner getPetOwner(int idPetOwner) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.find(PetOwner.class, idPetOwner);
        }
    }

    @Override
    public List<PetOwner> getPetOwners() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.SELECT_PET_OWNERS).getResultList();
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Veterinarian">
    @Override
    public int addVeterinarian(Veterinarian data) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            Veterinarian veterinarian = new Veterinarian(data);
            em.persist(veterinarian);
            em.getTransaction().commit();

            return veterinarian.getIDVeterinarian();
        }
    }

    @Override
    public void updateVeterinarian(Veterinarian veterinarian) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.find(Veterinarian.class, veterinarian.getIDVeterinarian()).updateData(veterinarian);
        }
    }

    @Override
    public void deleteVeterinarian(Veterinarian veterinarian) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            em.getTransaction().begin();
            em.remove(em.contains(veterinarian) ? veterinarian : em.merge(veterinarian));
            em.getTransaction().commit();
        }
    }

    @Override
    public Veterinarian getVeterinarian(int idVeterinarian) throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.find(Veterinarian.class, idVeterinarian);
        }
    }

    @Override
    public List<Veterinarian> getVeterinarians() throws Exception {
        try (EntityManagerWrapper emw = HibernateFactory.getEntityManager()) {
            EntityManager em = emw.get();
            return em.createNamedQuery(HibernateFactory.SELECT_VETERINARIANS).getResultList();
        }
    }
    // </editor-fold>
}
