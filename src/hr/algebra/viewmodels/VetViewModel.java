/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.viewmodels;

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
public class VetViewModel {
    
    private final Veterinarian veterinarian;

    public VetViewModel(Veterinarian veterinarian) {
        this.veterinarian = veterinarian;
    }

    public Veterinarian getVeterinarian() {
        return veterinarian;
    }
    
    public IntegerProperty getIdVeterinarianProperty(){
        return new SimpleIntegerProperty(veterinarian.getIDVeterinarian());
    }
    
    public StringProperty getFirstNameProperty(){
        return new SimpleStringProperty(veterinarian.getFirstName());
    }
    
    public StringProperty getLastNameProperty(){
        return new SimpleStringProperty(veterinarian.getLastName());
    }
    
    public StringProperty getEmailProperty(){
        return new SimpleStringProperty(veterinarian.getEmail());
    }
    
    public ObjectProperty<byte[]> getPictureProperty(){
        return new SimpleObjectProperty<>(veterinarian.getPicture()); 
    }
    
}
