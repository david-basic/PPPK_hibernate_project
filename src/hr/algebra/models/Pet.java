/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dbasi
 */
@Entity
@Table(name = "Pet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pet.findAll", query = "SELECT p FROM Pet p")
    , @NamedQuery(name = "Pet.findByIDPet", query = "SELECT p FROM Pet p WHERE p.iDPet = :iDPet")
    , @NamedQuery(name = "Pet.findByPetName", query = "SELECT p FROM Pet p WHERE p.petName = :petName")
    , @NamedQuery(name = "Pet.findBySpecies", query = "SELECT p FROM Pet p WHERE p.species = :species")
    , @NamedQuery(name = "Pet.findByAge", query = "SELECT p FROM Pet p WHERE p.age = :age")})
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPet")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer iDPet;
    @Basic(optional = false)
    @Column(name = "PetName")
    private String petName;
    @Basic(optional = false)
    @Column(name = "Species")
    private String species;
    @Basic(optional = false)
    @Column(name = "Age")
    private int age;
    @Lob
    @Column(name = "Picture")
    private byte[] picture;
    @JoinColumn(name = "PetOwnerID", referencedColumnName = "IDPetOwner")
    @ManyToOne(optional = false)
    private PetOwner petOwnerID;
    @JoinColumn(name = "VeterinarianID", referencedColumnName = "IDVeterinarian")
    @ManyToOne(optional = false)
    private Veterinarian veterinarianID;

    public Pet() {
    }

    public Pet(Integer iDPet) {
        this.iDPet = iDPet;
    }

    public Pet(Integer iDPet, String petName, String species, int age) {
        this.iDPet = iDPet;
        this.petName = petName;
        this.species = species;
        this.age = age;
    }

    public Integer getIDPet() {
        return iDPet;
    }

    public void setIDPet(Integer iDPet) {
        this.iDPet = iDPet;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public PetOwner getPetOwnerID() {
        return petOwnerID;
    }

    public void setPetOwnerID(PetOwner petOwnerID) {
        this.petOwnerID = petOwnerID;
    }

    public Veterinarian getVeterinarianID() {
        return veterinarianID;
    }

    public void setVeterinarianID(Veterinarian veterinarianID) {
        this.veterinarianID = veterinarianID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPet != null ? iDPet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pet)) {
            return false;
        }
        Pet other = (Pet) object;
        if ((this.iDPet == null && other.iDPet != null) || (this.iDPet != null && !this.iDPet.equals(other.iDPet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hr.algebra.models.Pet[ iDPet=" + iDPet + " ]";
    }
    
}
