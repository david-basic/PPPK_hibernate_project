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
@Table(name = "Veterinarian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = HibernateFactory.SELECT_VETERINARIANS, query = "SELECT v FROM Veterinarian v")
    , @NamedQuery(name = HibernateFactory.FIND_VETERINARIAN_BY_ID, query = "SELECT v FROM Veterinarian v WHERE v.iDVeterinarian = :iDVeterinarian")
    , @NamedQuery(name = HibernateFactory.FIND_VETERINARIAN_BY_FIRST_NAME, query = "SELECT v FROM Veterinarian v WHERE v.firstName = :firstName")
    , @NamedQuery(name = HibernateFactory.FIND_VETERINARIAN_BY_LAST_NAME, query = "SELECT v FROM Veterinarian v WHERE v.lastName = :lastName")
    , @NamedQuery(name = HibernateFactory.FIND_VETERINARIAN_BY_EMAIL, query = "SELECT v FROM Veterinarian v WHERE v.email = :email")})
public class Veterinarian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDVeterinarian")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer iDVeterinarian;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "veterinarianID")
    private Collection<Pet> petCollection;

    public Veterinarian() {
    }

    public Veterinarian(Integer iDVeterinarian) {
        this.iDVeterinarian = iDVeterinarian;
    }

    public Veterinarian(Integer iDVeterinarian, String firstName, String lastName, String email) {
        this.iDVeterinarian = iDVeterinarian;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Veterinarian(Veterinarian data) {
        updateData(data);
    }

    public Integer getIDVeterinarian() {
        return iDVeterinarian;
    }

    public void setIDVeterinarian(Integer iDVeterinarian) {
        this.iDVeterinarian = iDVeterinarian;
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
        hash += (iDVeterinarian != null ? iDVeterinarian.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Veterinarian)) {
            return false;
        }
        Veterinarian other = (Veterinarian) object;
        if ((this.iDVeterinarian == null && other.iDVeterinarian != null) || (this.iDVeterinarian != null && !this.iDVeterinarian.equals(other.iDVeterinarian))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void updateData(Veterinarian data) {
        firstName = data.firstName;
        lastName = data.lastName;
        email = data.email;
        picture = data.picture;
    }

}
