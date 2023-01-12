/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.viewmodels;

import hr.algebra.models.PetOwner;
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
public class OwnerViewModel {

    private final PetOwner petOwner;

    public OwnerViewModel(PetOwner petOwner) {
        this.petOwner = petOwner;
    }

    public PetOwner getPetOwner() {
        return petOwner;
    }

    public IntegerProperty getIdVeterinarianProperty() {
        return new SimpleIntegerProperty(petOwner.getIDPetOwner());
    }
    
    public StringProperty getFirstNameProperty(){
        return new SimpleStringProperty(petOwner.getFirstName());
    }
    
    public StringProperty getLastNameProperty(){
        return new SimpleStringProperty(petOwner.getLastName());
    }
    
    public StringProperty getEmailProperty(){
        return new SimpleStringProperty(petOwner.getEmail());
    }
    
    public ObjectProperty<byte[]> getPictureProperty(){
        return new SimpleObjectProperty<>(petOwner.getPicture()); 
    }

}
