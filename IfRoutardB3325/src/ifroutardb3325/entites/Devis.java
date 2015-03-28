/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.entites;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ltouzard & ggouzi
 */
@Entity
public class Devis implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateCreation;
    private int nbPersonnes;
    
    @ManyToOne
    private Client client;
    
    @ManyToOne
    private Depart depart;
    
    @ManyToOne
    private Conseiller conseiller;
    
    public Devis() {
        
    }

    public Devis(Date date, int nbPersonnes) {
        this.dateCreation = date;
        this.nbPersonnes = nbPersonnes;
    }
    
    public Devis(Long id, Date date, int nbPersonnes) {
        this.id = id;
        this.dateCreation = date;
        this.nbPersonnes = nbPersonnes;
    }

    public Date getDate() {
        return dateCreation;
    }

    public void setDate(Date date) {
        this.dateCreation = date;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Depart getDepart() {
        return depart;
    }

    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    public Conseiller getConseiller() {
        return conseiller;
    }

    public void setConseiller(Conseiller conseiller) {
        this.conseiller = conseiller;
    }
   
    public Voyage getVoyage() {
        return depart.getVoyage();
    }
    
    public float getPrixTotal(){
        return this.nbPersonnes*this.depart.getPrix();
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
        if (!(object instanceof Devis)) {
            return false;
        }
        Devis other = (Devis) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ifroutardb3325.entites.Devis[ id=" + id + " ]";
    }
    
}
