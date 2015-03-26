/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.entites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ltouzard
 */
@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String civilite;
    private String nom;
    private String prenom;
    @Column(unique=true)
    private String mail;
    private String adresse_postale;
    private String tel;
    
    @OneToMany
    private List<Devis> listeDevis = new ArrayList();
    
    public Client() {
        
    }

    public Client(Long id, String civilite, String nom, String prenom,  String adresse_postale, String tel, String mail) {
        this.id = id;
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse_postale = adresse_postale;
        this.tel = tel;
    }
    
     public Client(String civilite, String nom, String prenom, String adresse_postale, String tel, String mail ) {
        this.civilite = civilite;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse_postale = adresse_postale;
        this.tel = tel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdresse_postale() {
        return adresse_postale;
    }

    public void setAdresse_postale(String adresse_postale) {
        this.adresse_postale = adresse_postale;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    public List<Devis> getListeDevis() {
        return listeDevis;
    }

    public void setListeDevis(List<Devis> listeDevis) {
        this.listeDevis = listeDevis;
    }
    
    public void addDevis(Devis devis) {
        this.listeDevis.add(devis);
    }
    
    public void removeDevis(Devis devis) {
        this.listeDevis.remove(devis);
    }


    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ifroutardb3325.Client[ id=" + id + " ]";
    }
    
}
