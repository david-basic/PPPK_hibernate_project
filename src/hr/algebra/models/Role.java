/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

/**
 *
 * @author dbasi
 */
public class Role {
    private final Veterinarian veterinarian;
    private final PetOwner petOwner;

    public Role(Veterinarian veterinarian, PetOwner petOwner) {
        this.veterinarian = veterinarian;
        this.petOwner = petOwner;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }
}
