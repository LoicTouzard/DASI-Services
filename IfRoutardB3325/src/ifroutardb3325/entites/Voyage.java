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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author ltouzard & ggouzi
 */
@Entity
@Inheritance (strategy=InheritanceType.SINGLE_TABLE)
public abstract class Voyage implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;
    private String description;
    private Long duree;
    private String nom;
    
    @ManyToMany
    private List<Pays> pays = new ArrayList();

    @OneToMany
    private List<Depart> listeDepart = new ArrayList();

    
    
    public Voyage() {
        
    }

    public Voyage(String code, String description, Long duree, String nom) {
        this.code = code;
        this.description = description;
        this.duree = duree;
        this.nom = nom;
    }

    public Voyage(Long id, String code, String description, Long duree, String nom) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.duree = duree;
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public abstract String getType();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuree() {
        return duree;
    }

    public void setDuree(Long duree) {
        this.duree = duree;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Pays> getListePays() {
        return pays;
    }

    public void setListePays(List<Pays> listePays) {
        this.pays = listePays;
    }
    
    public List<Pays> addPays(Pays p) {
        this.pays.add(p);
        return pays;
    }

    public List<Pays> deletePays(Pays p) {
        this.pays.remove(p);
        return pays;
    }
    
    public List<Depart> getListeDepart() {
        return listeDepart;
    }

    public void setListeDepart(List<Depart> listeDepart) {
        this.listeDepart = listeDepart;
    }
    
    public List<Depart> addDepart(Depart d) {
        this.listeDepart.add(d);
        return listeDepart;
    }

    public List<Depart> deleteDepart(Depart d) {
        this.listeDepart.remove(d);
        return listeDepart;
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
        if (!(object instanceof Voyage)) {
            return false;
        }
        Voyage other = (Voyage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ifroutardb3325.entites.Voyage[ id=" + id + " ]";
    }
    
}
