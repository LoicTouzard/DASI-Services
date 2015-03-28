/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifroutardb3325.entites;

import javax.persistence.Entity;

/**
 *
 * @author ltouzard & ggouzi
 */
@Entity
public class Sejour  extends Voyage{

    private String residence;
    
    
    public Sejour() {
        super();
    }

    public Sejour( String code, String description, Long duree, String nom, String residence) {
        super(code, description, duree, nom);
        this.residence = residence;
    }
    
    public String getType(){
        return "Séjour";
    }

    public Sejour( Long id, String code, String description, Long duree, String nom, String residence) {
        super(id, code, description, duree, nom);
        this.residence = residence;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
    
}
