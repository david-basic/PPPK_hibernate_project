/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.models;

import hr.algebra.dao.sql.HibernateFactory;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dbasi
 */
@Entity
@Table(name = "PetOwner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = HibernateFactory.SELECT_PET_OWNERS, query = "SELECT p FROM PetOwner p")
    , @NamedQuery(name = HibernateFactory.FIND_PET_OWNER_BY_ID, query = "SELECT p FROM PetOwner p WHERE p.iDPetOwner = :iDPetOwner")
    , @NamedQuery(name = HibernateFactory.FIND_PET_OWNER_BY_FIRST_NAME, query = "SELECT p FROM PetOwner p WHERE p.firstName = :firstName")
    , @NamedQuery(name = HibernateFactory.FIND_PET_OWNER_BY_LAST_NAME, query = "SELECT p FROM PetOwner p WHERE p.lastName = :lastName")
    , @NamedQuery(name = HibernateFactory.FIND_PET_OWNER_BY_EMAIL, query = "SELECT p FROM PetOwner p WHERE p.email = :email")})
public class PetOwner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDPetOwner")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer iDPetOwner;
    @Basic(optional = false)
    @Column(name = "FirstName")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LastName")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Lob
    @Column(name = "Picture")
    private byte[] picture;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petOwnerID")
    private Collection<Pet> petCollection;

    public PetOwner() {
    }

    public PetOwner(Integer iDPetOwner) {
        this.iDPetOwner = iDPetOwner;
    }

    public PetOwner(Integer iDPetOwner, String firstName, String lastName, String email) {
        this.iDPetOwner = iDPetOwner;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public PetOwner(PetOwner data) {
        updateData(data);
    }

    public Integer getIDPetOwner() {
        return iDPetOwner;
    }

    public void setIDPetOwner(Integer iDPetOwner) {
        this.iDPetOwner = iDPetOwner;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @XmlTransient
    public Collection<Pet> getPetCollection() {
        return petCollection;
    }

    public void setPetCollection(Collection<Pet> petCollection) {
        this.petCollection = petCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPetOwner != null ? iDPetOwner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PetOwner)) {
            return false;
        }
        PetOwner other = (PetOwner) object;
        if ((this.iDPetOwner == null && other.iDPetOwner != null) || (this.iDPetOwner != null && !this.iDPetOwner.equals(other.iDPetOwner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void updateData(PetOwner data) {
        firstName = data.firstName;
        lastName = data.lastName;
        email = data.email;
        picture = data.picture;
    }
}
