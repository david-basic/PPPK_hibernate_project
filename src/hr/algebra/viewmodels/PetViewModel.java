/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.viewmodels;

import hr.algebra.models.Pet;
import hr.algebra.models.PetOwner;
import hr.algebra.models.Veterinarian;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author dbasi
 */
public class PetViewModel {

    private final Pet pet;

    public PetViewModel(Pet pet) {
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

    public IntegerProperty getIdPetProperty() {
        return new SimpleIntegerProperty(pet.getIDPet());
    }

    public IntegerProperty getAgeProperty() {
        return new SimpleIntegerProperty(pet.getAge());
    }

    public StringProperty getPetNameProperty() {
        return new SimpleStringProperty(pet.getPetName());
    }

    public StringProperty getSpeciesProperty() {
        return new SimpleStringProperty(pet.getSpecies());
    }

    public ObjectProperty<Veterinarian> getVeterinarianProperty() {
        return new SimpleObjectProperty<>(pet.getVeterinarianID()); // returns object Veterinarian not ID method should be renamed, same with PetOwner
    }

    public ObjectProperty<PetOwner> getPetOwnerProperty() {
        return new SimpleObjectProperty<>(pet.getPetOwnerID());
    }

    public ObjectProperty<byte[]> getPictureProperty() {
        return new SimpleObjectProperty<>(pet.getPicture());
    }

}
