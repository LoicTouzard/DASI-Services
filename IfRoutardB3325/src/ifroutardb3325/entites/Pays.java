/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author ltouzard
 */
@Entity
public class Pays implements Serializable {

    @ManyToMany
    private List<Voyage> listeVoyage = new ArrayList();
    
    public Pays() {
        
    }

    public Pays(String nom, String capitale, String code, int population, int superficie, String langue, String region) {
        this.nom = nom;
        this.capitale = capitale;
        this.code = code;
        this.population = population;
        this.superficie = superficie;
        this.langue = langue;
        this.region = region;
    }

    public Pays(Long id, String nom, String capitale, String code, int population, int superficie, String langue, String region) {
        this.id = id;
        this.nom = nom;
        this.capitale = capitale;
        this.code = code;
        this.population = population;
        this.superficie = superficie;
        this.langue = langue;
        this.region = region;
    }
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String capitale;
    private String code;
    private int population;
    private int superficie;
    private String langue;
    private String region;
    //@ManyToMany(mappedBy="Pays")
    //private List<Voyage> listeVoyage;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCapitale() {
        return capitale;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNbHabitants() {
        return population;
    }

    public void setNbHabitants(int population) {
        this.population = population;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public String getLangueOfficielle() {
        return langue;
    }

    public void setLangueOfficielle(String langue) {
        this.langue = langue;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Voyage> getListeVoyage() {
        return listeVoyage;
    }

    public void setListeVoyage(List<Voyage> listeVoyage) {
        this.listeVoyage = listeVoyage;
    }
    
    public List<Voyage> addVoyage(Voyage v) {
        this.listeVoyage.add(v);
        return listeVoyage;
    }

    public List<Voyage> deleteVoyage(Voyage v) {
        this.listeVoyage.remove(v);
        return listeVoyage;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pays)) {
            return false;
        }
        Pays other = (Pays) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ifroutardb3325.Pays[ id=" + id + " ]";
    }
    
}
